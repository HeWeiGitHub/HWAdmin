package myutildemo;

public class StringDemo {
	public static void main(String[] args) {
		String str=" hello ";//���ַ������У����õ�ַ����
		String str1=new String("hello ");//�·����ַ
		System.out.println(str==str1);
		System.out.println(str.equals(str1));
		
//		�ַ������ӣ�������+��ֱ������
		str.concat("world");
		System.out.println(str);
		str=str.concat("world");
		System.out.println(str);
		str=str.trim();
		System.out.println(str);
//		�ֵ�˳��Ƚ�
		System.out.println(str1.compareTo(str));
		
		String[] strcp=str.split(" ");
		for (String string : strcp) {
			System.out.println(string);
		}
		
		char[] chars=str.toCharArray();
		for (char c : chars) {
			System.out.println(c);
		}
		
//		�ײ㶼���ַ�������Ϊ����,��ӵ����ͬ�ķ���
		
//		�̰߳�ȫ
		StringBuffer stringBuffer=new StringBuffer();
//		�̲߳���ȫ
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
