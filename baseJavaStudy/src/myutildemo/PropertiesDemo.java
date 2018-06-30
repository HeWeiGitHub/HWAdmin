package myutildemo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Properties;

public class PropertiesDemo {
	public static void main(String[] args) throws IOException {
//		Properties继承于HashTable，是线程安全的
		Properties prop=new Properties();
		prop.setProperty("name", "hw");
		prop.setProperty("age", "23");
		prop.setProperty("phone", "15509516226");
		prop.setProperty("addr", "宁夏银川");
		System.out.println(prop.getProperty("name"));
		System.out.println(prop.getProperty("age"));
		System.out.println(prop.getProperty("phone"));
		System.out.println(prop.getProperty("addr"));
		
//		获取当前项目路径
		PropertiesDemo pDemo=new PropertiesDemo();
		String classPath=pDemo.getProjectPath();
		System.out.println(classPath);
  
//		属性写文件
		PrintWriter pw=new PrintWriter(classPath+"\\src\\myutildemo\\user.properties");
//		PrintStream pw=new PrintStream(classPath+"\\src\\myutildemo\\user.properties");
		prop.list(pw);
		pw.close();
		
//		属性读文件
		Properties pr=new Properties();
		FileReader pi=new FileReader(classPath+"\\src\\myutildemo\\user.properties");
//		FileInputStream pi=new FileInputStream(classPath+"\\src\\myutildemo\\user.properties");
		pr.load(pi);
		pi.close();
		System.out.println(pr.getProperty("name"));
		System.out.println(pr.getProperty("age"));
		System.out.println(pr.getProperty("phone"));
		System.out.println(pr.getProperty("addr"));
	}
	
	public String getProjectPath() throws IOException {
		// 第二种：获取项目路径    D:\git\daotie\daotie
		File directory = new File("");// 参数为空
		String courseFile = directory.getCanonicalPath();
		return courseFile;
	}

}
