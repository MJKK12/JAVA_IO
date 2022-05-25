package io.day1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileCopy_main_9 {

/*
 	=== 파일로 부터 입력받은 것을 파일에 기록(출력)하기로 한다. ===
	
	1. 데이터 소스   : 파일로 부터 입력받음.    [노드스트림(빨대) : FileInputStream]
	2. 데이터 목적지 : 결과를 특정 파일에 출력함. [노드스트림(빨대) : FileOutputStream]
 */
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println(">> 복사할 원본파일명(절대경로) 입력 => ");
		String srcFileName = sc.nextLine();
		
		System.out.println(">> 목적 파일명(절대경로) 입력 => ");
		String targetFileName = sc.nextLine();

		int totalByte = 0;		// Byte수 누적용도
		int cnt = 0;			// 반복횟수
		
		try {

			FileInputStream fist = new FileInputStream(srcFileName);
			// FileInputStream 생성 : 접속점이 파일인 것으로 특정 파일에 빨대를 꽂아 파일의 내용물을 1byte 기반으로 빨아들이는 ★입력노드★ 스트림이다.
			
			FileOutputStream fost = new FileOutputStream(targetFileName);
			// FileOutputStream 생성 : 접속점이 파일인 것으로 특정 파일에 빨대를 꽂아 파일의 내용물을 1byte 기반으로 기록해주는(써주는) ★출력노드★ 스트림이다.			
			
			byte[] dataArr = new byte[1024];
			int inputLength = 0;	// 읽어온 크기를 담아준다.
			
			while ((inputLength = fist.read(dataArr)) != -1) {
				fost.write(dataArr, 0, inputLength);	// 첫번째(0) 부터 읽어온 길이만큼!!, 파일에 쓰기
				fost.flush();
			
				totalByte += inputLength;
				cnt++;
				
			}// end of while------------------------------------
			
			fost.close(); 	// 출력을 close 한다.
			fist.close();	// 
						
		} catch (FileNotFoundException e) {
			e.printStackTrace();			
		} catch (IOException e) {
			e.printStackTrace();			
		}
	
		System.out.println(targetFileName + "에 쓰기 완료!!" +totalByte+ "byte 복사됨.");
		System.out.println("반복횟수 : "+cnt+ "번 반복함.");
			
		sc.close();		
		
	}// end of main(String[] args)--------------------------------

}
