package koreait.jdbc.day04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Calendar;
//import java.util.Date;

import koreait.jdbc.day02.OracleUtility;


public class JCustomerDao {

	public JCustomer selectById (String customid) throws SQLException{		
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from j_custom \n"
				+ "where custom_id=?";		//pk조회 : 결과 행 0 또는 1개
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, customid);
		//Statement : db와 연결되어있는 connection 객체를 통해 sql문을 db에 전달하여 실행하고
		//	ㄴ 결과를 리턴받아주는 객체, 
		//prepared : SQL 이 미리 컴파일되어 준비된
		//ResultSet : SELECT문의 결과를 저장하는 객체
		//executeQuery : 받아온 쿼리문을 실행, SQL문을 실행한 다음 레코드셋을 반환하는 경우
		ResultSet rs = ps.executeQuery();
		JCustomer result = null;
		if(rs.next()) {
			result = new JCustomer(rs.getString(1), 
					rs.getString(2), 
					rs.getString(3), 
					rs.getInt(4), 
					rs.getDate(5));
		}
		ps.close();
		connection.close();
		return result;
	}
}
