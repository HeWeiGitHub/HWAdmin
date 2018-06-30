package myutildemo;

import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

public class SetDemo {
	public static void main(String[] args) {
		//hashSet�ײ�ά����һ��hashMap,�õ���hashMap��key
//		HashSet<Teacher> teachers=new HashSet<Teacher>();
//		HashSet<Teacher> teachers=new HashSet<Teacher>(10);
		HashSet<Teacher> teachers=new HashSet<Teacher>(10,0.75f);
		Teacher teacher1=new Teacher("��ΰ", 18);
		Teacher teacher2=new Teacher("С��", 16);
		Teacher teacher3=new Teacher("��Ƽ", 20);
		Teacher teacher4=new Teacher("�˴�", 17);
		
		teachers.add(teacher1);
		teachers.add(teacher2);
		teachers.add(teacher3);
		teachers.add(teacher4);
		teachers.add(new Teacher("�˴�", 17));
		
		System.out.println(teachers.size());
		System.out.println(teachers.isEmpty());
		
		for (Teacher teacher : teachers) {
			System.out.println(teacher);
		}
		
		System.out.println("**********************treeset*****************");
		Comparator<Teacher> teachcomp=new Comparator<Teacher>() {

			@Override
			public int compare(Teacher o1, Teacher o2) {
				if (o1.getAge()>o2.getAge()) {
					return 1;
				}else if (o1.getAge()<o2.getAge()) {
					return -1;
				}
				return 0;
			}
		};
		
		//TreeSet�ײ�ά����һ��TreeMap,�õ���TreeMap��key
//		Ĭ�ϰ�����洢��֧�ֿ��ټ���
		TreeSet<Teacher> teachers1=new TreeSet<Teacher>(teachcomp);
		teachers1.add(teacher1);
		teachers1.add(teacher2);
		teachers1.add(teacher3);
		teachers1.add(teacher4);
		for (Teacher teacher : teachers1) {
			System.out.println(teacher);
		}
	}
}

class Teacher/* implements Comparable<Teacher>*/{
	
	private String name;
	private Integer age;
	
	public Teacher(String name, Integer age) {
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
		Teacher other = (Teacher) obj;
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
		return "Teacher [name=" + name + ", age=" + age + "]";
	}
//	�˷����ṩ��TreeMap���ڰ�key������Ƚ�����
	/*@Override
	public int compareTo(Teacher o) {
		if (this.age-o.age>0) {
			return 1;
		}else if (this.age-o.age<0) {
			return -1;
		}
		return 0;
	}*/
}