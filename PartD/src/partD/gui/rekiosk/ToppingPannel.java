package partD.gui.rekiosk;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ToppingPannel {
	
	private JPanel panel_Topping = new JPanel();
	public JPanel getPanel_Topping() {
		return panel_Topping;
	}
	//접근해야하는 다른 패널 private 으로 선언
	private JPanel panel_Menu;
	
	//다른 패널에서 접근하는 컴포넌트 또는 변수 선언 => 전연변수 선언
	String[] side = { "치즈추가", "햄추가" };
	int[] side_cost = { 500, 700 };
	int selected;
	
	
	int side_total = 0;				//토핑 추가 가격 합계
	int c_count = 0;				//치즈 추가 갯수
	int h_count = 0;				//햄 추가 갯수

	//다른 패널에서 접근해야 하는 메뉴 패널의 컴포넌트 선언 => 전역변수 선언
	int[] toppingC = new int[6];
	int[] toppingH = new int[6];


	JLabel lbltotalT = new JLabel("0원");
	JLabel lblTitle = new JLabel("토핑 추가 옵션");
	JSpinner spinner_corn = new JSpinner();
	JSpinner spinner_ham = new JSpinner();
	
	private String priceLabel(int num) { // 출력되는 숫자 3개마다 ,로 나뉘어 출력되도록 설정.
		DecimalFormat dc = new DecimalFormat("#,###,###,###");
		String d = dc.format(num);
		return d + "원";
	}
	
	public JPanel createPanel(MenuPanel pmenu, CartPanel pcart ) {
		//다른 패널 객체 인자로 받아 초기화
		this.panel_Menu = pmenu.getPanel_Menu();

		lblTitle.setFont(new Font("굴림", Font.PLAIN, 16));
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setBounds(154, 10, 250, 47);
		panel_Topping.add(lblTitle);
		
		//추가 메뉴 항목이 많으면 아래 2가지 레이블 추가도 for문으로 바꿀 수 있습니다.
		JLabel lblAddCorn = new JLabel("치즈 추가");
		lblAddCorn.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddCorn.setBounds(73, 88, 125, 47);
		panel_Topping.add(lblAddCorn);

		JLabel lblAddPot = new JLabel("햄 추가");
		lblAddPot.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddPot.setBounds(73, 195, 125, 22);
		panel_Topping.add(lblAddPot);

		//추가 메뉴 항목이 많으면 아래 2가지 스피너 추가도 for문으로 바꿀 수 있습니다.
	
		spinner_corn.setModel(new SpinnerNumberModel(0,0,5,1));
		spinner_corn.setBounds(269, 95, 84, 47);
		spinner_corn.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) { // 스피너 값 증가/감소 시 결제 전 화면에서 해당 메뉴가 추가될 수 있도록 설정.
				
				c_count = (int) spinner_corn.getValue();
				toppingC[selected] = c_count;
				side_total = c_count*side_cost[0] + h_count*side_cost[1];
				lbltotalT.setText(priceLabel(side_total));
			}

		});
		panel_Topping.add(spinner_corn);

		
		spinner_ham.setModel(new SpinnerNumberModel(0,0,5,1));
		spinner_ham.setBounds(269, 195, 84, 47);
		spinner_ham.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				
				h_count =(int) spinner_ham.getValue();
				toppingH[selected] = h_count;
				side_total = c_count*side_cost[0] + h_count*side_cost[1];
				lbltotalT.setText(priceLabel(side_total));

			}

		});

		panel_Topping.add(spinner_ham);

		JLabel lblSubTitle = new JLabel("추가된 금액");
		lblSubTitle.setFont(new Font("굴림", Font.BOLD, 12));
		lblSubTitle.setBounds(114, 307, 84, 22);
		panel_Topping.add(lblSubTitle);


		JLabel lblPrice1 = new JLabel("700원");
		lblPrice1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice1.setBounds(73, 220, 125, 15);
		panel_Topping.add(lblPrice1);

		JLabel lblPrice2 = new JLabel("500원");
		lblPrice2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice2.setBounds(73, 121, 125, 15);
		panel_Topping.add(lblPrice2);

		lbltotalT.setFont(new Font("굴림", Font.BOLD, 12));
		lbltotalT.setHorizontalAlignment(SwingConstants.RIGHT);
		lbltotalT.setBounds(272, 307, 81, 28);
		panel_Topping.add(lbltotalT);

		/////////// 토핑 추가 패널의 실행버튼 컴포넌트 - 선택
		JButton btnChoose = new JButton("선택");
		btnChoose.setBounds(284, 374, 113, 41);
		panel_Topping.add(btnChoose);
		btnChoose.addActionListener(new ActionListener() { // 선택 버튼 추가
			@Override
			public void actionPerformed(ActionEvent e) { // 토핑 선택화면에서 선택 버튼 누르면 다시 메인메뉴 화면으로 돌아가도록 설정.
				panel_Topping.setVisible(false);
				panel_Menu.setVisible(true);
			}
		});

		return panel_Topping;
	
	}

}
