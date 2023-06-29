package partD.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


//바이트 기반 스트림  //
//예시 4: 입력장치- 파일 => 프로그램 =>  출력장치-콘솔  +---+ byte[] 바이트 배열 사용 하기 전후 실행 시간 비교
public class D04FileInputStreamBoth {

    public static void main(String[] args) throws IOException {
        InputStream fin = new FileInputStream("C:/temp/AhnInst.log");

        int rByte;

        System.out.println("[파일을 바이트 단위로 읽어서 System.out 에 출력]\n\n");
        long cnt1=0;
        long start = System.nanoTime();
        while(true) {
            rByte = fin.read();   //읽은 바이트값  0~255 리턴

            if(rByte == -1) break;		//the next byte of data, or -1 if the end of thestream is reached.
            cnt1++;
            System.out.write(rByte);           
        }
        long worktime = System.nanoTime()-start;
        fin.close();
        
        
        System.out.println("[파일을 바이트buf(배열)크기로 읽어서 System.out 에 출력]\n\n");
        fin = new FileInputStream("C:/temp/AhnInst.log");  //긴 파일로 바꾸기

        byte[] buf = new byte[10];      //byte[] buf = new byte[fis.available()];   //현재 파일 포인터 위치에서 읽을 수 있는 바이트 수 반환
        int bcnt=0;			//실제로 읽은 바이트 수. 마지막에는 10바이트 보다 적은 데이터를 읽을수 있다.
        long cnt2=0;		//
        start = System.nanoTime();

        while(true) {
            bcnt=fin.read(buf);         //fis.read(buf,0,buf.length)
          //  System.out.write(buf);
            if(bcnt==-1) break;
            cnt2+=bcnt;
            //data+=new String(buf,0,bcnt);
        }
        System.out.println("\n첫 번째 => "+ cnt1 + "바이트 읽음.");
        System.out.println(" *실행시간 : " + worktime +"ns");

        System.out.println("\n두 번째 => "+ cnt2 + "바이트 읽음.");
        System.out.println("**실행시간 : " + (System.nanoTime()-start) +"ns");

        fin.close();

    }
}
