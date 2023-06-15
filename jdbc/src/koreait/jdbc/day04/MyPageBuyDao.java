package koreait.jdbc.day04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day02.OracleUtility;

public class MyPageBuyDao {
	//산 물품들을 리스트로 정리하기
	public List<MyPageBuy> mypageBuy(String customid) throws SQLException{
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from mypage_buy where customid = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, customid);
		ResultSet rs = ps.executeQuery();
		
		List<MyPageBuy> list = new ArrayList<>();
		while(rs.next()) {
			list.add(new MyPageBuy(rs.getDate(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getInt(5),
					rs.getInt(6),
					rs.getLong(7)));
		}
		ps.close();
		connection.close();
		return list;
	}
	//얼마치를 샀는지 조회하는 메소드
	public long myMoney (String customid) throws SQLException{
		
		Connection connection = OracleUtility.getConnection();
		String sql = "select sum(total) from mypage_buy where customid=? ";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, customid);
		
		ResultSet rs = ps.executeQuery();
		long sum = 0;
		if(rs.next()) {
			 sum = rs.getLong(1);
	    }
	    rs.close();
	    ps.close();
	    connection.close();
	    return sum;	
	}
}
