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
		File dscFile=new File("C:\\Users\\Administrator\\Desktop\\���ְ֡���ְ֣�Ӣ�İ棩.txt");
		File srcFile=new File("D:\\doc\\���ְ֡���ְ֣�Ӣ�İ棩.txt");
		long time=System.currentTimeMillis();
		
//		�ַ�����д
//		FileReader fr=new FileReader(srcFile);
//		FileWriter fw=new FileWriter(dscFile);
//		char[] buf=new char[500];//�ַ�������
//		int len=0;//ʵ�ʶ������ַ�����
//		while ((len=fr.read(buf))!=-1) {
//			fw.write(buf, 0, len);
////			fw.flush();
//		}
//		fr.close();
//		fw.close();
//		System.out.println("������ϣ�"+(System.currentTimeMillis()-time)+" ms");
//		
//		�����ַ�����д
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
//		System.out.println("������ϣ�"+(System.currentTimeMillis()-time)+" ms");
		
//		��д��(�ַ��ֽ���)
		FileInputStream inputStream=new FileInputStream(srcFile);
		FileOutputStream outputStream=new FileOutputStream(dscFile);
		//���ַ������Կ��ƶ�ȡ����
		InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
		OutputStreamWriter writer=new OutputStreamWriter(outputStream, "UTF-8");
		char[] buf=new char[500];
		int len=0;
		while ((len=reader.read(buf))!=-1) {
			writer.write(buf,0,len);
		}
		reader.close();
		writer.close();
		System.out.println("������ϣ�"+(System.currentTimeMillis()-time)+" ms");
		
	}

}
