package io.day2.a;

import java.io.*;
import java.util.Scanner;

public class File_main_3 {

/*
	 >>> File 클래스 <<<
 		자바에서 File 클래스의 객체라 함은 ★파일 및 폴더(디렉토리)★를 다 포함한다.
 		
 */
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.print("탐색기에 존재하는 파일명을 입력하세요. : ");
		String fileName = sc.nextLine();
		// C:/iotestdata/원본/쉐보레전면.jpg	(==  C:\\iotestdata\\원본/쉐보레전면.jpg ▶ 호환때문에 /를 사용.)
		
		File file1 = new File(fileName);
		
		System.out.println("파일명만 : " + file1.getName());	// getName == 파일name만 얻어옴.
		// file.getName(); 은 파일명만 알려주는 것이다.
		// 파일명만 : 쉐보레전면.jpg
		
		long fileSize = file1.length();
		System.out.println("파일크기 : " + fileSize + "byte");	// getName == 파일name만 얻어옴.
		// 파일크기 : 131110byte
		
		String AbsolutePath = file1.getAbsolutePath();
		System.out.println("파일의 절대경로 : " + AbsolutePath);	// getName == 파일name만 얻어옴.
		// 파일의 절대경로 : C:\iotestdata\원본\쉐보레전면.jpg

		String Path = file1.getPath();
		System.out.println("파일의 경로 : " + Path);	// getName == 파일name만 얻어옴.
		// 파일의 경로 : C:\iotestdata\원본\쉐보레전면.jpg

		System.out.println("====================================================\n");
		
		System.out.println(">>> 디렉토리(폴더) 생성하기 <<<");
		
		File dir = new File("C:/iotestdata/MyDir");
		
		boolean bool = false;
		
		if( !dir.exists() ) {
								// 해당 디렉토리(폴더)가 없으면 
			bool = dir.mkdir(); // 해당 디렉토리(폴더)를 생성해라
		
			String result = bool?"디렉토리(폴더) 생성 성공!!":"디렉토리(폴더) 생성 실패!!";
			
			System.out.println("C:/iotestdata/MyDir " + result);
		}
		
		// >> dir 이 디렉토리(폴더) 인지 알아오기 <<
		if(dir.isDirectory()) {	// dir이 directory 입니까 or file 입니까?
			System.out.println("C:/iotestdata/MyDir 은 디렉토리(폴더) 입니다.");
		}
		
		System.out.println("====================================================\n");
		
		System.out.println(">>> 파일 생성하기 <<<");
		
		File file2 = new File("C:/iotestdata/MyDir/테스트1.txt");
		
		if( !file2.exists() ) {
			// 해당 파일이 존재하지 않으면 
			
			try {
				bool = file2.createNewFile();	// 파일생성하기
			
				if(bool) {
					// 해당파일이 정상적으로 생성되었다면
					System.out.println("테스트1.txt 의 절대경로 : " + file2.getAbsolutePath());
				}
			
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
		}
		
		// >> file2 가 파일인지 알아오기 <<
		if(file2.isFile()) {	// dir이 directory 입니까 or file 입니까?
			System.out.println("C:/iotestdata/MyDir/테스트1.txt 은 파일입니다.");
		}
		
		System.out.println("====================================================\n");
		
		System.out.println(">>> 파일 삭제하기 <<<");

		bool = file2.delete(); // 파일 삭제하기 (정상적으로 삭제했으면 true, 그렇지 않으면 false)
		String result = bool?"C:\\iotestdata\\MyDir\\테스트1.txt 파일삭제 성공!!":"C:\\iotestdata\\MyDir\\테스트1.txt 파일삭제 실패!!";
		System.out.println(result);
		
		System.out.println("====================================================\n");
		
		System.out.println(">>> 텅빈디렉토리(폴더) 삭제하기 <<<");
		
		if(dir.exists()) {			 //dir이 존재합니까?
			bool = dir.delete();	// 텅빈디렉토리(폴더) 삭제하기 
			result = bool?"C:\\iotestdata\\MyDir 폴더삭제 성공!!":"C:\\iotestdata\\MyDir 폴더삭제 실패!!";
			System.out.println(result);
		}
		
		System.out.println("====================================================\n");
		
		System.out.println(">>> 내용물이 들어있는 디렉토리(폴더) 삭제하기 실패한 예제 <<<");
		// 먼저 아래의 실습을 하려면 탐색기에서 C:\iotestdata\ 밑에 images 라는 폴더를 생성하고
	    // images 폴더 속에 파일을 몇 개 올려둔다.
		
		File imagesDir = new File("C:/iotestdata/images");
		
		if(imagesDir.exists()) {		// imagesDir이 존재합니까?
			bool = imagesDir.delete();	// 텅빈디렉토리(폴더) 삭제하기 
			result = bool?"C:\\iotestdata\\images 폴더삭제 성공!!":"C:\\iotestdata\\images 폴더삭제 실패!!";
			System.out.println(result);
			// C:\iotestdata\images 폴더삭제 실패!!
		}
		
		System.out.println("====================================================\n");
		
		System.out.println(">>> 내용물이 들어있는 디렉토리(폴더) 삭제하기 성공한 예제 <<<");
		
		// 1. 내용물이 들어있는 디렉토리(폴더)내에 존재하는 내용물을 파악한다. 
		File[] deleteFileArr = imagesDir.listFiles();
		
		for(int i=0; i<deleteFileArr.length; i++) {
			String absolutePath_2 = deleteFileArr[i].getAbsolutePath();
			System.out.println(absolutePath_2);
		}// end of for------------------------------------------

			/*
			C:\iotestdata\images\쉐보레실내.jpg
			C:\iotestdata\images\쉐보레엔진.jpg
			C:\iotestdata\images\쉐보레전면.jpg
			C:\iotestdata\images\쉐보레후면.jpg
     	 	*/
			
		// 2. C:\iotestdata\images 폴더내의 파일들을 모두 삭제한다.
		for(int i=0; i<deleteFileArr.length; i++) {
			deleteFileArr[i].delete();			
		}// end of for------------------------------------
		
		bool = imagesDir.delete();	
		// C:\iotestdata\images\ 폴더는 텅빈디렉토리(폴더) 이므로 폴더 삭제가 가능해진다. 
		
		result = bool?"C:\\iotestdata\\images 폴더삭제 성공!!":"C:\\iotestdata\\images 폴더삭제 실패!!";
		System.out.println(result);
		
		sc.close();

	}// end of main(String[] args)----------------------------------------

}
