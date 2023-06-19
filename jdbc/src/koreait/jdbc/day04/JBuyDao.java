package koreait.jdbc.day04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import koreait.jdbc.day02.OracleUtility;

public class JBuyDao {		//구매와 관련된 CRUD실행 SQL. DAO:JCustomerDao, JProducDao
	//메소드 이름은 insert,update,delete,select,selectByPname 등등으로 이름을 작성하세요
	
	//트랜잭션을 처리하는 예시
	//try catch 를 직접하세요.throws 아닙니다.
	public int insertMany(List<JBuy> carts) {		
		Connection connection = OracleUtility.getConnection();
	//5. 상품 구매(결제)하기 - 장바구니의 데이터를 `구매` 테이블에 입력하기 (여러개 insert)
		String sql = "insert into j_buy \n"
				+ " values(jbuy_seq.nextval,?,?,?,sysdate)";
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

	public int insert(JBuy buy) {
		// TODO Auto-generated method stub
		return 1;
	}

	public JBuy selectOne(int buy_seq) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from j_buy where buy_seq = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, buy_seq);
		ResultSet rs = ps.executeQuery();
		JBuy buy = null;
		if(rs.next()) {
			buy = new JBuy(rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getInt(4),
					rs.getString(5)
					);
		}
		return buy;
	}	
}
