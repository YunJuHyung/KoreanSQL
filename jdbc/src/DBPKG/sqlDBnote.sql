create table member_tbl_02(
	custno number(6) primary key,
	custname varchar2(20),
	phone varchar2(13), --하이픈 구별때매 그냥 varchar
	address varchar2(60),
	joindate date,
	grade char(1),
	city char(2)
);
	select * from member_tbl_02;
	drop table member_tbl_02;
insert into member_tbl_02 values(100001,'김행복','010-1111-2222','서울 동대문구 휘경1동',to_date('2022-12-02', 'yyyy-mm-dd'),'A','01');
insert into member_tbl_02 values(100002, '이축복', '010-1111-3333' ,'서울 동대문구 휘경2동',to_date('2015-12-06', 'yyyy-mm-dd'), 'B' ,'01');
insert into member_tbl_02 values(100003, '장믿음', '010-1111-4444', '울릉군 울릉읍 독도1리',to_date('2015-10-01', 'yyyy-mm-dd'), 'B', '30');
insert into member_tbl_02 values(100004, '최사랑', '010-1111-5555', '울릉군 울릉읍 독도2리',to_date('2015-11-13', 'yyyy-mm-dd') , 'A', '30');
insert into member_tbl_02 values(100005, '진평화', '010-1111-6666', '제주도 제주시 외나무골',to_date('2015-12-25', 'yyyy-mm-dd') , 'B', '60');
insert into member_tbl_02 values(100006, '차공단', '010-1111-7777', '제주도 제주시 감나무골',to_date('2015-12-11', 'yyyy-mm-dd') , 'C', '60' );

create table money_tbl_02(
	custno number(6)not null,
	salenol  number(8)not null,
	pcost  number(8),
	amount  number(4),
	price  number(8),
	pcode varchar2(4),
	sdate  date,
	primary key(custno,salenol),
	FOREIGN KEY (custno)				-- 이 테이블의 외래키 컬럼
			REFERENCES member_tbl_02(custno)	-- 참조하는 테이블과 컬럼명
	);
	
	select * from money_tbl_02;
	drop table money_tbl_02;
insert into money_tbl_02 values(100001, 20160001, 500, 5, 2500, 'A001', to_date('2016-01-01', 'yyyy-mm-dd'));
insert into money_tbl_02 values(100001, 20160002, 1000, 4, 4000, 'A002', to_date('2016-01-01', 'yyyy-mm-dd'));
insert into money_tbl_02 values(100001, 20160003, 500, 3, 1500, 'A008', to_date('2016-01-01', 'yyyy-mm-dd'));
insert into money_tbl_02 values(100002, 20160004, 2000, 1, 2000, 'A004', to_date('2016-01-02', 'yyyy-mm-dd'));
insert into money_tbl_02 values(100002, 20160005, 500, 1, 500, 'A001', to_date('2016-01-03', 'yyyy-mm-dd'));
insert into money_tbl_02 values(100003, 20160006, 1500, 2, 3000, 'A003', to_date('2016-01-03', 'yyyy-mm-dd'));
insert into money_tbl_02 values(100004, 20160007, 500, 2, 1000, 'A001', to_date('2016-01-04', 'yyyy-mm-dd'));
insert into money_tbl_02 values(100004, 20160008, 300, 1, 300, 'A005', to_date('2016-01-04', 'yyyy-mm-dd'));
insert into money_tbl_02 values(100004, 20160009, 600, 1, 600, 'A006', to_date('2016-01-04', 'yyyy-mm-dd'));
insert into money_tbl_02 values(100004, 20160010, 3000, 1, 3000, 'A007', to_date('2016-01-06', 'yyyy-mm-dd'));
	
	
	
	