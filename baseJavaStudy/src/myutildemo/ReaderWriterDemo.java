package myutildemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ReaderWriterDemo {
	public static void main(String[] args) throws IOException {
		File dscFile=new File("C:\\Users\\Administrator\\Desktop\\富爸爸、穷爸爸（英文版）.txt");
		File srcFile=new File("D:\\doc\\富爸爸、穷爸爸（英文版）.txt");
		long time=System.currentTimeMillis();
		
//		字符流读写
//		FileReader fr=new FileReader(srcFile);
//		FileWriter fw=new FileWriter(dscFile);
//		char[] buf=new char[500];//字符缓冲区
//		int len=0;//实际读到的字符个数
//		while ((len=fr.read(buf))!=-1) {
//			fw.write(buf, 0, len);
////			fw.flush();
//		}
//		fr.close();
//		fw.close();
//		System.out.println("复制完毕："+(System.currentTimeMillis()-time)+" ms");
//		
//		缓冲字符流读写
//		BufferedReader br=new BufferedReader(fr);
//		BufferedWriter bw=new BufferedWriter(fw);
//		String line="";
//		while((line=br.readLine())!=null){
//			bw.write(line);
//			bw.newLine();
////			bw.flush();
//		}
//		br.close();
//		bw.close();
//		System.out.println("复制完毕："+(System.currentTimeMillis()-time)+" ms");
		
//		读写流(字符字节流)
		FileInputStream inputStream=new FileInputStream(srcFile);
		FileOutputStream outputStream=new FileOutputStream(dscFile);
		//这种方法可以控制读取编码
		InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
		OutputStreamWriter writer=new OutputStreamWriter(outputStream, "UTF-8");
		char[] buf=new char[500];
		int len=0;
		while ((len=reader.read(buf))!=-1) {
			writer.write(buf,0,len);
		}
		reader.close();
		writer.close();
		System.out.println("复制完毕："+(System.currentTimeMillis()-time)+" ms");
		
	}

}
