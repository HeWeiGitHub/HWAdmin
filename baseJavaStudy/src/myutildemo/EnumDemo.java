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
//			���õ���Person.P1.toSting()����
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
 * <p>ö�����Ͷ���class����</p>
 */
enum Color{
	//ö��������б�������public static final���Σ������޲ι��췽��,�������ʱ�͵��ù��캯��
	RED,GREEN,BLUE;
//	RED(),GREEN(),BLUE();
//	ö�ٵĹ��캯��ֻ����private��Ĭ����private
	Color() {
		System.out.println("���캯��");
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