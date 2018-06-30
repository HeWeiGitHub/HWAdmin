package myutildemo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Vector;

public class ListDemo {
	public static void main(String[] args) {
//		Vector��ArrayList�ṹ��ͬ���ײ㶼������ʵ�֣�vector���̰߳�ȫ��,
//		�ڲ���Ĭ�ϴ�����ʼ��СΪ10��object����
		Vector<Animal> users=new Vector<Animal>(5);
//		ArrayList<Animal> users=new ArrayList<Animal>();
//		ArrayList<Animal> users=new ArrayList<Animal>(5);
		
		Animal animal1=new Animal("����", 1);
		Animal animal2=new Animal("��Ƽ", 2);
		Animal animal3=new Animal("����", 3);
		Animal animal4=new Animal("�˴�", 4);
		users.add(animal1);
		users.add(animal2);
		users.add(animal3);
		users.add(animal4);
		System.out.println(users.isEmpty());
		//ArrayList�޴˷���
		System.out.println(users.capacity());
		System.out.println(users.size());
		System.out.println(users.contains(animal1));
		
		
		ListIterator<Animal> listIterator=users.listIterator();
		while (listIterator.hasNext()) {
			Animal animal = (Animal) listIterator.next();
			System.out.println(animal);
			if (animal.equals(users.get(users.size()-1))) {
				while (listIterator.hasPrevious()) {
					Animal string1 = (Animal) listIterator.previous();
					System.out.println(string1);
				}
				break;
			}
		}
		System.out.println("*************************************************");
		users.remove(animal3);
		Iterator<Animal> iterator= users.iterator();
		while (iterator.hasNext()) {
			Animal string = iterator.next();
			System.out.println(string);
		}
		System.out.println("*************************************************");
		System.out.println(users.firstElement());
		System.out.println(users.lastElement());
		
		/*
		contains�����ڵײ�ȶԵ�ʱ������˶����equals������
		���������û������equals���������object��equals������
		�ȶԵ����������ñ����Ƿ�������ͬһ����ַ�������Ƕ��������
		����Ҫ���Զ�������ʵ��equals�����������ݽ��жԱ�
		*/
//		����ע�͵�equals��hashCode�����������Ĭ�϶Աȣ�����Animal��ʵ�����ò�ͬ�ĵ�ַ�����Է���false
		System.out.println(users.contains(new Animal("��Ƽ", 2)));
		System.out.println("*********************ArrayList****************************\n");
		
		LinkedList<Animal> users1=new LinkedList<Animal>();
		users1.add(animal1);
		users1.add(animal2);
		users1.add(animal3);
		users1.add(animal4);
		System.out.println(users1.size());
		System.out.println(users1.isEmpty());
		System.out.println(users1.getFirst());
		System.out.println(users1.getLast());
		System.out.println(users1.contains(new Animal("��Ƽ", 2)));
		
		ListIterator<Animal> li=users1.listIterator(0);
		while (li.hasNext()) {
			Animal animal = (Animal) li.next();
			System.out.println(animal);
			if (animal.equals(users1.getLast())) {
				while (li.hasPrevious()) {
					Animal item = (Animal) li.previous();
					System.out.println(item);
				}
				break;
			}
		}
		
		System.out.println("*************************************************\n");
		users1.pollFirst();
		System.out.println(users1);
	}
	
}

class Animal{
	
	private String name;
	private Integer age;
	
	public Animal(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Animal [name=" + name + ", age=" + age + "]";
	}
	
}
