package io.day1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileInputStream_main_6 {
	
/*
    ※ Data Source (File, 키보드, 원격 컴퓨터)
    : 데이터의 근원
    
    ※ Data Destination (파일, 모니터, 프린터, 메모리)
    : 데이터가 최종적으로 도착하는 곳

  	Data Sourceㅇ======>ㅇ 프로그램 ㅇ======>ㅇ Data Destination
                입력스트림           출력스트림
              InputStream       OutputStream          
   
      
      === c:\iotestdata\korea.txt 파일을 읽어서 그 내용을 모니터(콘솔화면)에 출력하는 예제 ===
                  
      1. 데이터소스   : 파일로 부터 읽어들임   (노드스트림: FileInputStream) 
      2. 데이터목적지 : 결과물을 모니터에 출력  (노드스트림: System.out)
      
      >>>> FileInputStream
         - Node 스트림(접속점이 파일인 입력스트림)
         - 1byte 기반 스트림.
        
      >>>>> System.out :
                    부모클래스가 추상클래스 OutputStream(기본 출력 스트림) 타입인 것으로서
                    접속점(빨대)이 콘솔화면(모니터)인 출력 스트림(PrintStream)이다.
                    
       -- Node(접속점)가 콘솔화면(모니터)인 출력스트림이다.
       -- 1byte 기반 스트림이다.
       -- 주요 메소드 : println(String str),
                     print(String str),
                     write(int b)      
               
*/   
	// 파일을 읽어서 모니터에 쓰기.
	public static void main(String[] args) {	
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print(">> 읽을 파일의 이름(절대경로)을 입력하세요 => ");
		
		String fileName = sc.nextLine();
		// C:\iotestdata\korea.txt
		// 입력해준 파일명이 데이터소스가 된다.
		FileInputStream fist = null;
		
		
		try {	// 파일경로나 파일명을 잘못입력했을 경우..
			fist = new FileInputStream(fileName);		

			byte[] dataArr = new byte[10];
			// dataArr 변수의 용도는 빨대(System.in, 키보드)에서 흡입할 때, 단위크기를 10byte로 하는 것이다.
			// 또한 dataArr의 용도는 빨대(System.in, 키보드)에서 1번 흡입할 때 마다 흡입한 내용물을 저장하는 용도로 쓰인다.
			
			int inputLength = 0;
			// inputLength 변수의 용도는 빨대(System.in, 키보드)에서 흡입한 실제 크기(길이)를 나타내는 용도임.
			
			int totalByte = 0;	// byte 수 누적용도.
			int cnt = 0;		// while 문의 반복횟수를 알기 위한 것.
			
			while( ( inputLength = fist.read(dataArr)) != -1 ) {	// int를 읽어서 화면에 출력한다. input = fist.read() // -1이 파일의 끝이 아니라면!			
				/* fist.read(dataArr) 메소드는 해당 파일에서 데이터를 
				 배열 dataArr 크기인 10byte 씩 잘라서 읽는데,
				 실제 읽어온 byte 크기 (int 타입)를 inputLength 에 넣어준다.
				 이어서 읽어온 내용물을 배열 dataArr 에 저장시킨다.
				 
				 만약에 파일의 내용물에서 읽어들일 데이터가 없다면 -1 을 리턴시켜준다.
				 즉, 파일속의 내용물이 끝이 아니라면 계속해서 while{ } 부분을 실행하라는 말이다.
				// 한번 읽을 때마다 배열 10개씩 읽음. byte[] dataArr = new byte[10];
				*/
				System.out.write(dataArr, 0, inputLength);// 모니터(콘솔화면)에 출력
				System.out.flush();
				// print 는 int로 나오기 때문에 write로 한다.
				// write 가 있으면 flush 가 항상 있어야 한다.
								
				totalByte += inputLength;	// 읽어들인 크기만큼 누적
				
				cnt++;	// 반복횟수
				
			}// end of while--------------------

			System.out.println("\n==================================");
			System.out.println("총 "+totalByte+" byte");
			System.out.println("반복횟수 "+cnt+"번 반복함");
			System.out.println("==================================");
			/*
			==================================
			총 64 byte
			반복횟수 7번 반복함
			==================================
			*/
		
		} catch (FileNotFoundException e) {
			System.out.println(fileName+" 파일은 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fist != null)	// 위에 fist 의 기본값이 null; 이 었기 때문에.. if문 사용.
				fist.close();
			} catch (IOException e) {}
		}	// 위에 오류가 있든지 없든지 (finally)
				
		sc.close();

	}// end of main(String[] args)------------------------------------

}
