package io.day2.b;

import java.io.*;

import io.util.FileManager;

public class FileReader_FileWriter_main_3 {

	public static void main(String[] args) {

		try {
			FileManager.charFileCopy(args[0], args[1]);
			// args[0] 은 C:/iotestdata/애국가2.txt 으로 하고 애국가2.txt 파일은 인코딩이 ANSI 이다.
			// args[1] 은 C:/iotestdata/애국가2_복사본.txt 으로 한다.

			// args[0] 은 C:/iotestdata/애국가.txt 으로 하고 애국가.txt 파일은 인코딩이 UTF-8 이다.
			// args[1] 은 C:/iotestdata/애국가_copy.txt 으로 한다.
			
			System.out.println(args[0]+"을 "+args[1]+"로 복사완료함!!");
			
		} catch (FileNotFoundException e) {	// try multi-catch 를 했을 때, IOException 이 FileNotFoundException 까지 포함한다.
			System.out.println(args[0] +" 파일이 없거나 또는 " + args[1]+ " 파일 생성이 실패했습니다.");
		} catch (IOException e) {	// try multi-catch 를 했을 때, IOException 이 FileNotFoundException 까지 포함한다.
			System.out.println(args[0] +" 파일이 손상되었습니다. ");
	}
		
	}// end of main(String[] args)------------------------------------

	/*
	실행은 아래와 같이 함.
	C:\NCS\workspace(java)\IO\bin>java io.day2.b.FileReader_FileWriter_main_3 C:\iotestdata\애국가2.txt C:\iotestdata\애국가2_복사본.txt
	C:\iotestdata\애국가2.txt을 C:\iotestdata\애국가2_복사본.txt로 복사완료함!!
	*/
	
}
