package myutildemo;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class CollectionsDemo {
	public static void main(String[] args) {
		CollectionsDemo cd=new CollectionsDemo();
		LinkedList<String> users=new LinkedList<String>();
		users.add("anine");
		users.add("davie");
		users.add("mary");
		users.add("boby");
		cd.showList(users);
		System.out.println("*******************************");
		Collections.swap(users, 2, 1);
		cd.showList(users);
		System.out.println("*******************************");
		//实现comparator接口类，比较字符串按长度排序
		Comparator<String> strcmp=new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length()>o2.length()) {
					return 1;
				}else if (o1.length()<o2.length()) {
					return -1;
				}
				return 0;
			}
		};
		Collections.sort(users,strcmp);
		cd.showList(users);
		System.out.println("*******************************");
		//默认按字符字典顺序排序
		Collections.sort(users);
		cd.showList(users);
		System.out.println("*******************************");
		System.out.println(Collections.binarySearch(users, "mary"));
		System.out.println("*******************************");
		Collections.reverse(users);
		cd.showList(users);
		System.out.println("****************顺序打乱***************");
//		打乱顺序
		Collections.shuffle(users);
		cd.showList(users);
		System.out.println("****************顺序打乱***************");
		Collections.fill(users,"何伟");
		cd.showList(users);
	}
	
	private <T> void showList  (LinkedList<T> list){
		for (T e : list) {
			System.out.println(e);
		}
	}

}