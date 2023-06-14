package koreait.jdbc.day04;

import java.sql.Connection;
import java.sql.SQLException;

import koreait.jdbc.day02.OracleUtility;

public class JBuyDao {		//구매와 관련된 CRUD실행 SQL. DAO:JCustomerDao, JProducDao
	//메소드 이름은 insert,update,delete,select,selectByPname 등등으로 이름을 작성하세요
	public int insert(JBuy jbuy) throws SQLException() {
		
		Connection connection = OracleUtility.getConnection();
		
		String sql = "insert into TBL_BUY values(?,?,?,?"
	}
}
