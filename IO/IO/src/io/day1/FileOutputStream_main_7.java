package io.day1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStream_main_7 {

/*
    ※ Data Source (File, 키보드, 원격 컴퓨터)
   : 데이터의 근원
    
    ※ Data Destination (파일, 모니터, 프린터, 메모리)
   : 데이터가 최종적으로 도착하는 곳

  Data Sourceㅇ======>ㅇ 프로그램 ㅇ======>ㅇ Data Destination
                입력스트림         출력스트림
              InputStream     OutputStream          
   
      
   === 키보드로 부터 입력받은 것을 파일(C:\iotestdata\result.txt)에 기록(출력)하기로 한다. ===
  
   1. 데이터 소스   : 키보드로 부터 입력받음   [노드스트림 : System.in]	// 빨대
   2. 데이터 목적지 : 결과를 특정 파일에 출력함 [노드스트림 : FileOutputStream] 
*/   
	
	public static void main(String[] args) {

		System.out.println(">> 내용을 입력하세요[입력하신 내용은 C:\\iotestdata\\result.txt 파일에 저장됨] ");	// \ 한개만 쓰면 escape임
		
		String fileName = "C:/iotestdata/result.txt"; // OS가 windows 이외에 / 이기 때문에, 다 호환되게 하기 위해 /를 사용한다.
		int totalByte = 0;	// byte 수 누적용도
		int cnt = 0;		// 반복횟수 
		FileOutputStream fost = null;
		try {
			int input = 0;
			
				boolean append = true;			
		//		boolean append = false;			

			fost = new FileOutputStream(fileName, append);			
			// FileNotFoundException : fileName에 잘못된 파일명을 입력했을 때를 대비함 ▶ exception 처리 할 것
			/*
			  	만약에  탐색기에서 
             	C:/iotestdata/result.txt 파일이 없다라면
                파일을 자동으로 생성해준다.
                단, 탐색기에서 C:/iotestdata 폴더는 존재해야 한다. (폴더까지는 생성해주지 않기 때문에 미리 존재해야함.)
		
				두번째 파라미터인 ★append 가 true★ 인 경우는
                첫번째 파라미터인 해당파일에 이미 내용물이 적혀 있는 경우일때
                기존내용물은 그대로 두고 ★기존내용뒤에 새로운 내용을 덧붙여 추가하겠다★는 말이다. 
                
                두번째 파라미터인 ★append 가 false★ 인 경우는
                첫번째 파라미터인 해당파일에 이미 내용물이 적혀 있는 경우일때
                기존내용물은 싹 지우고 ★새로운 내용을 새롭게 처음부터 쓰겠다★는 말이다.
              
                그런데 만약에 ★두번째 파라미터를 생략★하면    
                즉, FileOutputStream fost = new FileOutputStream(filename); 이라면
                ★자동적으로 false★ 로 인식한다. 즉, filename 의 기존내용물은 싹 지우고 새로운 내용을 새롭게 처음부터 쓰겠다는 말이다. 
			*/
			
			while( (input = System.in.read()) != -1 ) {
				
				fost.write(input);		// print를 쓰면 int로 나오므로, write를 쓴다.
				fost.flush();
				
				totalByte++;
				cnt++;				
			}// end of while------------
	
			fost.close();	// null 값이 아니라면 close 해라.
							// 성공하든 실패하든 close 해주어야함. (finally)
			
		} catch (FileNotFoundException e) {	// file 명이 틀릴 때.
			System.out.println(fileName +" 파일이 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();		
		}
		
		System.out.println(fileName +"에 쓰기 완료!!" + totalByte +"byte 씀.");
		System.out.println("반복횟수 :"+cnt+" 번 반복함.");
		
	}// end of main--------------------------------------------------------

}
