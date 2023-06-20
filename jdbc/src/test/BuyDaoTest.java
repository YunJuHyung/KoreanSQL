package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import koreait.jdbc.day04.JBuy;
import koreait.jdbc.day04.JBuyDao;
//테스트 케이스 입니다.
//테스트 할 메소드 앞에는 @Test 애노테이션을 작성하기.
//테스트 결과는 성공 또는 실패 입니다.
class BuyDaoTest {

	private JBuyDao dao = new JBuyDao();
	
	@DisplayName("buy 테이블 insert 성공하면 리턴값은 1이 되어야합니다.")
	@Test
	void insertTest() {
		JBuy buy = JBuy.builder()
				.customid("Kim")
				.pcode("")
				.quantity(5).build();
		int i = dao.insert(buy);
		
		//성공 또는 실패 결과를 확인하는 테스트 메소드 실행하기
		assertEquals(1, i);		//기대값,실제값
	}
	
	
	@Test
	void selectOneTset() throws SQLException{
		JBuy buy = dao.selectOne(1);
		print();
		assertNotNull(buy);
	}
	
	
	private void print() {
	System.out.println("test");
	}
}
