package myutildemo;

//设计模式-单例模式

public class SingleTonDemo {
	public static void main(String[] args) {
		// SingleTon singleTon=SingleTon.getInstance();
		// SingleTon singleTon1=SingleTon.getInstance();
		// System.out.println(singleTon==singleTon1);

		// 通常的单例模式存在线程安全问题
		SingleRunnable runnable = new SingleRunnable();
		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);
		t1.start();
		t2.start();

	}
}

class SingleRunnable implements Runnable {

	@Override
	public void run() {
		SingleTon.getInstance();

	}
}

/*
 * 饿汉模式 class SingleTon{ private static SingleTon singleTon=new SingleTon();
 * private SingleTon(){ System.out.println("单例模式"); } public static SingleTon
 * getInstance(){ return singleTon; } }
 */

// 通常的单例模式存在线程安全问题
// 懒汉模式
/*
 * class SingleTon{ private static SingleTon singleTon=null; private
 * SingleTon(){ System.out.println("单例模式"); } public static SingleTon
 * getInstance(){ if (singleTon==null) { singleTon=new SingleTon(); } return
 * singleTon; } }
 */
// 线程安全的懒汉模式
class SingleTon {
	private static SingleTon singleTon = null;

	private SingleTon() {
		System.out.println("单例模式");
	}

	public static synchronized SingleTon getInstance() {
		if (singleTon == null) {
			singleTon = new SingleTon();
		}
		return singleTon;
	}
}