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
		1.�Ȼ�����Class
		2.�ж����class��û��ָ����ע�⣬�еĻ���ȡ���ע�⣬��ȡע����Ϣ
		3.����class�����ķ�����ѭ���жϷ�����û��ָ����ע�⣬�еĻ���ȡע�⣬�ٻ�ȡע�����Ϣ�����������ƣ�
		*/
//		Class<?> userclass=Class.forName("myutildemo.User");
		Class<?> userclass=User.class;
		
//		�����ע����Ϣ
		boolean flag=userclass.isAnnotationPresent(MyInterface.class);
		if (flag) {
			MyInterface myinf=userclass.getAnnotation(MyInterface.class);
			System.out.println(myinf.value());
		}
		
//		�������ע����Ϣ		
		Field[] fields=userclass.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(ValueInterface.class)) {
				ValueInterface vf= field.getAnnotation(ValueInterface.class);
				System.out.println(vf.value());
			}
		}
		
//		��÷���ע����Ϣ
		
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
	
//	ע���ʹ��ʾ��
	
	@ValueInterface("��ΰ")
	private String name;
	
//	@MyInterface
//	@NameInterface()
	@NameInterface(name="��ΰ")
//	@ValueInterface(value = "��ΰ")
	@Model(color=Colors.RED,name = { "��ΰ","��Ƽ" },age=18)
	public void showInfo(){
		
	}
}

/*(ע����Կ��������ʵ����Annotation�ӿڵ���),ע���������ͬһ����*/

/*
@Override ������ظ��෽�������û�����ظ��෽������ͨ����
@Deprecated ��ǹ��ڵģ����õķ�����ʹ��ʱ��������
@SuppressWarnings ѹ�ƾ��棬ȡ������������

*/

//Meta Annotation Ԫע��,��ע�����ע�͵�ע��
//@Target(ElementType.METHOD) ˵��ע������ʲôԪ����
//@Retention(RetentionPolicy.SOURCE) ˵��ע����Ϣ���ĸ��׶α�����
//@Documented ˵��ע����Ա���ӽ�doc�ĵ���
//@Inherited ˵��ע��ᱻ���༯������

//�Զ���ע��

//��ע���Ϊ���ע�⣬���Ա������κγ�Ա������
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface MyInterface{
	String value();//value��Ա����ֱ������������дֵ
}

@interface NameInterface{
	String name();
}

@Target(value=ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface ValueInterface{
	String value();
}

//ͨ�������ȡע����ϢʱRetention���ͱ�����RUNTIME
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
@Inherited
@interface Model{
	String[] name();
	int age() default 0;
//	ö�����͵ĳ�Ա
	Colors color();
}

@MyInterface("Annotation��User�����")
class User{
	
	@ValueInterface("�����û�ֵ")
	private String userValue;
	
	@Model(name={"��ΰ","��Ƽ"},age=10,color=Colors.RED)
	public void test(){
		System.out.println("����User���test");
	}
}