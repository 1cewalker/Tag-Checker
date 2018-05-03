
public class Main {

	public static void main(String[] args) {
		
		Solution sol = new Solution();
		
		System.out.println(sol.checkTag("The following text<C><B>is centred and in boldface</B></C>"));
		System.out.println(sol.checkTag("<B>This <\\g>is <B>boldface</B> in <<*> a</B> <\6> <<d>sentence"));
		System.out.println(sol.checkTag("<B><C> This should be centred and in boldface, but the tags are wrongly nested </B></C>"));
		System.out.println(sol.checkTag("<B>This should be in boldface, but there is an extra closing tag</B></C>"));
		System.out.println(sol.checkTag("<B><C>This should be centred and in boldface, but there is a missing closing tag</C>"));

	}

}
