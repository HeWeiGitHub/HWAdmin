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
//		文件流
		/*
		File dscFile=new File("C:\\Users\\Administrator\\Desktop\\Hita - 殇别离.mp3");
		File srcFile=new File("D:\\doc\\Hita - 殇别离.mp3");
		try {
			HWFileUtil.copyFile(srcFile, dscFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
//		字节流
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
//		第二个参数为true，以追加的方式写入文件，就是在第二次打开文件写的时候写到末尾，而不会覆盖文件的原有内容
		FileOutputStream fos=new FileOutputStream("D:\\doc\\doc.txt",true);
		bos.writeTo(fos);
		fos.close();
		*/
		
		/*
//		数据流
		File file=new File("D:\\doc\\doc.txt");
		FileInputStream fis=new FileInputStream(file);
		FileOutputStream fos=new FileOutputStream(file);
		DataInputStream dis=new DataInputStream(fis);
		DataOutputStream dos=new DataOutputStream(fos);
		
		String string="hello world 何伟";
		int i=10;
		boolean flag=true;
		char sex='男';
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
		
//		对象流(多个对象使用集合作序列化读取)
		
		File file=new File("D:\\doc\\doc.txt");
		
		Girl girl1=new Girl("杨萍", 16);
		Girl girl2=new Girl("灵纹", 20);
		FileOutputStream fos=new FileOutputStream(file);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		FileInputStream fis=new FileInputStream(file);
		ObjectInputStream ois=new ObjectInputStream(fis);
		
//		对象的写
//		oos.writeObject(girl1);
//		oos.writeObject(girl2);
//		oos.close();
		
		ArrayList<Girl> girls1=new ArrayList<Girl>();
		girls1.add(girl1);
		girls1.add(girl2);
		oos.writeObject(girls1);
		oos.close();
		
//		对象的读
		
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
//		一个字节一个字节读取
		/*
		int data=-1;
		while((data=fis.read())!=-1){
			fos.write(data);
		}
		*/
		
//		自定义一个字节数组来作为缓冲区进行文件读写（建议使用）
		
		byte[] buffer=new byte[1024*1024];//1M缓冲
		int len=0;//实际读到的字节长度
		while ((len=fis.read(buffer))!=-1) {
			fos.write(buffer, 0, len);
		}
		
		
		//使用缓冲流读写
		/*
		BufferedInputStream bis=new BufferedInputStream(fis);
		BufferedOutputStream bos=new BufferedOutputStream(fos);
//		先一个一个读写到缓冲区，缓冲区满后再写到文件中
		int data=0;
		while((data=bis.read())!=-1){ //读到缓冲区
			bos.write(data);//写到缓冲区，缓冲区满后写到文件中
		}
		bis.close();
		bos.close();
		*/
		System.out.println("复制完成："+(System.currentTimeMillis()-second)+" ms");
		fis.close();
		fos.close();
		
	}
}