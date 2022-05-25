package io.day2.b;

import io.util.FileManager;

public class FileReader_main_1 {

	public static void main(String[] args) {
	// 읽어들일 파일명은 명령프롬프트에서 파라미터(인자)로 주겠다.
	
	/*
		String str1 = args[0];
		String str2 = args[1];

		System.out.println("str1 =>" + str1);
		System.out.println("str2 =>" + str2);
	*/		

		// ★특정 파일명★에 해당하는 내용들을 읽어다가 화면에 출력하고자 한다.
		// 그런데 그 특정파일명의 내용은 글자로만 이루어진 파일이다.
		
		String str = FileManager.reading(args[0]);
		// args[0] 에 C:/iotestdata/애국가.txt 로 할 것이다.
		
		System.out.println(str);
	
	}// end of main(String[] args)---------------------------------
/*
	실행은 명령프롬프트에서 아래와 같이 한다.	 
	C:\NCS\workspace(java)\IO\bin>java io.day2.b.FileReader_main_1 안녕하세요 hello	
	
	또는 이클립스에서 실행을 하려면
	Run Configurations.. 에 클릭하여 Arguments 탭을 클릭하여 Program Arguments 에 문자열(ex. 안녕하세요 hello)을 입력하면 된다.

	실행은 아래와 같이 한다.
	Run Configurations.. 에 클릭하여 Arguments 탭을 클릭하여 Program Arguments 에 C:/iotestdata/애국가.txt 을 입력하면 된다.
	
	C:/iotestdata/애국가.txt 파일은 인코딩을 UTF-8 로 저장한다. 
	
*/
}
