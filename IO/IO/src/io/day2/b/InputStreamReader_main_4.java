package io.day2.b;

import java.io.*;

public class InputStreamReader_main_4 {
	
	/*
       소스 - 입력노드 스트림  키보드(System.in) => 1byte기반
       브릿지 스트림 InputStreamReader => 1byte 를 2byte 로 변경 
       목적지 - 출력노드 스트림 FileWriter => 2byte기반
	*/		

	public static void main(String[] args) {
		
		try {
			InputStreamReader istReader = new InputStreamReader(System.in);
			// 키보드에서 입력하는 것은 1byte 기반인데 이것을 2byte 기반으로 변경해주는 것이다.
			
			String targetFileName = "C:/iotestdata/오늘의날씨.txt";
			
			FileWriter fw = new FileWriter(targetFileName);
			
			int input = 0;
			
			while ( (input = istReader.read()) != -1 ) {
				fw.write(input);
				fw.flush();
			}// end of while------------------------------
			
			System.out.println("\n >>> 종료합니다. <<<");
						
			fw.close();
			istReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}// end of main(String[] args)--------------------------------
/*
	실행은 아래와 같이 명령프롬프트에서 한다.
	
	C:\NCS\workspace(java)\IO\bin>java io.day2.b.InputStreamReader_main_4
	오늘은 아침부터 춥다가 오후에는 조금 풀리겠습니다.
	저녁에는 눈이온대요요~~~
	Ctrl + C 한것임.	
 	>>> 종료합니다. <<<
*/
	
}
