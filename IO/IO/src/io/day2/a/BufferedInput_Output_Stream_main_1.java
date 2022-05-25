package io.day2.a;

import java.io.*;

public class BufferedInput_Output_Stream_main_1 {
  
	/*
    >>>>> BufferedInputStream 와 BufferedOutputStream <<<<<
    -- 1 byte 기반 스트림.
    -- 필터스트림(다른말로 보조스트림 이라고 부른다.)
    -- 단독으로 사용할 수 없고, 반드시 노드스트림에 장착되어 사용되는 것이다.
       마치 수영장에서 오리발처럼 보조기구로 사용한다.
       이것을 사용하면 오리발처럼 속도가 향상되므로 많이 사용한다.
            
    -- 읽어올 데이터를 매번 1 byte 마다 읽고,쓰고 한다면 입.출력에 너무 많은 시간이 소요된다.
       그래서 쓰는 작업없이 ★메모리 버퍼에 데이터를 한꺼번에 쭉~~ 읽기만 하여 모아두면★
       그만큼 쓰는 작업이 없으므로 읽기 속도는 빨라질 것이다.
       그리고 나서 메모리 버퍼에 읽어서 모아두었던 내용을 한방에 쓰기를 하면 매번 1byte 씩 쓰는 것보다
       속도가 빨라진다.
            
       BufferedInputStream 와 BufferedOutputStream 의 기본 버퍼크기는 512 byte 이다.	▶Buffer(용량이 512 byte) 에 다 차면, 크기를 늘려주겠다.
     
     [예제]
        필터스트림(보조스트림)을 이용해서 키보드로 부터 입력받고, 
        입력받은 그 내용을 모니터(콘솔화면)에 출력하고,
        또한 동시에 파일에도 출력해본다.
        
     >>> 데이터소스 : 키보드(System.in --> 노드스트림)
                  + 필터스트림(보조스트림)으로 BufferedInputStream 을 사용함.
     
     >>> 데이터목적지 : 모니터(System.out --> 노드스트림)
                   + 필터스트림(보조스트림)으로  BufferedOutputStream 을 사용함.
                   
                     파일(FileOutputStream --> 노드스트림)
                   + 필터스트림(보조스트림)으로  BufferedOutputStream 을 사용함.                                                 
       
  */   
	
	public static void main(String[] args) {

		try {
			// 입력노드스트림 ==> 키보드(System.in)
	        // 입력노드스트림에 보조(필터)스트림(BufferedInputStream)을 장착한다.
	     
		//	BufferedInputStream bist = new BufferedInputStream(System.in);
	        // bist 의 기본 버퍼크기는 512 byte
	
			BufferedInputStream bist = new BufferedInputStream(System.in, 1024);
			// 노드스트림인 System.in(키보드)에 필터스트림(보조스트림)을 장착함.
	        // bist[필터스트림(보조스트림)] 의 버퍼크기는 1024 byte 로 했다.	(기본은 512byte 이지만, 1024byte로 바꿨다.)
			
			
			// 출력노드스트림 ==> 모니터(System.out)
	        // 출력노드스트림에 보조(필터)스트림(BufferedOutputStream)을 장착한다.
	        BufferedOutputStream bost1 = new BufferedOutputStream(System.out, 1024);
	        // 노드스트림인 System.out(모니터)에 필터스트림(보조스트림)을 장착함.
	        // bost1[필터스트림(보조스트림)] 의 버퍼크기는 1024 byte 로 했다.		
	        
	        String fileName = "C:/iotestdata/나의소개.txt";
			FileOutputStream fost = new FileOutputStream(fileName);
			// 출력노드스트림 ==> 파일(FileOutputStream)
			
		//	BufferedOutputStream bost2 = new BufferedOutputStream(fost);
			// 출력노드스트림인 fost 에 필터스트림(보조스트림)을 장착함. 
			// bost2[필터스트림(보조스트림)] 의 버퍼크기는 512 byte 가 된다.
			// bost2 에 fost를 장착한 것임.
			
			BufferedOutputStream bost2 = new BufferedOutputStream(fost, 1024);	// 입력이 1024 였으니, 출력도 1024로 한다.
			// 출력노드스트림인 fost 에 필터스트림(보조스트림)을 장착함. 
			// bost2[필터스트림(보조스트림)] 의 버퍼크기는 1024 byte 가 된다.
			// bost2 에 fost를 장착한 것임.
			
			int input = 0;
			int totalByte = 0;
			
			while( (input = bist.read()) != -1) {
	
				// 모니터에 출력하기
				bost1.write(input);
				bost1.flush();
				
				// 파일에 출력하기
				bost2.write(input);
				bost2.flush();
				
				totalByte++;
			}// end of while------------------------------------
		
			// 닫을때는 보조(필터)스트림 부터 먼저 닫고, 그 다음에 노드스트림을 닫는다.
			bost2.close();
			fost.close();
			
			bost1.close();
			System.out.close();
			
			bist.close();
			System.in.close();
			
			System.out.println(">> "+ totalByte +" byte 씀 <<");
			
		} catch (FileNotFoundException e) {
			System.out.println(">> 파일이 존재하지 않습니다. <<");
			e.printStackTrace();
		}
		 catch (IOException e) {
				e.printStackTrace();
		}
	        
	}// end of main(String[] args)----------------------------------------

}
