--프로시저 복습하기 위한 테이블. tbl_buy 복사
CREATE TABLE p_buy
AS
SELECT * FROM TBL_BUY tb ;

SELECT * FROM p_buy;

--웹애플리케이션(인터넷 환경) 개발할 때, 사용자가 원하는 기능 요청 하나에 sql을 1개씩만 실행을 합니다.
--			프로시저를 이용하면 요청 1 : n의 SQL을 실행할수 있다
--데이터베이스 관점에서 `무결성`(정확성)을 유지할 수 있는 방법.
--프로시저에서도 트랜잭션을 관리

CREATE OR REPLACE PROCEDURE ICLASS.proc_set_money(
	acustom_id IN varchar2, -- 회원 ID  --입력 매개변수 IN
	apcode IN varchar2,		--상품코드
	acnt IN NUMBER ,		--수량
	isSuccess OUT varchar2	--출력 매개변수 OUT, 트랜잭션 처리 성공여부 저장
)
IS
	vseq NUMBER; --변수선언
	vprice NUMBER;

BEGIN
	INSERT INTO p_buy(buy_seq,customid,pcode,quantity,buy_date)
		VALUES (pbuy_seq.nextval, acustom_id,apcode,acnt,sysdate);
	SELECT pbuy_seq.currval
		INTO vseq
		FROM dual;
	SELECT price 
	INTO vprice
		from TBL_PRODUCT tp WHERE pcode=apcode;		--상품코드에 대한 가격 조회
	UPDATE p_buy SET money = vprice * quantity		-- 위 INSERT 한 데이터로
		WHERE buy_seq = vseq;						--가격*수량 수식 구해서 MONEY 컬럼값 수정
	dbms_output.put_line('실행 성공');
	isSuccess := 'success';		--프로시저 일반변수 대입문 기호 :=
	COMMIT;
	EXCEPTION
		WHEN OTHERS THEN
		dbms_output.put_line('실행 실패');
		ROLLBACK;
		isSuccess :='fail';
END;

--실행을 위해서 시퀀스 생성,money 컬럼 추가
CREATE SEQUENCE pbuy_seq START WITH 1008;
ALTER TABLE p_buy ADD money varchar2(7);



--롤백 시나리오 만들기
ALTER TABLE p_buy drop COLUMN money;
ALTER TABLE P_BUY ADD money number(7) CHECK (money>= 10000);

--실행 예시
DECLARE
    vresult varchar2(20);
BEGIN
    proc_set_money('twice','3MCRY',3,vresult);-- 프로시저에 올바른 인수를 전달해야 합니다.
--메시지는 'fail' 이고 p_buy 테이블 insert 도 입력값 없어야합니다.
    dbms_output.put_line('결과: ' || vresult);
END;

