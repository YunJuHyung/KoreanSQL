package partD.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

//FileOutputStream 클래스는 출력스트림 OutputStream 자식 클래스 입니다.
//	바이트 기반 스트림
//예시 2: 입력장치 -콘솔 => 프로그램 => 출력장치-파일
public class D02FileOutputStream {

	public static void main(String[] args) {
		int count = 0;
		int b = 0;
		System.out.println("파일에 저장할 내용을 입력하세요.");
		OutputStream fo=null;
		try {
			fo = new FileOutputStream("d:/temp/do2.txt");
			while((b = System.in.read()) != -1) {
				count++;
				fo.write(b);
			}
		}catch(IOException ex) {
					System.err.println("입출력오류 : " + ex.getMessage());
		}/*finally {
			if(fo != null) {
				try {
					fo.close();		//파일 출력장치에 대한 close 꼭 필요.
				}catch (IOException e) {
				}
			}
		}*/
		System.out.println(count + "바이트 파일에 저장.");	
		}	
	
}
