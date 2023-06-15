package koreait.jdbc.day04;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class MyPageBuy {
	private Date buy_date;
	private String customid;
	private String pcode;
	private String pname;
	private int  quantity;
	private int price;
	private long total;
	
	//select * from mypage_buy where customid = 'twice';
	public List<MyPageBuy> mypageBuy(String customid){
		return null;
	}
	//select sum(total) from mypage_buy where customid='twice';
	public long myMoney(String customid) {
		return 0;
	}
}
