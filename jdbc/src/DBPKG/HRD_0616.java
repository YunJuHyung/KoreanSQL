package DBPKG;

import java.sql.Connection;
import java.sql.DriverManager;

public class HRD_0616 {

	public static void main(String[] args) {
		
		String url ="jdbc:oracle:thin:@//localhost:1521/xe";
		String user = "system";
		String password = "1234";
		
		
		try (
				//Class.forName("oracle.jdbc.OracleDriver");
				Connection conn = DriverManager.getConnection(url,user,password)
				){
			System.out.println("연결 상태 =" + conn);
			if(conn!=null) {
				System.out.println("오라클 데이터베이스 연결 성공!!");
			}
			else
				System.out.println("오라클 데이터베이스 연결 실패!!");
		}catch(Exception e) {
			System.out.println("ClassNotFoundExceptoin = 드라이버 경로가 잘못됐습니다.");
			System.out.println("SQLException = url 또는 user 또는 패스워드가 잘못됐습니다");
			System.out.println("오류 메시지 풀" + e.getMessage());
			e.printStackTrace();	//Exception 발생의 모든 원일을 cascade 형식으로 출력
		}
		
		
	}
	public static Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection
		 ("jdbc:oracle:thin:@//localhost:1521/xe","system","1234");
		return con;
		}
}