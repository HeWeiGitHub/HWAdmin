package myutildemo;

import java.util.LinkedList;

public class ThreadDemo {
	public static void main(String[] args) throws InterruptedException {
		Mythread thread1=new Mythread("Mythread1");
//		thread1.setName("Mythread1");
		Mythread thread2=new Mythread();
		thread2.setName("Mythread2");
		
//		优先级最大10，最小1，正常5，优先级越高获得执行的机会越大，但不是绝对的最先执行
		System.out.println(thread1.getPriority());
		System.out.println(thread2.getPriority());
//		thread2.setPriority(10);
//		设置为守护线程，随着主线程结束而结束
//		thread1.setDaemon(true);
		
//		1.Join线程会抢先执行，执行完之后其他线程再执行，
//		2.join语句必须在join线程启动之后，其他线程启动之前,
//		3.在使用join之后yield将无法暂停线程
		
		thread2.start();
		thread2.interrupt();
//		thread2.stop();
//		thread2.join(); //在Thread2启动之后，在Thread1启动之前，确定Thread2执行完后再执行Thread1
		thread1.start();
//		thread1.join();
		
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		/***********************************多线程编程************************/
//		System.out.println("****************************多线程*********************");
//		两个线程使用的是同一个rannable对象的内存资源
//		MyRunnable runnable=new MyRunnable();
//		Thread thread3=new Thread(runnable,"rannableThread1");
////		thread3.setName("rannableThread1");
//		thread3.start();
//		Thread thread4=new Thread(runnable,"rannableThread2");
////		thread3.setName("rannableThread2");
//		thread4.start();
//		
//		Bank bank=new Bank(500);
//		BankThread bankThread1=new BankThread(bank, "银行1");
//		bankThread1.start();
//		BankThread bankThread2=new BankThread(bank, "银行2");
//		bankThread2.start();
		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		/***********************************死锁************************/
//		System.out.println("****************************死锁*********************");
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
		
		/***********************************线程键通信（生产者和消费者）************************/
//		System.out.println("****************************线程键通信（生产者和消费者）*********************");
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
			//每取两次票然后让给其他人取
			if (ticket%3==0) {
				//使当前线程释放资源，进入就绪状态,与其他线程一起抢资源，
				//有可能释放资源之后立马又抢到,所以会出现调用yield之后还是本线程执行
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
 * <li>2017年12月17日</li>
 * <li>建议使用Runnable接口</li>
 * <li>避免单继承的局限性</li>
 * <li>适合资源共享</li>
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
		System.out.println(Thread.currentThread().getName()+ "取了："+bank.getMoney(200)+"元");
	}
}

class Bank{
	private int money=500;
	public Bank(int money) {
		this.money = money;
	}
//	synchronized可以用在方法申明上，也可以以代码块的形式申明,每个对象都有自己对应的一个锁
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
				System.out.println("余额："+money+"元");
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

/*************************************生产者和消费之*************************/
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
	
//	向篮子放苹果
	public void push(Apple apple){
//		放够5个一等等待消费者消费
		if (basket.size()==5) {
			try {
//				notify();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//每.5秒放一次苹果
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		basket.addFirst(apple);
		System.out.println("存放："+apple);
		notify();
	}
//	向篮子取苹果
	public void pop(){
//		篮子取空后等待生产者生产
		if (basket.size()==0) {
			try {
//				notify();
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//每.5秒取一次苹果
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("取走："+basket.removeFirst());
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
1）wait()、notify()和notifyAll()方法是本地方法，并且为final方法，无法被重写，它们操作同一个对象的锁，每个对象都有唯一的一个锁。

2）当前线程必须拥有此对象的monitor（即锁），才能调用某个对象的wait()方法能让当前线程阻塞，--wait方法后面的代码不再执行
   （这种阻塞是通过提前释放synchronized锁，重新去请求锁导致的阻塞，这种请求必须有其他线程通过notify()或者notifyAll（）唤醒重新竞争获得锁）

3）调用某个对象的notify()方法能够唤醒一个正在等待这个对象的monitor的线程，如果有多个线程都在等待这个对象的monitor，则只能唤醒其中一个线程；
（notify()或者notifyAll()方法并不是真正释放锁，必须等到synchronized方法或者语法块执行完才真正释放锁）

4）调用notifyAll()方法能够唤醒所有正在等待这个对象的monitor的线程，唤醒的线程获得锁的概率是随机的，取决于cpu调度

*/