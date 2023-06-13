package koreait.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
// 학생 성적처리 프로그램 table 중에 새로운 학생을 등록(입력) 하는 기능을 만들어 봅시다.
//(테이블에 insert 실행)
public class InsertDMLUsingParameter {

	public static void main(String[] args) {

		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "iclass";
		String password = "0419";
		
		try(
				Connection conn = DriverManager.getConnection(url,user,password);
			){
			System.out.println("연결 상태 =" + conn);
			if(conn!=null) {
				System.out.println("오라클 데이터베이스 연결 성공!!");
			}
			else
				System.out.println("오라클 데이터베이스 연결 실패!!");
			//db연결 완료 후에 sql 실행하기.
			
			
			//insert SQL 작성 : 실행되는 값이 되도록 매개변수로 처리합니다.매개변수 기호는 ? 입니다.
			String sql ="insert into TBL_STUDENT values (?,?,?,?)";		//insert SQL 작성
			
			
			//PreparedStatement 객체를 생성하면서 실행할 Sql을 설정합니다.
			//PreparedStatement 객체는 Connection 객체 메소드로 만듭니다.Connection 구현객체는 dbms 종류에 따라
			//생성되고 PreparedStatement 객체도 그에 따라 구현 객체가 결정됩니다.
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			
			
			//PreparedStatement() 메소드는 객체를 생성`해서 리턴합니다.
			
			//매개변수에 값 전달하기 : 매개변수의 데이터형식 순서 문자열,문자열,정수,문자열
			//setXXXX 메소드의 첫번째 인자는 매개변수의 인덱스, 두번째 인자는 값입니다.XXXX는 매개변수의 타입
			pstmt.setString(1, "2023008"); //1번 인덱스에 "2023003" 대입하기
			pstmt.setString(2,"김땡사");
			pstmt.setInt(3,17);
			pstmt.setString(4,"강원도");
		
			
			
			pstmt.execute();
			pstmt.close();
			
			//System.out.println("정상적으로 입력이 되었습니다.");
			
		}catch(Exception e) {
			System.out.println("ClassNotFoundExceptoin = 드라이버 경로가 잘못됐습니다.");
			System.out.println("SQLException = url 또는 user 또는 패스워드가 잘못됐습니다");
			System.out.println("오류 메시지 풀" + e.getMessage());
			e.printStackTrace();	//Exception 발생의 모든 원일을 cascade 형식으로 출력
		}
	}
}
/*
 * Statement 인터페이스는 sql 쿼리 처리와 과련된 방법을 정의합니다.
 * Statement 인터페이스의 객체는 SQL 쿼리문을 데이터베이스에 전송합니다. Connection 객체를 통해서 만듭니다.
 * 
 * PreparedStatement는 Statement의 자식 인터페이스.
 * 특징은 sql을 먼저 컴파일하고 sql 실행에 필요한 값은 실행할 때 매개변수로 전달하는 방식입니다.
 * 
 * 
 * 
 * */
 
 
 
 
 
 
 