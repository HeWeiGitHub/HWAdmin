package myutildemo;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
	public static void main(String[] args) throws UnknownHostException {
		
//		InetAddress ia=InetAddress.getLocalHost();
		InetAddress ia=InetAddress.getByName("SZNE494EV2FBEHF");
		System.out.println(ia);
		System.out.println(ia.getHostAddress());
		System.out.println(ia.getHostName());
	}
}
