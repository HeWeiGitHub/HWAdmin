package myutildemo;

import java.util.LinkedList;

public class ThreadDemo {
	public static void main(String[] args) throws InterruptedException {
		Mythread thread1=new Mythread("Mythread1");
//		thread1.setName("Mythread1");
		Mythread thread2=new Mythread();
		thread2.setName("Mythread2");
		
//		���ȼ����10����С1������5�����ȼ�Խ�߻��ִ�еĻ���Խ�󣬵����Ǿ��Ե�����ִ��
		System.out.println(thread1.getPriority());
		System.out.println(thread2.getPriority());
//		thread2.setPriority(10);
//		����Ϊ�ػ��̣߳��������߳̽���������
//		thread1.setDaemon(true);
		
//		1.Join�̻߳�����ִ�У�ִ����֮�������߳���ִ�У�
//		2.join��������join�߳�����֮�������߳�����֮ǰ,
//		3.��ʹ��join֮��yield���޷���ͣ�߳�
		
		thread2.start();
		thread2.interrupt();
//		thread2.stop();
//		thread2.join(); //��Thread2����֮����Thread1����֮ǰ��ȷ��Thread2ִ�������ִ��Thread1
		thread1.start();
//		thread1.join();
		
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		/***********************************���̱߳��************************/
//		System.out.println("****************************���߳�*********************");
//		�����߳�ʹ�õ���ͬһ��rannable������ڴ���Դ
//		MyRunnable runnable=new MyRunnable();
//		Thread thread3=new Thread(runnable,"rannableThread1");
////		thread3.setName("rannableThread1");
//		thread3.start();
//		Thread thread4=new Thread(runnable,"rannableThread2");
////		thread3.setName("rannableThread2");
//		thread4.start();
//		
//		Bank bank=new Bank(500);
//		BankThread bankThread1=new BankThread(bank, "����1");
//		bankThread1.start();
//		BankThread bankThread2=new BankThread(bank, "����2");
//		bankThread2.start();
		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		/***********************************����************************/
//		System.out.println("****************************����*********************");
//		Lock lock=new Lock();
//		LockThread1 lock1=new LockThread1(lock, "lock1");
//		LockThread2 lock2=new LockThread2(lock, "lock2");
//		lock1.start();
//		lock2.start();
		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		/***********************************�̼߳�ͨ�ţ������ߺ������ߣ�************************/
//		System.out.println("****************************�̼߳�ͨ�ţ������ߺ������ߣ�*********************");
//		Basket basket=new Basket();
//		Productor productor=new Productor(basket);
//		Consumer consumer=new Consumer(basket);
//		productor.start();
//		consumer.start();
	}
}


class Mythread extends Thread{
	private int ticket=10;
	
	public Mythread() {
	}
	public Mythread(String name) {
		super(name);
	}

	@Override
	public void run(){
		while(true){
			System.out.println(this.getId()+"--"+this.getName()
					+":"+ticket--);
			if (ticket<1) {
				break;
			}
			//ÿȡ����ƱȻ���ø�������ȡ
			if (ticket%3==0) {
				//ʹ��ǰ�߳��ͷ���Դ���������״̬,�������߳�һ������Դ��
				//�п����ͷ���Դ֮������������,���Ի���ֵ���yield֮���Ǳ��߳�ִ��
				Thread.yield();
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
		}
	}
}

/**
 * @author HW
 * <ul>
 * <li>2017��12��17��</li>
 * <li>����ʹ��Runnable�ӿ�</li>
 * <li>���ⵥ�̳еľ�����</li>
 * <li>�ʺ���Դ����</li>
 * </ul>
 * @description
 * <p>info</p>
 */
class MyRunnable implements Runnable{
	private int ticket=5;
	@Override
	public void run(){
		while(true){
			System.out.println(Thread.currentThread().
					getId()+"--"+ Thread.currentThread().getName()
					+":"+ticket--);
			if (ticket<1) {
				break;
			}
		}
	}
}


class BankThread extends Thread{
	
	private Bank bank=null;
	public BankThread(Bank bank,String name) {
		super(name);
		this.bank=bank;
	}
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+ "ȡ�ˣ�"+bank.getMoney(200)+"Ԫ");
	}
}

