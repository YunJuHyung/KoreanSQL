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
				+ "where custom_id=?";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, customid);
	
		
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
