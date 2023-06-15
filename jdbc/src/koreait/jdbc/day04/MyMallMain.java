package koreait.jdbc.day04;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyMallMain {

	public static void main(String[] args) {
		System.out.println(":::::: 김땡땡 쇼핑몰에 오신걸 환영합니다. :::::");
		System.out.println("<< 상품 소개 >>");
		JProductDao jProductDao = new JProductDao();
		try {
			List<JProduct> products = jProductDao.selectAll();
			for (JProduct product : products)
				System.out.println(product);

		} catch (SQLException e) {
			System.out.println("상품소개 예외 : " + e.getMessage());
		}

		System.out.println("\n<< 편리한 상품구매를 위해 검색하기 >>");
		Scanner sc = new Scanner(System.in);
		System.out.print("검색어 입력 >>> ");
		String pname = sc.nextLine();
		try {
			List<JProduct> products = jProductDao.searchpname(pname);
			for (JProduct product : products)
				System.out.println(product);

		} catch (SQLException e) {
			System.out.println("상품검색 예외 : " + e.getMessage());
		}
		///////
		JCustomerDao cdao = new JCustomerDao();
		JCustomer customer = null;
		boolean isLogin = false;
		String customid = null;
		System.out.println("\n<< 장바구니 담기와  상품 구매를 위해 로그인 하기(필수) >>");
		while (!isLogin) {
			System.out.println("<< 상품 구매를 위해 로그인 하기 >>");
			System.out.println("간편 로그인 - 사용자 id입력 (로그인 취소는 0000) >> ");
			customid = sc.nextLine();
			if (customid.equals("0000"))
				break; // 반복 종료
			try {
				customer = cdao.selectById(customid);
				if (customer != null) {
					System.out.println(customer.getName() + "안녕하세요");
					isLogin = true;
				} else {
					System.out.println("존재하지 않는 id입니다.");
				}
			} catch (SQLException e) {
				System.out.println("간편 로그인 예외 : " + e.getMessage());
			}
		}

		///// 장바구니 담기는 로그인 상태인 경우만 실행하기
		// 4. 상품 장바구니 담기 - 장바구니 테이블이 없으므로 구매를 원하는 상품을 main에서 List 로 담기
		JBuyDao bdao = new JBuyDao();
		MyPageBuyDao mdao = new MyPageBuyDao();
		List<JBuy> carts = new ArrayList<>();
		if (isLogin) {
			while (true) {
				System.out.println("\n장바구니에 담기 합니다. 그만하려면 상품코드 0000 입력하세요.");
				System.out.println("구매할 상품 코드 입력하세요. >>> ");
				String pcode = sc.nextLine();
				if (pcode.equals("0000"))
					break;

				System.out.println("구매할 수량을 입력하세요. >>>");
				int quantity = Integer.parseInt(sc.nextLine());
				carts.add(new JBuy(0, customid, pcode, quantity, null));

				System.out.println("장바구니에 담긴 상품을 결제하시겠습까? (y/Y)");
				if (sc.nextLine().toLowerCase().equals("y"))
					break;
			}
			System.out.println("장바구니 확인 : " + carts);
			// dao에서 carts 를 전달받아 list 만큼 반복하는 insert 실행하기
			int count = bdao.insertMany(carts); // 구매 테이블에 저장하기
			if (count != 0)
				System.out.println("\n결제를 완료했습니다.");
			// 6. 마이페이지- 구매 내역 보기. 총 구매 금액을 출력해 주기
			try {
				System.out.println("\n" + "현재까지 " + customer.getName() + " 회원님의 구매 내역 입니다.");
				List<MyPageBuy> buys;
				buys = mdao.mypageBuy(customid);
				System.out.println("구매하신 목록은 " + buys + "입니다.");
				long total = mdao.myMoney(customid);
				System.out.println("총 구매 금액은 " + total + "입니다");
			} catch (SQLException e) {
				System.out.println("오류 메시지 " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println("로그인을 취소했습니다. 프로그램 종료합니다.");
		}
		sc.close();
	}
}
