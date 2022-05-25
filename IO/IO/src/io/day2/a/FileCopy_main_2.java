package io.day2.a;

import java.io.*;
import java.util.Scanner;

public class FileCopy_main_2 {

/*
 	=== 파일로 부터 입력받은 것을 파일에 기록(출력)하기로 한다. ===
	
	1. 데이터 소스   : 파일로 부터 입력받음.    [노드스트림(빨대) : FileInputStream]
				   + 필터스트림(보조스트림)으로 BufferedInputStream 을 사용함.
				   
	2. 데이터 목적지 : 결과를 특정 파일에 출력함. [노드스트림(빨대) : FileOutputStream]
 				   + 필터스트림(보조스트림)으로 BufferedOutputStream 을 사용함. 
 */
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.print(">> 복사할 원본파일명(절대경로) 입력 => ");
		String srcFileName = sc.nextLine();			// 파일명에 asfsreds 이런것이 존재하지 않을 경우.. ▶ FileNotFoundException
		
		System.out.print(">> 목적 파일명(절대경로) 입력 => ");
		String targetFileName = sc.nextLine();		// z:\\ z 드라이브라는 것은 없음 ▶ FileNotFoundException

		int totalByte = 0;		// Byte수 누적용도
		int cnt = 0;			// 반복횟수
		

		// === 우리는 파일원본크기가 2Mb 이내는 복사를 허락해주지만, 2Mb 초과한 경우에는 복사를 불허하도록 만들어 보겠다. === ▶ 용량제한
			
		// 소스 File 객체 생성하기.
		// String 타입인 srcFileName이 실제 File 클래스의 객체로 만들어야만 파일의 크기(용량)를 알아와서 용량제한을 걸 수 있게 된다.

		if(srcFileName != null && targetFileName != null) {
			
			try {
				File srcFile = new File(srcFileName);	// fileName이 반드시 존재해야만 하므로, srcFileName을 null로 할 수 없다.!!
				long srcFileSize = srcFile.length();						// 파일의 크기를 알려준다. 
				System.out.println(">> 원본 파일 크기 : " + srcFileSize + "byte");
				
				long maxSize = 1024*1024*10; // 1024*1024*2 == 2Mb, 1024*1024*10 == 10Mb.
				
				if(srcFileSize >= maxSize) {
					// 원본파일의 크기가 10Mb를 초과한 경우
					System.out.println(">> [경고] 원본 파일의 크기가 2Mb 를 초과했으므로 복사할 수 없습니다. <<");
					
					sc.close();
					return;	// main()메소드 종료
				}
				
				else {
					// 원본파일의 크기가 10Mb를 초과하지 않은 경우 ▶ 그제서야 copy를 한다.!!
					
					// 입력노트스트림 ==> 파일 (FileInputStream)
					FileInputStream fist = new FileInputStream(srcFile);	//() 파라미터에 srcFile 이나 srcFileName 둘 다 사용 가능하다.
					// FileInputStream 생성 : 접속점이 파일인 것으로 특정 파일에 빨대를 꽂아 파일의 내용물을 1byte 기반으로 빨아들이는 ★입력노드★ 스트림이다.
					
					BufferedInputStream bist = new BufferedInputStream(fist, 1024*1024);
					// 노드스트림인 fist 에 필터스트림(보조스트림) 을 장착함.
					// bist[필터스트림(보조스트림)]의 버퍼크기는 1024*1024 byte(== 1Mb) 로 했다.
					
					
					// 출력노드스트림 ==> 파일(FileOutputStream)
					File targetFile = new File(targetFileName);	// targetFile을 넣어도되고 targetFileName 을 넣어도 된다.
					FileOutputStream fost = new FileOutputStream(targetFile);
					// FileOutputStream 생성 : 접속점이 파일인 것으로 특정 파일에 빨대를 꽂아 파일의 내용물을 1byte 기반으로 기록해주는(써주는) ★출력노드★ 스트림이다.			
					
					BufferedOutputStream bost = new BufferedOutputStream(fost, 1024*1024);
					// 노드스트림인 fost 에 필터스트림(보조스트림) 을 장착함.
					// bost[필터스트림(보조스트림)]의 버퍼크기는 1024*1024 byte(== 1Mb) 로 했다.
					
					byte[] dataArr = new byte[1024*1024];	// 1024*1024 == 1Mb
					int inputLength = 0;					// 읽어온 크기를 담아준다.
					
					while ((inputLength = bist.read(dataArr)) != -1) {
						
						// 파일에 출력하기
						bost.write(dataArr, 0, inputLength);	// 첫번째(0) 부터 읽어온 길이만큼!!, 파일에 쓰기
						bost.flush();
					
						totalByte += inputLength;
						cnt++;
						
						double percent = ((double)totalByte/srcFileSize) * 100;	// 12.56789
						
						System.out.printf("\n%4.1f%% 복사중...\n", percent);		// 12.6%(위에서 반올림됨) .... 99.9% 100.0% // 전체 4자리에서 소수부 1개.
						// printf 에서 %를 나타내려면 %% 로 하면 된다.
						
					}// end of while------------------------------------
				
					// 닫을때는 보조(필터)스트림 부터 먼저 닫고, 그 다음에 노드스트림을 닫는다.
		
					bost.close();
					fost.close(); 	// 출력을 close 한다.
					
					bist.close();
					fist.close();	
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();			
			} catch (IOException e) {
				e.printStackTrace();			
			}	
		
			System.out.println(targetFileName + "에 쓰기 완료!!" +totalByte+ "byte 복사됨.");
			System.out.println("반복횟수 : "+cnt+ "번 반복함.");
				
				
			}// end of if------------------------------------------------------------
			
			else {
				// srcFileName 이 null 인 경우 
				
				System.out.println(">> 원본소스파일에 문제가 발생하여 복사가 불가합니다. <<");
				
			}// end of else------------------------------------------------------
			
			sc.close();		
		
	}// end of main(String[] args)--------------------------------

}
