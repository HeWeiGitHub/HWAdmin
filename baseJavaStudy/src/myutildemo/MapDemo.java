package myutildemo;

import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class MapDemo {
	public static void main(String[] args) {
		Student stu1=new Student("��ΰ", 18);
		Student stu2=new Student("�˴�", 20);
		Student stu3=new Student("����", 19);
		Student stu4=new Student("С��", 18);
		/*
		 �ײ�hash����Ԫ�ضԱȵ��������е�hashCode��equals������
		���û��ʵ������ø���object��hashCode��equals����
		ɢ��ӳ�䲻��֤˳���ò��䣬Ĭ������16����������0.75
		
		HashMap��Hashtable�ײ㶼����������ṹ����ʵ����map�ӿڣ�HashMap����null key��null value����hashtable������Hashtable���̰߳�ȫ��
		*/
//		HashMap<String, String> strmap=new HashMap<String,String>();
//		HashMap<String, String> strmap=new HashMap<String,String>(10);
//		����һ����ʼ����Ϊ10���������ӣ�����ʹ��Ϊ����ʱ����������Ϊ0.75��ɢ��ӳ��
//		HashMap<String, Student> strmap=new HashMap<String,Student>(10,0.75f);
		Hashtable<String, Student> strmap=new Hashtable<String,Student>(10,0.75f);
//		�������ظ���ֵ���ظ�
		strmap.put("stu1", stu1);
		strmap.put("stu1", stu1);
		strmap.put("stu2", stu2);
		strmap.put("stu3", stu3);
		strmap.put("stu4", stu4);
		
		System.out.println(strmap.size());
		System.out.println(strmap.isEmpty());
		System.out.println(strmap.containsKey("stu2"));
		System.out.println(strmap.containsValue(stu1));
		System.out.println(strmap.containsValue(new Student("��ΰ", 18)));
		
		for (String name : strmap.keySet()) {
			System.out.println(name);
			System.out.println(strmap.get(name));
		}
		strmap.remove("stu4");
		strmap.replace("stu3", new Student("����", 16));
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
		�����ӳ�䣬Ԫ��Ĭ�ϰ���ֵ��������
		��ֵ�ñȽ����õ�Comparator�ӿ�ʵ�����compare��Comparable�ӿڵ�compareTo������
		�ȵ���Comparator�����ComparatorΪnull�����Comparable
		�������ʱ�ṩ��Comparator��ʵ�ֻ������̳���Comparable�࣬����ʵ�ֵıȽϷ������бȽ�����
		������ټ���
		
		*/
//		TreeMap<String, Student> stuMap=new TreeMap<String,Student>();
		
		/*TreeMap<String, Student> stuMap=new TreeMap<String,Student>(strmap);
		
		for (Entry<String, Student> entry : stuMap.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}*/
		
//		Comparator�ӿڵ�ʵ���࣬����treeMap���������Ƚ�����
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
//	hashCode��equals�����ṩ��HashMap�����Ƚϼ�ֵ
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
//	�˷����ṩ��TreeMap���ڰ�key������Ƚ�����
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
