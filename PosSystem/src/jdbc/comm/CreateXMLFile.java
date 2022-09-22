package jdbc.comm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class CreateXMLFile {
	public static void main(String[] args) {

		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("생성할 파일 이름 : ");
			String fileName = sc.next();
			
			Properties prop = new Properties();
			prop.storeToXML(new FileOutputStream(fileName +".xml"), fileName+".xml file");
		
			System.out.println(fileName + ".xml 파일 생성 완료");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}