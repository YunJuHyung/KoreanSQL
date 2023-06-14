package koreait.jdbc.day04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
//import java.util.Calendar;
//import java.util.Date;

import koreait.jdbc.day02.OracleUtility;
import koreait.jdbc.day03.StudentDto;

public class JCustomerDao {

	public JCustomer selectById (JCustomer jcustomer) throws SQLException{		
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from TBL_CUSTOMER"
				+ "where custom_id=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, custom_id); //이름만 같으면 되는 줄알았는데 왜 안되는거지?
	
		
		ResultSet rs = ps.executeQuery();
		JCustomer result = null;
		if(rs.next()) {
			String name = rs.getString(2);
			String email = rs.getString(3);
			int age = rs.getInt(4);
			String reg_date = rs.getString(5);
			result = new JCustomer(custom_id, name, email, age, reg_date);
		}
		return result;
	}
}
