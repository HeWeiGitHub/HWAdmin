package myutildemo;

import java.io.Serializable;
//Serializable接口用于序列化标记，没有什么实际方法（ObjectStream实例）
public class Girl implements Serializable {
	
	/**
	 * serialVersionUID序列版本号，使序列化和反序列化时不会出错，以IOStreamDemo中的对象读写为例
	 * 添加序列号以后，写进去对象的什么属性，读出来的就是什么属性，不会出错
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Integer age;
	public int PI=3;

	public Girl(){
	}
	public Girl(String name, Integer age) {
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
	
	// hashCode和equals方法提供给HashMap用来比较键值
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
		Girl other = (Girl) obj;
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
		return "Girl [name=" + name + ", age=" + age + "]";
	}
	
	@SuppressWarnings("unchecked")
	private String show(String owner){
		System.out.println("主人：" + owner);
		return "Girl [name=" + name + ", age=" + age + "]";
	}
}
