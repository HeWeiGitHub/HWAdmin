package myutildemo;
import java.util.Arrays;
/**
 * @author HW
 * <ul>
 * <li>2017��12��12��</li>
 * <li>myutildemo</li>
 * </ul>
 * description
 * <p>Arrays������ʹ��ʵ��</p>
 */
public class ArraysDemo {
	
	/**
	 * @param args
	 * TODO
	 * void
	 */
	public static void main(String[] args) {
		int[] src1={1,2,3,4,5,6};
		/*int[] src2=Arrays.sort(src1,0,5,new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2-o1;
			}
		});*/
		int[] dsc1=Arrays.copyOf(src1, 10);
		for (int i : dsc1) {
			System.out.println(i);
		}
		System.out.println("*************************");
//		����System�����鿽��
		System.arraycopy(src1, 0, dsc1, 6, 4);
		for (int i : dsc1) {
			System.out.println(i);
		}
		System.out.println("*************************");
		int[] dsc2=Arrays.copyOfRange(src1, 0, 4);
		for(int i:dsc2){
			System.out.println(i);
		}
		System.out.println("*************************");
		System.out.println(Arrays.equals(dsc1, dsc2));
//		�ҵ������±�
		System.out.println(Arrays.binarySearch(dsc1,5));
		
	}
	
	
}
