package koreait.jdbc.day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentSelectOneMenuComplete {
	public static void main(String[] args) {
		Connection conn = OracleUtility.getConnection();
		System.out.println("::::::::학생을 학번으로 조회하는 메뉴 ::::::::");
		StudentSelectAllMenu(conn);

		OracleUtility.close(conn);

	}

	private static void selectOneStudent(Connection conn) {
		Scanner sc = new Scanner(System.in);
		String sql = "select * from TBL_STUDENT where stuno = ?";
		System.out.println("조회할 학번 입력 >>>>");
		String stuno = sc.nextLine();
		try (PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, stuno);

			ResultSet rs = ps.executeQuery();
			System.out.println("rs 객체의 구현 클래스는" + rs.getClass().getName());

			if (rs.next()) { // 주의 : 테이블 컬럼의 구조를 알아야 인덱스를 정할 수 있습니다
				System.out.println("학번 : " + rs.getString("stuno")); // 인덱스 대신 컬럼명으로 함.
				System.out.println("이름 : " + rs.getString("name"));
				System.out.println("나이 : " + rs.getInt("age"));
				System.out.println("주소 : " + rs.getString("address"));
			} else {
				System.out.println("<<조회 결과가 없습니다.>>");
			}

		} catch (SQLException e) {
			System.out.println("데이터 조회에 문제가 생겼습니다." + e.getMessage());
		}
		sc.close();
	}

	private static void StudentSelectAllMenu(Connection conn) {
		Scanner sc = new Scanner(System.in);
		String sql = "select * from TBL_STUDENT";
		int count = 0;
		try (PreparedStatement ps = conn.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count++;
				System.out.printf("학번 : " + rs.getString(1));
				System.out.printf("\t이름 : " + rs.getString(2));
				System.out.printf("\t나이 : " + rs.getInt(3));
				System.out.printf("\t주소 : " + rs.getString(4));
				System.out.println("\t몇번째 학생인가요?" + count);

			}
		} catch (SQLException e) {
			System.out.println("데이터 조회에 문제가 생겼습니다." + e.getMessage());
		}
		sc.close();
	}

private static void ScoreSelectWithSubject(Connection conn) {
	Scanner sc = new Scanner(System.in);
	String sql = "select * from TBL_SCORE where subject = ? and jumsu > ?";
	System.out.println("조회할 과목 입력 >>>>");
	String subject = sc.nextLine();
	System.out.println("몇 점 초과로 조회할까요? >>>>");
	int jumsu = sc.nextInt();
	
	try (
			PreparedStatement ps = conn.prepareStatement(sql);
		){
		ps.setString(1, subject);
		ps.setInt(2, jumsu);
		int count = 0;
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			count++;
			System.out.printf("학번 : " + rs.getString(1));
			System.out.printf("\t과목 : " + rs.getString(2));
			System.out.printf("\t점수 : " + rs.getInt(3));
			System.out.printf("\t강사 : " + rs.getString(4));
			System.out.printf("\t기간 : " + rs.getString(5)+"\n");
			
		}
		System.out.println("몇명이나 있나요?" + count + "명 있네요");
	}catch(SQLException e) {
		System.out.println(e);
		System.out.println("데이터 조회에 문제가 생겼습니다." + e.getMessage());
	}
	sc.close();
		
		
	
}
}
/*
 * 모든 학생 조회하는 StudentSelectAllMenu 클래스 : 1줄에 1개 행을 출력하세요. 과목명을 입력하면 해당 과목을 조회하는
 * ScoreSelectWithSubject 클래스
 */
