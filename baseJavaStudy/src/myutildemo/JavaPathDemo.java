package myutildemo;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JavaPathDemo {
	
	public static void main(String[] args) {
		URL url= JavaPathDemo.class.getClassLoader().getResource("script.js");
		System.out.println(url);
		String path= JavaPathDemo.class.getResource("/").getPath();
		System.out.println(path);
		
	}
	public String getDirectory1() throws IOException {

         // 第一种：获取类加载的根路径   D:\git\daotie\daotie\target\classes
         File f = new File(this.getClass().getResource("/").getPath());
         System.out.println(f);
         return f.toString();

     }
	public String getDirectory2() throws IOException {
		
		// 获取当前类的所在工程路径; 如果不加“/”  获取当前类的加载目录  D:\git\daotie\daotie\target\classes\my
		File f2 = new File(this.getClass().getResource("").getPath());
		System.out.println(f2);
		return f2.toString();
		
	}
	public String getDirectory3() throws IOException {
		
		// 第二种：获取项目路径    D:\git\daotie\daotie
		File directory = new File("");// 参数为空
		String courseFile = directory.getCanonicalPath();
		System.out.println(courseFile);
		return courseFile;
	}
	public String getDirectory4() throws IOException {
		
		// 第三种：  file:/D:/git/daotie/daotie/target/classes/
		URL xmlpath = this.getClass().getClassLoader().getResource("");
		System.out.println(xmlpath);
		return xmlpath.toString();
	}
	public String getDirectory5() throws IOException {
		
		// 第四种： D:\git\daotie\daotie
		System.out.println(System.getProperty("user.dir"));
		return System.getProperty("user.dir");
		/*
		 * 结果： C:\Documents and Settings\Administrator\workspace\projectName
		 * 获取当前工程路径
		 */
		
	}
	
	public String getDirectory6() throws IOException {
		
		// 第五种：  获取所有的类路径 包括jar包的路径
		System.out.println(System.getProperty("java.class.path"));
		return System.getProperty("java.class.path");
		
	}
}