--트리거에서 오류발생하는 대표적인 경우 : 트리거 대상 테이블과 동일한 테이블에 대한 DML실행
--		ALTER trigger sale_info disablel;

-- 집계 테이블 : 고객별 총 구매금액 저장
CREATE TABLE tbl_sale
(
	customid varchar2(20) PRIMARY KEY,
	total NUMBER(7)
);

DROP TABLE tbl_sale;

--------------------------------------------
INSERT INTO p_buy
VALUES (2000,'mina012','CJBAB012',13,sysdate,null);

SELECT * FROM p_buy;
--------------------------------------------
ALTER TABLE sale_info_trig enable;

--------------------------------------------
CREATE  OR  REPLACE  TRIGGER  sale_info_trig
AFTER  INSERT  ON  p_buy
FOR  EACH  ROW 
DECLARE 
    vtotal number(7):=0;      -- 일반 변수 초기화 
    vcnt number(7):=0;
    vprice number(7);
BEGIN 
    SELECT  count(*)       -- 0 또는 1
       INTO  vcnt
       FROM   tbl_sale WHERE  customid=:NEW.customid;
   dbms_output.put_line('*' || :NEW.customid || ' ' || vtotal || ' ' || vcnt);
    SELECT  sum(money)
       INTO  vtotal      -- 지정된 고객에 대한 money 총합계
       FROM  p_buy		-- 트리거 걸려있는 테이블 대상으로 DML
       WHERE  customid = :NEW.customid;
    SELECT price 
       INTO vprice
       from tbl_product 
       WHERE pcode = :NEW.pcode;
    UPDATE p_buy SET money = vprice * :NEW.QUANTITY 
      WHERE BUY_SEQ = :NEW.buy_seq;
    IF vcnt=0 THEN  
        INSERT  INTO  tbl_sale VALUES  (:NEW.customid, vtotal);
    ELSE 
        UPDATE  tbl_sale 
           SET  total=vtotal 
             WHERE  customid = :NEW.customid;
    END  IF;
    EXCEPTION          -- EXCEPTION 추가하여 처리 -> 메시지 출력, rollback 
       WHEN no_data_found THEN      -- 여러 종류 예외처리하는 예시
          dbms_output.put_line('no_data_found');
      WHEN OTHERS THEN 
      dbms_output.put_line('실행 실패----------------------------------');
END;