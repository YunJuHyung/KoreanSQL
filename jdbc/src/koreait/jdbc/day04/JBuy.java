package koreait.jdbc.day04;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class JBuy {
	private int buy_seq;
	private String customid;
	private String pcode;
	private int  quantity;
	private String buy_date;
}
