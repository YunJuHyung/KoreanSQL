package jdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import com.google.common.hash.Hashing;

import koreait.jdbc.day04.JCustomer;
import koreait.jdbc.day06.HashFunctionTest;
import koreait.jdbc.day06.JCustomerDao2;

class LoginMain {
	// LoginMain 에서 사용자에게 아이디 >>>, 패스워드>>>>
	// 로그인 처리 결과 '로그인 성공했습니다.xxx 님 환영합니다.'
	// `또는 `입력한 계정정보가 틀립니다.'출력

	// 반복문을 통해서 5이상 틀릴시 break;

	boolean isLogin = false;
	JCustomerDao2 jcd = JCustomerDao2.getJCustomerDao2();
	HashFunctionTest hsh = new HashFunctionTest();
	JCustomer Logindata = null;
	String password = null;
	@Test
	public void data() { 
	while(!isLogin)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("아이디 입력:");
		String id = sc.nextLine();

		System.out.println("비밀번호 입력:");
	//	String password = sc.nextLine();
		
		password = Hashing.sha256()					//적용할 해시함수 실행
				.hashString("1234", StandardCharsets.UTF_8)	//평문, 인코딩 형식
				.toString();
		try {
			Logindata = jcd.login(id, password);
			if (Logindata != null) {
				System.out.println("로그인 성공" + Logindata.getName() + "님 안녕하세요");
				isLogin = true;
			} else {
				System.out.println("로그인 실패");
			}
		} catch (SQLException e) {
			System.out.println("오류메세지" + e.getMessage());
		}
	}
}
}
