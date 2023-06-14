package koreait.jdbc.day04;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MyMallMain {

	public static void main(String[] args) {
		System.out.println(":::::김땡땡 쇼핑몰에 오신걸 환영합니다.:::::");
		System.out.println("상 품 소 개");
		JProductDao jProductDao = new JProductDao();
		try {
			List<JProduct> products = jProductDao.productAll();
			for(JProduct product : products)
				System.out.println(product);
		}catch(Exception e) {
			System.out.println("상품소개 예외 :" + e.getMessage());	
		}
		
		
		System.out.println("\n<< 편리한 상품구매를 위해 검색하기 >>");
		Scanner sc = new Scanner(System.in);
		String pname = sc.nextLine();
		try {
			List<JProduct> products = jProductDao.searchpname(pname);
				for(JProduct product : products)
					System.out.println(product);			
			}catch(SQLException e) {
				System.out.println("상품소개 예외 :" + e.getMessage());
			}
		}
	}
