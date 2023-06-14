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
public class JCustomer {
//DTO : JBuy,JProduct
	private String custom_id;
	private String name;
	private String email;
	private int age;
	private String reg_date;
	
}
