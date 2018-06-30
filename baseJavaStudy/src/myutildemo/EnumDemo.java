package myutildemo;

public class EnumDemo {
	public static void main(String[] args) {
		
		Color[] colors=Color.values();
		for (Color color : colors) {
			System.out.println(color);
		}
		System.out.println(Color.RED.toString());
		
		Person[] persons=Person.values();
		for (Person person : persons) {
			System.out.println(person);
		}
		
		Person p1=Person.P1;
		
		switch(p1){
		case P1:
//			调用的是Person.P1.toSting()方法
			System.out.println(Person.P1);
			break;
		case P2:
			System.out.println(Person.P2);
			break;
			
		}
		
	}
}

/**
 * 
 * @description
 * <p>枚举类型都是class类型</p>
 */
enum Color{
	//枚举类对象列表，都是以public static final修饰，调用无参构造方法,在类加载时就调用构造函数
	RED,GREEN,BLUE;
//	RED(),GREEN(),BLUE();
//	枚举的构造函数只能是private，默认是private
	Color() {
		System.out.println("构造函数");
	}
}

enum Person{
	P1("hw",23),P2("mqq",16);
	private String name;
	private int age;
	
	private Person(String name,int age) {
		this.name=name;
		this.age=age;
	}
	
	public String toString(){
		return name+"--"+age;
	}
}