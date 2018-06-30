package myutildemo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;

/**
 * @author hw
 *         <ul>
 *         <li>2017��12��20��</li>
 *         <li>pakage��myutildemo</li>
 *         </ul>
 * @description
 *              <p>
 * 				java�ֽ��������javassist���ʹ�ã�
 *              </p>
 */
public class JavassistDemo {
	public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

//		test0();
//		test1();
//		test2();
		test3();
		
	}
	
	/**
	 * <p>�����ಢд�뵽�ļ�<p/>
	 * void
	 */
	public static void test0() throws CannotCompileException, NotFoundException{
		ClassPool pool = ClassPool.getDefault();

//		����������class�ļ�
		CtClass emp = pool.makeClass("myutildemo.ssist.emp");

		CtField no = CtField.make("private int no;", emp);
		CtField name = CtField.make("private String name;", emp);
		emp.addField(no);
		emp.addField(name);

		CtMethod setNo = CtMethod.make("public void setNo(int no){this.no=no;}", emp);
		CtMethod getNo = CtMethod.make("public int getNo(){return no;}", emp);
		CtMethod setName = CtMethod.make("public void setName(String name){this.name=name;}", emp);
		CtMethod getName = CtMethod.make("public String getName(){return name;}", emp);
		CtMethod toStr = CtMethod.make("public String toString(){return \"emp{no:\"+this.no+\",name:\"+this.name+\"}\";}", emp);
		emp.addMethod(setNo);
		emp.addMethod(getNo);
		emp.addMethod(setName);
		emp.addMethod(getName);
		emp.addMethod(toStr);

		CtConstructor constructor = new CtConstructor
		(new CtClass[] { CtClass.intType, pool.get("java.lang.String") },emp);
//		$1,$2��ʾ��һ���͵ڶ�������,$0��ʾthis����
		constructor.setBody("{this.no=$1;this.name=$2;}");
		emp.addConstructor(constructor);
		
		try {
			emp.writeFile("F:\\JavaWorkspace\\baseJavaStudy\\src");
			System.out.println("���ļ����ɳɹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws NotFoundException
	 * @throws IOException
	 * @throws CannotCompileException
	 * 
	 * <p>�����������Ϣ<p/>
	 * void
	 */
	public static void test1() throws NotFoundException, IOException, CannotCompileException {
		ClassPool pool=ClassPool.getDefault();
		CtClass cla=pool.get("myutildemo.ssist.emp");
		
		byte[] bytes=cla.toBytecode();
		System.out.println(Arrays.toString(bytes));
		System.out.println(cla.getName());
		System.out.println(cla.getSimpleName());
		System.out.println(cla.getSuperclass());
		System.out.println(cla.getInterfaces());
		
		CtMethod[] methods=cla.getDeclaredMethods();
		for (CtMethod ctMethod : methods) {
			System.out.println(ctMethod.getName());
		}
	}
	
	/**
	 * @throws NotFoundException
	 * @throws IOException
	 * @throws CannotCompileException
	 * 
	 * <p>�������޸���ӷ���������<p/>
	 * void
	 */
	public static void test2() throws NotFoundException, CannotCompileException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		ClassPool pool=ClassPool.getDefault();
		CtClass cla=pool.get("myutildemo.ssist.emp");
		
		CtMethod add=CtNewMethod.make("public int add(int a,int b){return a+b;}", cla);
//		CtMethod add=new CtMethod(CtClass.intType, "add", new CtClass[]{CtClass.intType,CtClass.intType},cla);
//		add.setModifiers(Modifier.PUBLIC);
//		add.setBody("{return $1+$2;}");
		cla.addMethod(add);
		
		try {
			cla.writeFile("F:\\JavaWorkspace\\baseJavaStudy\\src");
			System.out.println("���ļ����ɳɹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
		CtMethod[] methods1=cla.getDeclaredMethods();
		for (CtMethod ctMethod : methods1) {
			System.out.println(ctMethod.getName());
		}
		
		Class c=cla.toClass();
//		Object object=c.newInstance();
		Object obj=c.getConstructor(new Class[]{int.class,String.class}).newInstance(new Object[]{23,"��ΰ"});
		System.out.println(obj);
		Method method=c.getDeclaredMethod("add", new Class[]{int.class,int.class});
		Object result= method.invoke(obj, new Object[]{23,17});
		System.out.println(result);
		
	}
	
	/**
	 * @throws NotFoundException
	 * @throws CannotCompileException
	 * <p>���Բ���</p>
	 * void
	 */
	public static void test3() throws NotFoundException, CannotCompileException{
		ClassPool pool=ClassPool.getDefault();
		
		CtClass cla=pool.get("myutildemo.ssist.emp");
		
		CtField f=new CtField(CtClass.intType, "age", cla);
		f.setModifiers(Modifier.PUBLIC);
		cla.addField(f);
		cla.addMethod(CtNewMethod.getter("getAge", f));
		cla.addMethod(CtNewMethod.setter("setAge", f));
		
		try {
			cla.writeFile("F:\\JavaWorkspace\\baseJavaStudy\\src");
			System.out.println("���ļ����ɳɹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
		CtMethod[] methods1=cla.getDeclaredMethods();
		for (CtMethod ctMethod : methods1) {
			System.out.println(ctMethod.getName());
		}
	}
}
