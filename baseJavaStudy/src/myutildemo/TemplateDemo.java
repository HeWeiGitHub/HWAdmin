package myutildemo;

import java.util.LinkedList;

public class TemplateDemo {
	public static void main(String[] args) {
		HwStack<String> strStack = new HwStack<String>();
		strStack.push("hello");
		strStack.push("world");
		strStack.push("何伟");
		strStack.push("你好");
		System.out.println(strStack.toString());
		strStack.pop();
		System.out.println(strStack.toString());

		for (String string : strStack.list()) {
			System.out.println(string);
		}

		SubClass<String, Integer, String> subClass = new SubClass<String, Integer, String>("何伟", 23, "宁夏固原");

		System.out.println(subClass.getTempData() + subClass.getSuperdata() + ":" + subClass.getSubData());

		NumberAction<Integer> numberAction = new NumberAction<Integer>();
		// ?通配符，匹配任何类型
		// NumberAction<?> numberAction1=new NumberAction<Integer>();
		// NumberAction<?> numberAction1=numberAction;
		// NumberAction<? extends Integer> numberAction2=new
		// NumberAction<Integer>();
		System.out.println(numberAction.add(1, 2));
		System.out.println(add(1, 2));
		int a=1;
		int b=3;
		int c=a+b;
		System.out.println(c);

	}
	//泛型方法在返回值前面申明
	private static <T extends Integer> int add(T e1, T e2) {
		return e1 + e2;
	}
}
//泛型类和接口在类名后申明
class NumberAction<T extends Integer> {

	public int add(T e1, T e2) {
		return e1 + e2;
	}

	public int comper(T e1, T e2) {
		return e1 - e2;
	}

	public int roundme(T e) {
		return Math.round(e.floatValue());
	}
}

class HwStack<T> {
	private LinkedList<T> container;

	public HwStack() {
		this.container = new LinkedList<T>();
	}

	public void push(T element) {
		container.addLast(element);
	}

	public T pop() {
		return container.removeLast();
	}

	public LinkedList<T> list() {
		return container;
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		for (T t : container) {
			stringBuilder.append(t.toString());
			if (!t.equals(container.get(container.size() - 1))) {
				stringBuilder.append(",");
			}
		}
		return stringBuilder.toString();
	}
}

interface myTemplate<S> {

	S getTempData();

	void setTempData(S data);

	void showData();
}

class SuperClass<T> {
	private T superdata;

	public SuperClass(T superdata) {
		this.superdata = superdata;
	}

	public T getSuperdata() {
		return superdata;
	}

	public void setSuperdata(T superdata) {
		this.superdata = superdata;
	}
}

// 先继承父类再实现接口
class SubClass<S, T, U> extends SuperClass<T> implements myTemplate<S> {
	private U subData;
	private S tempdata;

	public SubClass(S tempdata, T superData, U subData) {
		super(superData);
		this.tempdata = tempdata;
		this.subData = subData;
	}

	public U getSubData() {
		return subData;
	}

	public void setSubData(U subData) {
		this.subData = subData;
	}

	@Override
	public S getTempData() {
		return tempdata;
	}

	@Override
	public void setTempData(S data) {
		this.tempdata = data;
	}

	@Override
	public void showData() {
		System.out.println(this.tempdata);

	}

}
