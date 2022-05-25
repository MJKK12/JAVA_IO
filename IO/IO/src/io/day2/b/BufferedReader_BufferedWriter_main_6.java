package io.day2.b;

import java.io.*;

import io.util.FileManager;

/*
     소스 -- 파일 "C:/iotestdata/myprofile.txt"
     노드스트림 -- FileReader
     필터스트림(보조스트림, 오리발) BufferedReader
        
        
     목적지 -- 파일 "C:/iotestdata/myprofile_복사본.txt" 
     노드스트림 -- FileWriter
     필터스트림(보조스트림, 오리발) BufferedWriter / PrintWriter (속도의 향상!!)
*/

public class BufferedReader_BufferedWriter_main_6 {

	public static void main(String[] args) {

		try {
			// 소스파일 이름
			String srcFileName = "C:/iotestdata/myprofile.txt";
			
			// 2byte 기반의 입력 노드스트림 생성(빨대꼽기)
			FileReader fr = new FileReader(srcFileName);
			
			// 입력 필터스트림(보조스트림, 오리발)을 노드스트림에 장착하기
			BufferedReader bufReader = new BufferedReader(fr, 1024);	// int sz : 1024로 하겠다.
			
			
			// --------------------------------------------------- //
			
			// 목적지파일 이름
			String targetFileName = "C:/iotestdata/myprofile_복사본.txt";
			
			// 2byte 기반의 출력 노드스트림 생성(빨대꼽기)
			FileWriter fw = new FileWriter(targetFileName);
			
			// 입력 필터스트림(보조스트림, 오리발)을 노드스트림에 장착하기
			BufferedWriter bufWriter = new BufferedWriter(fw, 1024);	// int sz : 1024로 하겠다.
			
			String strLine = null;
			while( (strLine = bufReader.readLine()) != null) {
				// bufReader.readLine() 메소드는 1줄 단위로 읽어와서 
				// 읽어온 내용은 String 타입이므로 strLine 변수에 넣어준다.
				// 1줄을 읽어오되 엔터 전까지 읽어온다.	// 읽었는데 아무것도 나오지 않음( == null )
	
				bufWriter.write(strLine);
				bufWriter.newLine();// 엔터전까지 읽어오는 것이기 때문에 반드시 줄바꿈을 해주어야 한다.!!
			//  또는 
			//	bufWriter.write("\r\n"); // 엔터
				
				bufWriter.flush();			
			}// end of while---------------------------------------		
			
			// while 문 빠져나온 뒤에 copy를 한다.
			
			System.out.println("\n >>> 파일복사 완료!! <<<");
			
			// 닫을때는 항상 필터스트림(보조스트림) 부터 먼저 닫고, 그 다음에 노드스트림을 닫는다.
			bufWriter.close();	// 필터스트림(보조스트림)
			fw.close();			// 노드스트림
			
			bufReader.close();  // 필터스트림(보조스트림)
			fr.close();			// 노드스트림
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}// end of main(String[] args)------------------------------------
/*
	 실행은 아래와 같이 한다.
	 먼저 탐색기에서 C:/iotestdata/myprofile.txt 파일을 인코딩 ANSI 로 생성한 후
	 
	 C:\NCS\workspace(java)\IO\bin>java io.day2.b.BufferedReader_BufferedWriter_main_6
 	 >>> 파일복사 완료!! <<<	(한줄한줄 읽어온 것임.)
 	 
*/

	
}
