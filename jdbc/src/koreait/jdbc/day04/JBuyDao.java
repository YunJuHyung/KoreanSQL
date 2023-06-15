package koreait.jdbc.day04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import koreait.jdbc.day02.OracleUtility;

public class JBuyDao {		//구매와 관련된 CRUD실행 SQL. DAO:JCustomerDao, JProducDao
	//메소드 이름은 insert,update,delete,select,selectByPname 등등으로 이름을 작성하세요
	public int insertMany(List<JBuy> carts) {		
		Connection connection = OracleUtility.getConnection();
	//5. 상품 구매(결제)하기 - 장바구니의 데이터를 `구매` 테이블에 입력하기 (여러개 insert)
		String sql = "insert into j_buy \n"
				+ " values(jbuy_seq.nextval,?,?,?,sysdate)";
		int count = 0;
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			for(JBuy b : carts) {
				ps.setString(1, b.getCustomid());
				ps.setString(2, b.getPcode());
				ps.setInt(3, b.getQuantity());
				count += ps.executeUpdate();
			}
			connection.close();
		}catch(SQLException e) {
			System.out.println("장바구니 상품 구매하기 예외 :" + e.getMessage());
			try {
				connection.rollback();
			}catch(SQLException e1) {
			}
		}
		return count;
	}	
}
