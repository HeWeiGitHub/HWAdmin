package myutildemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * @author Administrator
 * <ul>
 * <li>2017��12��21��</li>
 * <li>pakage��myutildemo</li>
 * </ul>
 * @description
 * <p>java��̬����</p>
 */
public class JavaCompilerDemo {
	public static void main(String[] args) throws IOException {
		
		JavaCompiler compiler=ToolProvider.getSystemJavaCompiler();
		
//		java���ļ���̬����
		String path=new File("").getCanonicalPath();
		System.out.println(path);
		int result= compiler.run(null, null, null, path+"/src/myutildemo/Girl.java");
		System.out.println(result==0?"����ɹ�":"����ʧ��");
		
//		java�ַ���������ʱ�ļ���̬����
		String code="class Compdemo{public static void main(String[] args){System.out.println(\"����java��ʱ�ļ���̬�������\");}}";
		FileWriter fw=new FileWriter("C:\\Users\\Administrator\\Desktop\\javassist\\Compdemo.java");
		fw.write(code);
		fw.close();
		System.out.println("�ļ����ɳɹ�");
		int rs=compiler.run(null, null, null, "C:\\Users\\Administrator\\Desktop\\javassist\\Compdemo.java");
		System.out.println(rs==0?"����ɹ�":"����ʧ��");
		
//		ͨ������ִ��java�ֽ����ļ�
//		Runtime run=Runtime.getRuntime();
//		Process process=run.exec("java -cp C:/Users/Administrator/Desktop/javassist Compdemo");
//		InputStream in=process.getInputStream();
//		Scanner scanner=new Scanner(in);
//		while (scanner.hasNextLine()) {
//			String info=scanner.nextLine();
//			System.out.println(info);
//		}
//		BufferedReader reader=new BufferedReader(new InputStreamReader(in));
//		String info="";
//		while((info=reader.readLine())!=null){
//			System.out.println(info);
//		}
		
//		ͨ����������ͷ���ִ��
		try {
			URL[] urls=new URL[]{new URL("file:/"+"C:/Users/Administrator/Desktop/javassist/")};
			URLClassLoader loader=new URLClassLoader(urls);
			Class<?> c=loader.loadClass("Compdemo");
			Method method=c.getMethod("main",String[].class);
			method.setAccessible(true);
			method.invoke(null, (Object)new String[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
