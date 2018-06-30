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
		String str="var user={name:'hw',age:23,schools:['清华大学','北京大学']};";
		str+="print(user.schools);print(msg);";
		engine.eval(str);
		engine.eval("msg='何伟是个好男人！'");
		engine.eval("print(msg)");
		System.out.println(engine.get("msg"));
		
		//java调用js
		String addf="function sum(a,b){return a+b;};";
		engine.eval(addf);
//		获得调用接口
		Invocable jsInvoc=(Invocable)engine;
		Object result= jsInvoc.invokeFunction("sum", new Object[]{15,23});
		System.out.println(result);
		
		//js引用java
		StringBuilder jscode=new StringBuilder();
//		jscode.append("importPackage(java.util);");java的jdk1.6版本使用
		jscode.append("var list=java.util.Arrays.asList([\"北京尚学堂\",\"北京大学\",\"清华大学\"]);");
		jscode.append("java.lang.System.out.println('js调用java');");
		engine.eval(jscode.toString());
		List<String> list=(List<String>) engine.get("list");
		for (String string : list) {
			System.out.println(string);
		}
		
		//引入js脚本文件
		
		//获得源码目录下的文件
		File directory = new File("");// 参数为空
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
