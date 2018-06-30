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

         // ��һ�֣���ȡ����صĸ�·��   D:\git\daotie\daotie\target\classes
         File f = new File(this.getClass().getResource("/").getPath());
         System.out.println(f);
         return f.toString();

     }
	public String getDirectory2() throws IOException {
		
		// ��ȡ��ǰ������ڹ���·��; ������ӡ�/��  ��ȡ��ǰ��ļ���Ŀ¼  D:\git\daotie\daotie\target\classes\my
		File f2 = new File(this.getClass().getResource("").getPath());
		System.out.println(f2);
		return f2.toString();
		
	}
	public String getDirectory3() throws IOException {
		
		// �ڶ��֣���ȡ��Ŀ·��    D:\git\daotie\daotie
		File directory = new File("");// ����Ϊ��
		String courseFile = directory.getCanonicalPath();
		System.out.println(courseFile);
		return courseFile;
	}
	public String getDirectory4() throws IOException {
		
		// �����֣�  file:/D:/git/daotie/daotie/target/classes/
		URL xmlpath = this.getClass().getClassLoader().getResource("");
		System.out.println(xmlpath);
		return xmlpath.toString();
	}
	public String getDirectory5() throws IOException {
		
		// �����֣� D:\git\daotie\daotie
		System.out.println(System.getProperty("user.dir"));
		return System.getProperty("user.dir");
		/*
		 * ����� C:\Documents and Settings\Administrator\workspace\projectName
		 * ��ȡ��ǰ����·��
		 */
		
	}
	
	public String getDirectory6() throws IOException {
		
		// �����֣�  ��ȡ���е���·�� ����jar����·��
		System.out.println(System.getProperty("java.class.path"));
		return System.getProperty("java.class.path");
		
	}
}