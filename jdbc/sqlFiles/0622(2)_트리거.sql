-- 트리거(trigger)는 데이터베이스 시스템에서 
--        데이터의 `입력, 갱신, 삭제() 등의 이벤트`가 발생하 때마다 자동적으로 수행되는 사용자 정의 프로시저이다.
--        특정 테이블에 속해있는 객체
/*
 * 
CREATE OR REPLACE TRIGGER 트리거이름
BEFORE | AFTER                  -- 동작 시점  | << or 기호
INSERT | UPDATE | DELETE ON 테이블명        -- 테이블과 해당 C,R,D 지정
[FOR EACH ROW]      -- 행 트리거. 테이블 행 단위 변화에 대해 동작하기.
[WHEN(조건)]      -- 특정컬럼에 대한 값 조건
DECLARE    -- 변수선언
...
BEGIN
...
[EXCEPTION]  -- 예외사항
END; 
 * 
 * 
 */


SELECT * FROM p_buy;

CREATE OR REPLACE TRIGGER cancel_buy
AFTER DELETE ON p_buy		--p_buy 테이블 대상으로 DELETE 실행한 후에 동작한디.
FOR EACH ROW 	--만족(적용)하는 행이 여러개 일때, :
				--OLD UPDATE 또는 DELETE하시전 값, :NEW 는 INSERT 한값

BEGIN
	--구매 취소(p_buy 테이블에서 삭제)한 데이터 tri_temp 임시 테이블에 insert : 여러행에 대한 작업(행 트리거)
	INSERT INTO tri_temp
	VALUES
	(:OLD.buy_seq, :OLD.customid, :OLD.pcode, :OLD.quantity, :OLD.buy_date, :OLD.money);
END;

--데이터는 복사하지 않고 테이블 구조만 동일하게 만들기
CREATE TABLE tri_temp
as
SELECT * FROM P_BUY WHERE buy_seq = 0;

--확인
SELECT * FROM tri_temp;
SELECT * FROM p_buy;


--트리거 동작시키기
DELETE FROM p_buy WHERE customid='twice'