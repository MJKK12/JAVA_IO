package io.day1;

import java.io.IOException;

public class InputStream_main_4 {

	// ★★★main_3 과 달리 배열을 사용함으로써 반복횟수를 줄였음★★★
	// ★결과물은 동일하게 나오지만, 반복 횟수가 줄어들었음.★ (속도 향상)
	// file 로 부터 읽어서 키보드에 뿌릴 때에도, 매번 1byte 씩 읽는게 아니라 배열에 담아서 돌린다..
	/*
	   ※ Data Source (File, 키보드, 원격 컴퓨터)		// 데이터를 어디서 보내느냐!
	   : 데이터의 근원
	    
	   ※ Data Destination (파일, 모니터, 프린터, 메모리)	// 데이터를 받는 곳.
	   : 데이터가 최종적으로 도착하는 곳

	  Data Sourceㅇ======>ㅇ 프로그램 ㅇ======>ㅇ Data Destination
                  입력스트림           출력스트림
	              InputStream       OutputStream          
	   
	    
	  >>>>> System.in :
	         부모클래스가 추상클래스 InputStream(기본 입력 스트림) 타입인 것으로서 
	         접속점(빨대)이 키보드인 입력 스트림이다.
	             
	    -- Node(접속점)가 키보드인 입력스트림이다.
	    -- 1 byte 기반 스트림이다.
	    -- 주요메소드 :
	          public int read() throws IOException
	          ==> 1byte 씩 데이터를 읽어서
	              1byte 씩 반환하고
                  입력받은 키보드가 Ctrl+C(윈도우), Ctrl+D(유닉스,리눅스)		// Ctrl+C = 종료
                  이라면 -1 을 반환해주는 메소드이다.
	             
	              read() 메소드의 리턴타입은 byte 가 아니라 int 이다.
                  데이터 입력의 끝을 나타내는 것으로 -1 을 사용하는데
	              Ctrl+C(윈도우), Ctrl+D(유닉스,리눅스)을 사용하면 된다.
                  
                  또한 IOException 이 발생할수도 있으므로 반드시 예외처리를 꼭 해주어야 한다.              
                  그래서 현재 우리는 윈도우를 사용하고 있으므로 InputStream 작업을 
                  강제로 종료하려면  Ctrl+C(윈도우) 하면 된다.
	    
	    
	  >>>>> System.out :
              부모클래스가 추상클래스인 OutputStream(기본 출력 스트림) 타입인 것으로서
              접속점(빨대)이 콘솔화면(모니터)인 출력 스트림(PrintStream)이다.
	                   
	    -- Node(접속점)가 콘솔화면(모니터)인 출력스트림이다.
	    -- 1byte 기반 스트림이다.
	    -- 주요 메소드 : println(String str),
	                  print(String str),
	                  write(int b)             
	*/   

	public static void main(String[] args) throws IOException {
	
		// 키보드에서 "대한민국서울시홍대입구엔터" 했다면
		// "대한민국서" "울시홍대입" "구엔터"
		//  10byte	  10byte	 4byte
		
		byte[] dataArr = new byte[10];
		// dataArr 변수의 용도는 빨대(System.in, 키보드)에서 흡입할 때, 단위크기를 10byte로 하는 것이다.
		// 또한 dataArr의 용도는 빨대(System.in, 키보드)에서 1번 흡입할 때 마다 흡입한 내용물을 저장하는 용도로 쓰인다.
		
		int inputLength = 0;
		// inputLength 변수의 용도는 빨대(System.in, 키보드)에서 흡입한 실제 크기(길이)를 나타내는 용도임.
		
		int totalByte = 0;	// byte 수 누적용도.
		int cnt = 0;		// while 문의 반복횟수를 알기 위한 것.
		
		while( ( inputLength = System.in.read(dataArr)) != -1 ) {
			// -1 은 키보드로 Ctrl + C(강제종료) 를 입력한 값이다.
			// 키보드로 Ctrl + C(강제종료) 를 하면 while 을 빠져 나간다.
			// System.in.read() 는 1byte 씩 읽어온다.
			
		  /*
            System.in.read(dataArr)은 
            입력한 데이터가 "대한민국서울시홍대입구엔터" 라면
            입력한 내용에서 배열 dataArr의 크기만큼(지금은  10byte) 읽어들인다(지금은 "대한민국서"). 
            읽어들인 "대한민국서"을 배열 dataArr 에 저장시키고, 읽어들인 크기를(지금은 10byte) 리턴시켜준다. 
           
            반복문의 첫번째일때
            dataArr 에는  "대한민국서" 가 들어가고, inputLength 에는 10 이 들어간다.
         
            반복문의 두번째일때
            dataArr 에는  "울시홍대입" 이 들어가고, inputLength 에는 10 이 들어간다.
            
            반복문의 세번째일때
            dataArr 에는  "구엔터" 가 들어가고, inputLength 에는 4 가 들어간다.    
         */
			System.out.write(dataArr, 0, inputLength);	// 글자 그대로 써야하기 때문에, int로 바꾸는 print을 쓰지 않는다.
			System.out.flush();
			// 배열 dataArr 에 저장된 데이터에서 시작점이 0번째 index 부터(처음부터) inputLength byte 수 만큼 출력하라는 말이다.
			// buffer(배열)에 byte가 들어감, off 시작점(0번), len 출력해야할 byte 수)
			// (byte[] buf, int off, int len)
			// 맨마지막 "구엔터" 는 4byte.
			// write 쓰면 flush는 무조건 밑에 써준다..
			
			totalByte += inputLength;	// 10 + 10 + 4 (대한민국서 울시홍대입 구엔터)
			cnt++;
		}// end of while------------------------------------------------
		
		System.out.println("총 : " + (totalByte-2) + "byte 입력함");	// (totalByte-2) 는 엔터크기(2byte)를 빼려고 -2를 한 것임.
		System.out.println("반복횟수 : " + cnt + "번 반복함");			// 결과 : 3번 반복함 (배열을 사용함으로써 22번 --> 3번 으로 줄어듦)
		
		
		
		
	}// end of main(String[] args)------------------------------------

}
