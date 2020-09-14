package spelling;

import java.util.HashSet;
import java.util.Set;

public class SetTest {
	public static void main(String[] args) {
		Set<Character> mySet = new HashSet<Character>();
		mySet.add('a');
		mySet.add('e');
		char myChar = 'e';
		if (mySet.contains(myChar)) {
			System.out.println("It works!!!");
		}
	}
}
