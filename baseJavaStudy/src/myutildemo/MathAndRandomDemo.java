package myutildemo;

import java.util.Random;

public class MathAndRandomDemo {
	public static void main(String[] args) {
		
		float f=5.21f;
		System.out.println(Math.PI);
		System.out.println(Math.floor(f));
		System.out.println(Math.ceil(f));
		System.out.println(Math.round(f));
		
		
//		����0-1֮���double��
		System.out.println(Math.random());
		
		Random random=new Random();
//		Random random=new Random(10);
		
		System.out.println(random.nextInt(10));
		System.out.println(random.nextBoolean());
		System.out.println(random.nextFloat());
	}

}
