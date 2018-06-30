package myutildemo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class IOStreamDemo {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
//		�ļ���
		/*
		File dscFile=new File("C:\\Users\\Administrator\\Desktop\\Hita - �����.mp3");
		File srcFile=new File("D:\\doc\\Hita - �����.mp3");
		try {
			HWFileUtil.copyFile(srcFile, dscFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
//		�ֽ���
		/*
		String string="hello world!";
		ByteArrayInputStream bis=new ByteArrayInputStream(string.getBytes());
		int data=-1;
		while ((data=bis.read())!=-1) {
			System.out.println((char)data);
		}
		bis.close();
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		bos.write(97);
		bos.write(65);
		bos.write(string.getBytes());
		
		byte[] bytes=bos.toByteArray();
		bos.close();
		for (byte b : bytes) {
			System.out.println((char)b);
		}
//		�ڶ�������Ϊtrue����׷�ӵķ�ʽд���ļ��������ڵڶ��δ��ļ�д��ʱ��д��ĩβ�������Ḳ���ļ���ԭ������
		FileOutputStream fos=new FileOutputStream("D:\\doc\\doc.txt",true);
		bos.writeTo(fos);
		fos.close();
		*/
		
		/*
//		������
		File file=new File("D:\\doc\\doc.txt");
		FileInputStream fis=new FileInputStream(file);
		FileOutputStream fos=new FileOutputStream(file);
		DataInputStream dis=new DataInputStream(fis);
		DataOutputStream dos=new DataOutputStream(fos);
		
		String string="hello world ��ΰ";
		int i=10;
		boolean flag=true;
		char sex='��';
		double money=1000000.95;
//		dos.writeChars(string);
		
		dos.writeUTF(string);
		dos.writeInt(i);
		dos.writeBoolean(flag);
		dos.writeChar(sex);
		dos.writeDouble(money);
		dos.close();
		
		System.out.println(dis.readUTF());
		System.out.println(dis.readInt());
		System.out.println(dis.readBoolean());
		System.out.println(dis.readChar());
		System.out.println(dis.readDouble());
		dis.close();
		*/
		
//		������(�������ʹ�ü��������л���ȡ)
		
		File file=new File("D:\\doc\\doc.txt");
		
		Girl girl1=new Girl("��Ƽ", 16);
		Girl girl2=new Girl("����", 20);
		FileOutputStream fos=new FileOutputStream(file);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		FileInputStream fis=new FileInputStream(file);
		ObjectInputStream ois=new ObjectInputStream(fis);
		
//		�����д
//		oos.writeObject(girl1);
//		oos.writeObject(girl2);
//		oos.close();
		
		ArrayList<Girl> girls1=new ArrayList<Girl>();
		girls1.add(girl1);
		girls1.add(girl2);
		oos.writeObject(girls1);
		oos.close();
		
//		����Ķ�
		
//		Girl girl3= (Girl) ois.readObject();
//		Girl girl4=(Girl) ois.readObject();
//		System.out.println(girl3);
//		System.out.println(girl4);
//		ois.close();
		
		ArrayList<Girl>	girls2=(ArrayList<Girl>) ois.readObject();
		for (Girl girl : girls2) {
			System.out.println(girl);
		}
		ois.close();
	}
}

class HWFileUtil{
	
	public static void copyFile(File srcFile,File dscFile) throws IOException{
		
		FileInputStream fis=new FileInputStream(srcFile);
		FileOutputStream fos=new FileOutputStream(dscFile);
		long second=System.currentTimeMillis();
//		һ���ֽ�һ���ֽڶ�ȡ
		/*
		int data=-1;
		while((data=fis.read())!=-1){
			fos.write(data);
		}
		*/
		
//		�Զ���һ���ֽ���������Ϊ�����������ļ���д������ʹ�ã�
		
		byte[] buffer=new byte[1024*1024];//1M����
		int len=0;//ʵ�ʶ������ֽڳ���
		while ((len=fis.read(buffer))!=-1) {
			fos.write(buffer, 0, len);
		}
		
		
		//ʹ�û�������д
		/*
		BufferedInputStream bis=new BufferedInputStream(fis);
		BufferedOutputStream bos=new BufferedOutputStream(fos);
//		��һ��һ����д����������������������д���ļ���
		int data=0;
		while((data=bis.read())!=-1){ //����������
			bos.write(data);//д��������������������д���ļ���
		}
		bis.close();
		bos.close();
		*/
		System.out.println("������ɣ�"+(System.currentTimeMillis()-second)+" ms");
		fis.close();
		fos.close();
		
	}
}