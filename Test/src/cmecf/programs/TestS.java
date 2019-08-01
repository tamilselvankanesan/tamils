package cmecf.programs;

public class TestS {

	public static void main(String[] args) {
		MyStatic s1 = new TestS.MyStatic();
		System.out.println(s1.toString());
		
		MyStatic s2 = new TestS.MyStatic();
		System.out.println(s2.toString());
	}

	public static class MyStatic{
		
	}
}
