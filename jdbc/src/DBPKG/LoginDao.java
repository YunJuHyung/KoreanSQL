package DBPKG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import koreait.jdbc.day02.OracleUtility;
import koreait.jdbc.day04.JBuy;
import koreait.jdbc.day04.JCustomer;

public class LoginDao {

	public int LoginDto createId (List<LoginDto> custno) throws SQLException{		
		Connection connection = HRD_0616.getConnection();
		String sql = "insert into member_tbl_02 \n"
				+ "(jbuy_seq.nextval,?,?,?,sysdate,?,?)";		//pk조회 : 결과 행 0 또는 1개
		
		PreparedStatement ps = connection.prepareStatement(sql); //********06-16 여기서 부터 수정해야됨
		ResultSet rs = ps.executeQuery();
		LoginDto result = null;
		int count = 0;
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);	//auto commit 설정 - false
			ps = connection.prepareStatement(sql);
			for(JBuy b : carts) {
				ps.setString(1, b.getCustomid());
				ps.setString(2, b.getPcode());
				ps.setInt(3, b.getQuantity());
				count += ps.executeUpdate();
			}
			connection.commit(); //커밋		
		}catch(SQLException e) {
			System.out.println("장바구니 상품 구매하기 예외 :" + e.getMessage());
			try {
				connection.rollback();
			}catch(SQLException e1) {
			}
		}
		return count;
		}
		ps.close();
		connection.close();
		return result;
	}
}
