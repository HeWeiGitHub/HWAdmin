package myutildemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptEngineDemo {
	public static void main(String[] args) throws ScriptException, NoSuchMethodException, IOException {
		ScriptEngineManager sem=new ScriptEngineManager();
		ScriptEngine engine=sem.getEngineByName("javascript");

		engine.put("msg", "hw is a good man!");
		String str="var user={name:'hw',age:23,schools:['�廪��ѧ','������ѧ']};";
		str+="print(user.schools);print(msg);";
		engine.eval(str);
		engine.eval("msg='��ΰ�Ǹ������ˣ�'");
		engine.eval("print(msg)");
		System.out.println(engine.get("msg"));
		
		//java����js
		String addf="function sum(a,b){return a+b;};";
		engine.eval(addf);
//		��õ��ýӿ�
		Invocable jsInvoc=(Invocable)engine;
		Object result= jsInvoc.invokeFunction("sum", new Object[]{15,23});
		System.out.println(result);
		
		//js����java
		StringBuilder jscode=new StringBuilder();
//		jscode.append("importPackage(java.util);");java��jdk1.6�汾ʹ��
		jscode.append("var list=java.util.Arrays.asList([\"������ѧ��\",\"������ѧ\",\"�廪��ѧ\"]);");
		jscode.append("java.lang.System.out.println('js����java');");
		engine.eval(jscode.toString());
		List<String> list=(List<String>) engine.get("list");
		for (String string : list) {
			System.out.println(string);
		}
		
		//����js�ű��ļ�
		
		//���Դ��Ŀ¼�µ��ļ�
		File directory = new File("");// ����Ϊ��
		String str1 = directory.getCanonicalPath();
		String path=str1+"\\src\\myutildemo\\script.js";
//		FileReader reader=new FileReader(new File(path));
		FileInputStream inputStream=new FileInputStream(path);
		InputStreamReader reader = new InputStreamReader(
				inputStream, "UTF-8");
		engine.eval(reader);
		reader.close();
	}

}
