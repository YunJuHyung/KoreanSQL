package koreait.jdbc.day04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import koreait.jdbc.day02.OracleUtility;



public class JProductDao {
	//2. 상품 목록 보기
		public List<JProduct> selectAll() throws SQLException{
			Connection conn = OracleUtility.getConnection();
			String sql = "select * from j_product";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			List<JProduct> list = new ArrayList<>();
			while(rs.next()) {
				list.add(new JProduct(rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getInt(4)));
			}
			ps.close();
			conn.close();
			return list;
		}
	//3. 상품명으로 검색하기 (유사검색) 시험나옴
	public List<JProduct> searchpname(String pname)throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from TBL_product where pname like '%' || ? || '%'";
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
