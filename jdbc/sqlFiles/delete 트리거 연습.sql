

SELECT * FROM tbl_money;
SELECT * FROM tbl_buy2;
SELECT * FROM TBL_SALE ts ;

ALTER TABLE tbl_money
ADD FOREIGN KEY (buy_seq)
REFERENCES tbl_buy2 (buy_seq);

DELETE FROM TBL_BUY2 tb WHERE buy_seq = 1007;


ALTER TABLE tbl_buy2
ADD 
PRIMARY KEY (buy_seq);

ALTER TABLE tbl_money
ADD FOREIGN KEY (buy_seq)
REFERENCES tbl_buy2 (buy_seq)
ON DELETE CASCADE;

CREATE OR REPLACE TRIGGER set_delete_trig
AFTER DELETE ON tbl_buy2
FOR EACH ROW
DECLARE 
	vtotal number(7);
BEGIN
	DELETE  FROM TBL_MONEY tm  
			WHERE buy_seq = :OLD.buy_seq;
	SELECT  sum(money)
       INTO  vtotal
       FROM  tbl_money
       WHERE  customid = :old.customid;
     UPDATE  tbl_sale SET  total=vtotal 
    	WHERE  customid = :old.customid;
     dbms_output.put_line('*' || :old.customid || ' ' || vtotal);
    EXCEPTION          -- EXCEPTION 추가하여 처리 -> 메시지 출력
      WHEN OTHERS THEN 
      	 dbms_output.put_line('//// fail  ////');
   		 ROLLBACK;
END;

--cascade 때문에 tbl_buy2 테이블 삭제하면 tbl_money 테이블 삭제되면서 충돌.
--mutating 오류 : 돌연변이 즉 잘못 실행되고 있음.(같)
