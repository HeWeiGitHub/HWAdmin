package myutildemo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Vector;

public class ListDemo {
	public static void main(String[] args) {
//		Vector和ArrayList结构相同，底层都是数组实现，vector是线程安全的,
//		内部是默认创建初始大小为10的object数组
		Vector<Animal> users=new Vector<Animal>(5);
//		ArrayList<Animal> users=new ArrayList<Animal>();
//		ArrayList<Animal> users=new ArrayList<Animal>(5);
		
		Animal animal1=new Animal("灵纹", 1);
		Animal animal2=new Animal("杨萍", 2);
		Animal animal3=new Animal("马青", 3);
		Animal animal4=new Animal("宜春", 4);
		users.add(animal1);
		users.add(animal2);
		users.add(animal3);
		users.add(animal4);
		System.out.println(users.isEmpty());
		//ArrayList无此方法
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
		contains方法在底层比对的时候调用了对象的equals方法，
		如果对象类没有重载equals方法则调用object的equals方法，
		比对的是两个引用变量是否引用了同一个地址，而不是对象的内容
		所以要在自定义类中实现equals方法，对内容进行对比
		*/
//		可以注释掉equals和hashCode方法看结果，默认对比，两个Animal的实例引用不同的地址，所以返回false
		System.out.println(users.contains(new Animal("杨萍", 2)));
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
		System.out.println(users1.contains(new Animal("杨萍", 2)));
		
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
