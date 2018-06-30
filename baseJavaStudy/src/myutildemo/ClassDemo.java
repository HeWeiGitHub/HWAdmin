package myutildemo;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassDemo {
	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		/*
		 * // 第一种 Girl girl=new Girl("女孩", 10); Class<?>
		 * girlClass=girl.getClass();
		 * 
		 * System.out.println(girl.getAge());
		 * System.out.println(girlClass.getName());
		 * System.out.println(girlClass.getSuperclass().getName());
		 */

		
////		第二种 
//		Class<Girl> girlClass = Girl.class;
//		Girl girl = null;
//		girl = girlClass.newInstance();
//		try {
//			Constructor<?> constructor = girlClass.getConstructor(new Class[] {});
//			girl = (Girl) constructor.newInstance(new Object[] {});
//			// girl=(Girl)girlClass.getConstructor(newClass[]{String.class,Integer.class}).newInstance(newObject[]{"何伟",23});
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		}
//		System.out.println(girl);
//		System.out.println(girlClass.getName());
//		System.out.println(girlClass.getSuperclass().getName());
//		 

//		第三种
		// Class<Girl> girlClass=(Class<Girl>) Class.forName("myutildemo.Girl");
		Class<?> girlClass = Class.forName("myutildemo.Girl");
		System.out.println(girlClass.getName());
		System.out.println(girlClass.getSuperclass().getName());
		Girl girl = null;
		// 调用默认的无参构造方法
		// girl=(Girl)girlClass.newInstance();
		try {
			// 通过class对象调用带参构造方法
			Constructor<?> constructor = girlClass.getConstructor(new Class[] { String.class, Integer.class });
			girl = (Girl) constructor.newInstance(new Object[] { "何伟", 23 });
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		System.out.println(girl);
		
		Method method = null;
		String methStr=null;
		try {
//			getMethod获取公有的方法
			// 获取类的public修饰的toString方法，参数为空,所以参数对应的类型数组是new Class[] {}
//			method = girlClass.getMethod("toString", new Class[] {});
//			getDeclaredMethod获取任何一个方法，包括私有的
			// 获取类的private修饰的show方法，参数为String类型，所以参数对应的类型数组是new Class[]{String.class}
			method=girlClass.getDeclaredMethod("show", new Class[]{String.class});
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
		System.out.println(method);
		System.out.println(method.getName());
		try {
			//调用girl对象的公有的toString方法,如果调用静态方法对象参数可以传null
//			methStr=(String) method.invoke(girl, new Object[]{});
			//调用girl对象的私有的show方法之前先设置setAccessible为true
			method.setAccessible(true);
			methStr=(String) method.invoke(girl, new Object[]{"何伟"});
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println(methStr);
		
		Method[] methods=girlClass.getDeclaredMethods();
		for (Method mt : methods) {
			System.out.println(mt.getName()+"---"+mt.getModifiers()+"---"+mt.getReturnType());
//			System.out.println(mt);
		}
		
		try {
			Field field=girlClass.getDeclaredField("name");
			field.setAccessible(true);
			System.out.println(field.get(girl));
			field.set(girl, "蓝天迷梦");
			System.out.println(field.get(girl));
			
//			Field field=girlClass.getField("PI");
//			System.out.println(field.getName());
//			System.out.println(field.getInt(girl));
//			field.setInt(girl, 10);
//			System.out.println(field.getInt(girl));
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		/*
		 * 第四种
		 * //基本类型获取Class对象，基本类型（int）没有父类,基本类型的包装类（Integer）有父类 // Class<?>
		 * intClass=int.class; // Class<?> intClass=Integer.TYPE; Class<?>
		 * intClass=Integer.class; System.out.println(intClass.getName());
		 * System.out.println(intClass.getSuperclass().getName());
		 */
		
//		Class<?> strClass=String.class;
		Class<?> strClass=Class.forName("java.lang.String");
		Object array=Array.newInstance(strClass, 5);
		Array.set(array, 2, "二");
		System.out.println(Array.get(array, 2));
	}
}
