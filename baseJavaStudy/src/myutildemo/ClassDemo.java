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
		 * // ��һ�� Girl girl=new Girl("Ů��", 10); Class<?>
		 * girlClass=girl.getClass();
		 * 
		 * System.out.println(girl.getAge());
		 * System.out.println(girlClass.getName());
		 * System.out.println(girlClass.getSuperclass().getName());
		 */

		
////		�ڶ��� 
//		Class<Girl> girlClass = Girl.class;
//		Girl girl = null;
//		girl = girlClass.newInstance();
//		try {
//			Constructor<?> constructor = girlClass.getConstructor(new Class[] {});
//			girl = (Girl) constructor.newInstance(new Object[] {});
//			// girl=(Girl)girlClass.getConstructor(newClass[]{String.class,Integer.class}).newInstance(newObject[]{"��ΰ",23});
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

//		������
		// Class<Girl> girlClass=(Class<Girl>) Class.forName("myutildemo.Girl");
		Class<?> girlClass = Class.forName("myutildemo.Girl");
		System.out.println(girlClass.getName());
		System.out.println(girlClass.getSuperclass().getName());
		Girl girl = null;
		// ����Ĭ�ϵ��޲ι��췽��
		// girl=(Girl)girlClass.newInstance();
		try {
			// ͨ��class������ô��ι��췽��
			Constructor<?> constructor = girlClass.getConstructor(new Class[] { String.class, Integer.class });
			girl = (Girl) constructor.newInstance(new Object[] { "��ΰ", 23 });
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
//			getMethod��ȡ���еķ���
			// ��ȡ���public���ε�toString����������Ϊ��,���Բ�����Ӧ������������new Class[] {}
//			method = girlClass.getMethod("toString", new Class[] {});
//			getDeclaredMethod��ȡ�κ�һ������������˽�е�
			// ��ȡ���private���ε�show����������ΪString���ͣ����Բ�����Ӧ������������new Class[]{String.class}
			method=girlClass.getDeclaredMethod("show", new Class[]{String.class});
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
		System.out.println(method);
		System.out.println(method.getName());
		try {
			//����girl����Ĺ��е�toString����,������þ�̬��������������Դ�null
//			methStr=(String) method.invoke(girl, new Object[]{});
			//����girl�����˽�е�show����֮ǰ������setAccessibleΪtrue
			method.setAccessible(true);
			methStr=(String) method.invoke(girl, new Object[]{"��ΰ"});
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
			field.set(girl, "��������");
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
		 * ������
		 * //�������ͻ�ȡClass���󣬻������ͣ�int��û�и���,�������͵İ�װ�ࣨInteger���и��� // Class<?>
		 * intClass=int.class; // Class<?> intClass=Integer.TYPE; Class<?>
		 * intClass=Integer.class; System.out.println(intClass.getName());
		 * System.out.println(intClass.getSuperclass().getName());
		 */
		
//		Class<?> strClass=String.class;
		Class<?> strClass=Class.forName("java.lang.String");
		Object array=Array.newInstance(strClass, 5);
		Array.set(array, 2, "��");
		System.out.println(Array.get(array, 2));
	}
}
