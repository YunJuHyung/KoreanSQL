package koreait.jdbc.day04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import koreait.jdbc.day02.OracleUtility;
import koreait.jdbc.day03.StudentDto;


public class JProductDao {
	public JProduct productAll(String pcode) throws SQLException{
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from TBL_PRODUCT";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, pcode);
		
		ResultSet rs = ps.executeQuery();
		JProduct result = null;
		if(rs.next()) {
			String category = rs.getString(2);
			String pname = rs.getString(3);
			int price = rs.getInt(4);
			result = new JProduct(pcode, category, pname, price);
		}
		
		return result;
	}
	//3. 상품명으로 검색하기 (유사검색) 시험나옴
	public List<JProduct> searchpname(String pname)throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from TBL_product where pname like '%' || '?' || '%'";
		//스캐너로 입력 받아야하긴 할듯?
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, pname);
		
		List<JProduct> list = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			list.add(new JProduct(rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getInt(4)));
		}
		return list;
	}

	
}
