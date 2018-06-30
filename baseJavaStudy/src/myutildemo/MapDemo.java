package myutildemo;

import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class MapDemo {
	public static void main(String[] args) {
		Student stu1=new Student("何伟", 18);
		Student stu2=new Student("宜春", 20);
		Student stu3=new Student("灵纹", 19);
		Student stu4=new Student("小荣", 18);
		/*
		 底层hash表中元素对比调用了类中的hashCode和equals方法，
		如果没有实现则调用根类object的hashCode和equals方法
		散列映射不保证顺序恒久不变，默认容量16，加载因子0.75
		
		HashMap和Hashtable底层都是数组链表结构，都实现了map接口，HashMap允许null key和null value，而hashtable不允许，Hashtable是线程安全的
		*/
//		HashMap<String, String> strmap=new HashMap<String,String>();
//		HashMap<String, String> strmap=new HashMap<String,String>(10);
//		创建一个初始容量为10，加载因子（容量使用为多少时增加容量）为0.75的散列映射
//		HashMap<String, Student> strmap=new HashMap<String,Student>(10,0.75f);
		Hashtable<String, Student> strmap=new Hashtable<String,Student>(10,0.75f);
//		键不能重复，值可重复
		strmap.put("stu1", stu1);
		strmap.put("stu1", stu1);
		strmap.put("stu2", stu2);
		strmap.put("stu3", stu3);
		strmap.put("stu4", stu4);
		
		System.out.println(strmap.size());
		System.out.println(strmap.isEmpty());
		System.out.println(strmap.containsKey("stu2"));
		System.out.println(strmap.containsValue(stu1));
		System.out.println(strmap.containsValue(new Student("何伟", 18)));
		
		for (String name : strmap.keySet()) {
			System.out.println(name);
			System.out.println(strmap.get(name));
		}
		strmap.remove("stu4");
		strmap.replace("stu3", new Student("灵纹", 16));
		for (Student student : strmap.values()) {
			System.out.println(student);
		}
		
		Set<Entry<String, Student>> entrys=strmap.entrySet();
		for (Entry<String, Student> entry : entrys) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
		System.out.println("******************TreeMap***************************");
		/*
		红黑树映射，元素默认按键值升序排序。
		键值得比较是用的Comparator接口实现类的compare和Comparable接口的compareTo方法，
		先调用Comparator，如果Comparator为null则调用Comparable
		如果创建时提供了Comparator的实现或键对象继承了Comparable类，则按照实现的比较方法进行比较排序
		允许快速检索
		
		*/
//		TreeMap<String, Student> stuMap=new TreeMap<String,Student>();
		
		/*TreeMap<String, Student> stuMap=new TreeMap<String,Student>(strmap);
		
		for (Entry<String, Student> entry : stuMap.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}*/
		
//		Comparator接口的实现类，用于treeMap用来给键比较排序
		Comparator<Student> stucomp=new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				if (o1.getAge()-o2.getAge()>0) {
					return 1;
				}else if (o1.getAge()-o2.getAge()<0) {
					return -1;
				}
				return 0;
			}
		};
		
//		TreeMap<Student, String> stuMap=new TreeMap<Student, String>();
		TreeMap<Student, String> stuMap=new TreeMap<Student, String>(stucomp);
		stuMap.put(stu1, "stu1");
		stuMap.put(stu2, "stu2");
		stuMap.put(stu3, "stu3");
		stuMap.put(stu4, "stu4");
		
		for (Entry<Student, String> entry : stuMap.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
		
	}

}

class Student/* implements Comparable<Student>*/{
	
	private String name;
	private Integer age;
	
	public Student(String name, Integer age) {
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
//	hashCode和equals方法提供给HashMap用来比较键值
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
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
		return "Student [name=" + name + ", age=" + age + "]";
	}
//	此方法提供给TreeMap用于按key的年龄比较排序
	/*@Override
	public int compareTo(Student o) {
		if (this.age-o.age>0) {
			return 1;
		}else if (this.age-o.age<0) {
			return -1;
		}
		return 0;
	}*/
}
