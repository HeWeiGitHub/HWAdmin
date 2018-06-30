package myutildemo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@MyInterface("AnnotationDemo")
public class AnnotationDemo {
	
//	@SuppressWarnings({"unchecked"})
	public static void main(String[] args) throws ClassNotFoundException {
		/*
		1.先获得类的Class
		2.判断类的class有没有指定的注解，有的话获取类的注解，获取注解信息
		3.根据class获得类的方法，循环判断方法有没有指定的注解，有的话获取注解，再获取注解的信息（类属性类似）
		*/
//		Class<?> userclass=Class.forName("myutildemo.User");
		Class<?> userclass=User.class;
		
//		获得类注解信息
		boolean flag=userclass.isAnnotationPresent(MyInterface.class);
		if (flag) {
			MyInterface myinf=userclass.getAnnotation(MyInterface.class);
			System.out.println(myinf.value());
		}
		
//		获得属性注解信息		
		Field[] fields=userclass.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(ValueInterface.class)) {
				ValueInterface vf= field.getAnnotation(ValueInterface.class);
				System.out.println(vf.value());
			}
		}
		
//		获得方法注解信息
		
		Method[] methods=userclass.getDeclaredMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(Model.class)) {
				Model model=method.getAnnotation(Model.class);
				for (String name : model.name()) {
					System.out.print(name+",");
				}
				System.out.println();
				System.out.println(model.age());
				System.out.println(model.color());
			}
		}
		
	}
	
//	注解简单使用示例
	
	@ValueInterface("何伟")
	private String name;
	
//	@MyInterface
//	@NameInterface()
	@NameInterface(name="何伟")
//	@ValueInterface(value = "何伟")
	@Model(color=Colors.RED,name = { "何伟","杨萍" },age=18)
	public void showInfo(){
		
	}
}

/*(注解可以看成特殊的实现了Annotation接口的类),注解和类属于同一级别*/

/*
@Override 标记重载父类方法，如果没有重载父类方法编译通不过
@Deprecated 标记过期的，弃用的方法，使用时给出警告
@SuppressWarnings 压制警告，取消编译器警告

*/

//Meta Annotation 元注解,对注解进行注释的注解
//@Target(ElementType.METHOD) 说明注解用在什么元素上
//@Retention(RetentionPolicy.SOURCE) 说明注解信息在哪个阶段被保留
//@Documented 说明注解可以被添加进doc文档中
//@Inherited 说明注解会被子类集成下来

//自定义注解

//空注解称为标记注解，可以标记类的任何成员包括类
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface MyInterface{
	String value();//value成员可以直接在括号中填写值
}

@interface NameInterface{
	String name();
}

@Target(value=ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface ValueInterface{
	String value();
}

//通过反射获取注解信息时Retention类型必须是RUNTIME
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
@Inherited
@interface Model{
	String[] name();
	int age() default 0;
//	枚举类型的成员
	Colors color();
}

@MyInterface("Annotation的User类测试")
class User{
	
	@ValueInterface("这是用户值")
	private String userValue;
	
	@Model(name={"何伟","杨萍"},age=10,color=Colors.RED)
	public void test(){
		System.out.println("这是User类的test");
	}
}