class Bank{
	private int money=500;
	public Bank(int money) {
		this.money = money;
	}
//	synchronized�������ڷ��������ϣ�Ҳ�����Դ�������ʽ����,ÿ���������Լ���Ӧ��һ����
	public /*synchronized*/ int getMoney(int number){
		synchronized(this) {
			if (number<0) {
				return -1;
			}else if (money-number<0) {
				return -2;
			}else{
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				money-=number;
				System.out.println("��"+money+"Ԫ");
				return number;
			}
		}
	}
}

class LockThread1 extends Thread{

	private Lock lock=null;
	
	public LockThread1(Lock lock,String name) {
		super(name);
		this.lock = lock;
	}
	@Override
	public void run(){
		lock.method1();
	}
}
class LockThread2 extends Thread{
	
	private Lock lock=null;
	
	public LockThread2(Lock lock,String name) {
		super(name);
		this.lock = lock;
	}
	@Override
	public void run(){
		lock.method2();
	}
}

class Lock{
	private Object o1=new Object();
	private Object o2=new Object();
	
	public void method1(){
		synchronized (o1) {
			try {
				System.out.println("method1=o1");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (o2) {
				System.out.println("method1=o2");
			}
		}
	}
	
	public void method2(){
		synchronized (o2) {
			try{
				System.out.println("method2-o2");
				Thread.sleep(1000);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			synchronized (o1) {
				System.out.println("method2-o2");
			}
		}
	}
}

/*************************************�����ߺ�����֮*************************/
class Productor extends Thread{
	
	private Basket basket=null;
	
	public Productor(Basket basket) {
		this.basket = basket;
	}

	@Override
	public void run(){
		basket.pushApple();
	}
}
class Consumer extends Thread{
	
	private Basket basket=null;
	
	public Consumer(Basket basket) {
		this.basket = basket;
	}
	
	@Override
	public void run(){
		basket.popApple();
	}
}

class Basket{
	private LinkedList<Apple> basket=new LinkedList<Apple>();
	
	public synchronized void pushApple(){
		for(int i=0;i<20;i++){
			Apple apple=new Apple(i);
			push(apple);
			if (basket.size()%5==0) {
				System.out.println("****************************");
			}
		}
	}
	
	public synchronized void popApple(){
		for(int i=0;i<20;i++){
			pop();
			if (basket.size()%5==0) {
				System.out.println("****************************");
			}
		}
	}
	
//	�����ӷ�ƻ��
	public void push(Apple apple){
//		�Ź�5��һ�ȵȴ�����������
		if (basket.size()==5) {
			try {
//				notify();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//ÿ.5���һ��ƻ��
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		basket.addFirst(apple);
		System.out.println("��ţ�"+apple);
		notify();
	}
//	������ȡƻ��
	public void pop(){
//		����ȡ�պ�ȴ�����������
		if (basket.size()==0) {
			try {
//				notify();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//ÿ.5��ȡһ��ƻ��
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("ȡ�ߣ�"+basket.removeFirst());
		notify();
	}
}
class Apple{
	private int id;
	
	public Apple(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Apple [id=" + id + "]";
	}
	
}
/*
1��wait()��notify()��notifyAll()�����Ǳ��ط���������Ϊfinal�������޷�����д�����ǲ���ͬһ�����������ÿ��������Ψһ��һ������

2����ǰ�̱߳���ӵ�д˶����monitor�������������ܵ���ĳ�������wait()�������õ�ǰ�߳�������--wait��������Ĵ��벻��ִ��
   ������������ͨ����ǰ�ͷ�synchronized��������ȥ���������µ�������������������������߳�ͨ��notify()����notifyAll�����������¾����������

3������ĳ�������notify()�����ܹ�����һ�����ڵȴ���������monitor���̣߳�����ж���̶߳��ڵȴ���������monitor����ֻ�ܻ�������һ���̣߳�
��notify()����notifyAll()���������������ͷ���������ȵ�synchronized���������﷨��ִ����������ͷ�����

4������notifyAll()�����ܹ������������ڵȴ���������monitor���̣߳����ѵ��̻߳�����ĸ���������ģ�ȡ����cpu����

*/