package io.util;

import java.io.*;

public class FileManager {

	public static String reading(String fileName) {
		
		try {
			// 해당 파일에 노드 연결 생성 (빨대꽂기)
			FileReader fr = new FileReader(fileName);
		
			int data =0;
			StringBuilder sb = new StringBuilder();
			
			do {
				data = fr.read();	// fr 이라는 파일로 부터 글자(char) 1개(2byte)씩 읽어들임.
									// fr 이라는 파일로 부터 읽어들인 글자(char)가 없으면 -1 을 리턴시켜줌.
		
				if(data != -1)				
				// 파일내용물에서 읽어온 것이 존재하는 경우.
					sb.append((char)data);	// data가 int 타입인데, 글자로 바꾸어주저야 하므로 char로 바꿈.
				else						
				// 파일내용물에서 더이상 읽어올 것이 없는 경우.
					break;				
			
			} while (true);
			// end of do~while----------------------------------------
			
			fr.close(); 			// return 하기전에 닫는다. (빨대제거!!)	
			
			return sb.toString();	//StringBuilder에 쌓여져 있던것을 return 시켜준다.
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return fileName + " 라는 파일은 존재하지 않습니다.";			
		} catch (IOException e) {		// ex. 디스크나 USB 손상 시 발생 가능.
			e.printStackTrace();
			return fileName + " 이 손상되었습니다.";
		}

		
	}// end of public static String reading(String fileName)-------
	
	public static String reading_2(String fileName) throws FileNotFoundException, IOException {
															// File 이 없으면 throws(던져라)!, IOException
			// 해당 파일에 노드 연결 생성 (빨대꽂기)
			FileReader fr = new FileReader(fileName);
		
			char[] dataArr = new char[10];	// 한 번 읽을 때 20글자 읽어온다.
			int dataLength = 0;
			
			StringBuilder sb = new StringBuilder();
			
			do {
				dataLength = fr.read(dataArr);
				// fr 파일로 부터 dataArr 배열의 크기인 글자(char)10개씩 읽어들임.
	            // 읽어들인 글자는 char[] 타입의 배열인 dataArr 에 저장시킨후
	            // 읽어들인 글자수(길이)는 dataLength 에 저장시킨다.
	            // 그런데 fr 파일이 손상되었을시 IOException 이 발생된다. 
				
				if(dataLength != -1) {				
					// 파일내용물에서 읽어온 것이 존재하는 경우.
					String str = new String(dataArr, 0, dataLength);	// char 타입 배열에서 0번째 길이에서 dataLength 길이만큼!!
				    // new String(dataArr, 0, dataLength) 은
	                // char[] 타입의 배열인 dataArr 에서 0번째 인덱스 글자부터 
	                // dataLength 갯수만큼 뽑아서 String 타입으로 만든다는 말이다.
					
					sb.append(str);	// data가 int 타입인데, 글자로 바꾸어주저야 하므로 char로 바꿈.
					
				}
				else						
				// 파일내용물에서 더이상 읽어올 것이 없는 경우.
					break;				
			
			} while (true);
			// end of do~while----------------------------------------
			
			fr.close(); 			// return 하기전에 닫는다. (빨대제거!!)	
			
			return sb.toString();	//StringBuilder에 쌓여져 있던것을 return 시켜준다.
			
		
	}// end of public static String reading_2(String fileName)-------
	
	public static void charFileCopy(String srcFileName, String targetFileName) throws FileNotFoundException, IOException {
		// 오로지 글자로만 된 file copy!!
		
		// 해당 소스파일에 노드 연결 생성(빨대꽂기)
		FileReader fr = new FileReader(srcFileName);
		
		// 해당 타겟파일에 노드 연결 생성(빨대꽂기)
		FileWriter fw = new FileWriter(targetFileName);
		
		char[] dataArr = new char[10];
		int dataLength = 0;
		
		
		do {
			dataLength = fr.read(dataArr);	// 이 크기만큼 읽어들인다.
			// fr 파일로 부터 글자(char)10개씩 읽어들임.
            // 읽어들인 글자는 char[] 타입의 배열인 dataArr 에 저장시킨후
            // 읽어들인 글자수(길이)는 dataLength 에 저장시킨다.
            // 그런데 fr 파일이 손상되었을시 IOException 이 발생된다. 
			
			if(dataLength != -1) {
			fw.write(dataArr, 0, dataLength);
			fw.flush();
			}			
			else {
				break;
			}
		} while (true);
		// end of do~while------------------------------
		
		fw.close();	// 빨대제거
		fr.close(); // 빨대제거
		
	}// end of charFileCopy(String srcFileName, String targetFileName)------------
	
}
