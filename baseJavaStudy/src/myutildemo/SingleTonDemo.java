package myutildemo;

//���ģʽ-����ģʽ

public class SingleTonDemo {
	public static void main(String[] args) {
		// SingleTon singleTon=SingleTon.getInstance();
		// SingleTon singleTon1=SingleTon.getInstance();
		// System.out.println(singleTon==singleTon1);

		// ͨ���ĵ���ģʽ�����̰߳�ȫ����
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
 * ����ģʽ class SingleTon{ private static SingleTon singleTon=new SingleTon();
 * private SingleTon(){ System.out.println("����ģʽ"); } public static SingleTon
 * getInstance(){ return singleTon; } }
 */

// ͨ���ĵ���ģʽ�����̰߳�ȫ����
// ����ģʽ
/*
 * class SingleTon{ private static SingleTon singleTon=null; private
 * SingleTon(){ System.out.println("����ģʽ"); } public static SingleTon
 * getInstance(){ if (singleTon==null) { singleTon=new SingleTon(); } return
 * singleTon; } }
 */
// �̰߳�ȫ������ģʽ
class SingleTon {
	private static SingleTon singleTon = null;

	private SingleTon() {
		System.out.println("����ģʽ");
	}

	public static synchronized SingleTon getInstance() {
		if (singleTon == null) {
			singleTon = new SingleTon();
		}
		return singleTon;
	}
}