package myutildemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerDemo {
	public static void main(String[] args) throws FileNotFoundException {
//		Scanner sysScan=new Scanner(System.in);
//		for (int i = 0; i < 3; i++) {
//			int number=sysScan.nextInt();
//			int number=sysScan.nextLine();
//			System.out.println(number);
//		}
		
		File file=new File("D:\\doc\\doc.txt");
		Scanner fscan=new Scanner(file);
		/*
		fscan.useDelimiter("\\D\\s*");//过滤非数字，即只读入数字
//		fscan.useDelimiter(":");
		while (fscan.hasNext()) {
			System.out.println(fscan.next());
		}
		*/
		while(fscan.hasNextLine()){
			System.out.println(fscan.nextLine()); 
		}
	}
}
