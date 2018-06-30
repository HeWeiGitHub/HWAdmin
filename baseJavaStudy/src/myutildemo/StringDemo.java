package myutildemo;

public class StringDemo {
	public static void main(String[] args) {
		String str=" hello ";//在字符串池中，引用地址不变
		String str1=new String("hello ");//新分配地址
		System.out.println(str==str1);
		System.out.println(str.equals(str1));
		
//		字符串连接，可以用+号直接连接
		str.concat("world");
		System.out.println(str);
		str=str.concat("world");
		System.out.println(str);
		str=str.trim();
		System.out.println(str);
//		字典顺序比较
		System.out.println(str1.compareTo(str));
		
		String[] strcp=str.split(" ");
		for (String string : strcp) {
			System.out.println(string);
		}
		
		char[] chars=str.toCharArray();
		for (char c : chars) {
			System.out.println(c);
		}
		
//		底层都是字符数组作为缓冲,且拥有相同的方法
		
//		线程安全
		StringBuffer stringBuffer=new StringBuffer();
//		线程不安全
		StringBuilder stringBuilder=new StringBuilder();
		
		stringBuffer.append(str1)
		.append(1)
		.append(" ")
		.append('a')
		.append(" ")
		.append(true)
		.append(" ")
		.append(" stringBuffer");
		
		stringBuilder.append(str1)
		.append(1)
		.append(" ")
		.append('a')
		.append(" ")
		.append(true)
		.append(" ")
		.append(" stringBuilder");
		stringBuilder.replace(8, 9, "b");
		
		System.out.println(stringBuffer.toString()+" "+stringBuffer.length()+" "+stringBuffer.capacity());
		System.out.println(stringBuilder);
	}
}
