package koreait.jdbc.day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentInertMenu {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
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
			
			String sql = "insert into TBl_Student values(?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			while(true) {
				try {
						System.out.println(":::::::: 학생 등록 메뉴 입니다.::::::::");
						System.out.println("시작은 1번 입력");
						System.out.println("학생번호 입력시 0000 입력은 종료입니다.");
						String input = sc.nextLine();
						if(input.equals("0000")) {
							System.out.println("종료됨");
							break;
							}
					System.out.println("학번 입력");
					String stuno = sc.nextLine();
					pstmt.setString(1,stuno);
					System.out.println("이름 입력");
					String name = sc.nextLine();
					pstmt.setString(2,name);
					System.out.println("나이 입력");
					String stage = sc.nextLine();
					int age = Integer.parseInt(stage);
					pstmt.setInt(3,age);
					System.out.println("주소 입력");
					String address = sc.nextLine();
					pstmt.setString(4,address);
					pstmt.execute();		//실행은 저절로 되는게 아니라 만들어진 pstmt 를 실행하는 execute() 사용함
				}catch (SQLException e) {
					System.out.println(e.getMessage());
			        System.out.println("삽입값이 중복됩니다.");
				}
				//반복적으로 실행되는 코드 continue이후에 읽음
				
		}//while end
			
			pstmt.close();
		}catch(Exception e) {
			System.out.println("ClassNotFoundExceptoin = 드라이버 경로가 잘못됐습니다.");
			System.out.println("SQLException = url 또는 user 또는 패스워드가 잘못됐습니다");
			System.out.println("오류 메시지 풀" + e.getMessage());
			e.printStackTrace();	//Exception 발생의 모든 원일을 cascade 형식으로 출력
		}//
	}
}
