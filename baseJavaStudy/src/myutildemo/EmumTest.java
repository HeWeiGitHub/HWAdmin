package myutildemo;

public class EmumTest {
	public static void main(String[] args) {
		
		Colors[] colors=Colors.values();
		for (Colors c : colors) {
			System.out.println(c);
		}
	}
}

enum Colors{
	RED("red",255,0,0,1),GREEN("green",0,255,0,1),BLUE("blue",0,0,255,1);
	private int red;
	private int green;
	private int blue;
	private float opacity;
	private String name;
	Colors(String name,int red,int green,int blue,float opacity){
		this.name=name;
		this.red=red;
		this.green=green;
		this.blue=blue;
		this.opacity=opacity;
		System.out.println("ππ‘Ï£∫"+name);
	}
	
	@Override
	public String toString() {
		return name+":{"+red+","+green+","+blue+","+opacity+"}";
	}
}