package partD.io;

import java.io.IOException;

//바이트 기반 스트림 // 표준입력 - System.in, 표준 출력 - System.out 이 2개는 콘솔이라고 부르며 기본 입출력 장치입니다.
//예시 1 : 입력장치 - 콘솔 => 프로그램 => 출력장치 -콘솔

public class D01StdIO {
	public static void main(String[] args) {
		System.out.println("저장할 내용을 입력하세요.");
		int count = 0;
		int b = 0;
		try {
		//	while(b = System.in.read() != -1) {
		while(true) {
			b = System.in.read();		//read() 로 입력받은 것을 변수 b에 저장합니다.
			if(b==-1)break;				//b가 -1 이면 입력 종료(EOF:End Of File)
				count++;
		//	System.out.write((char)b);		//b를 출력합니다
				System.out.print((char)b); //1바이트씩만 처리한다. -멀티바이트 문자 인코딩못함
		}
		
		}catch(IOException ex) {
					System.out.println("입출력오류 : " + ex.getMessage());
		}
					System.out.println("===" + count + "바이트 전달.===");	
		}	
	}
