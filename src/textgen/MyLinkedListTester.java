/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		// test removing the first element from list1 
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		// test removing an element that is out of bounds
		try {
			int b = list1.remove(2);
			fail("Check out of bounds");
		}
		
		catch (IndexOutOfBoundsException e) {
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		
		// test adding on the empty list first for contents, and then out of bounds
		boolean a = emptyList.add(2);
		assertEquals("AddEnd: check element 0 is correct ", (Integer)2, emptyList.get(0));
		assertEquals("AddEnd: check size is correct ", 1, emptyList.size());
		
		try {
			emptyList.get(1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		// test adding on a longer list list1 for contents
		boolean b = list1.add(129);
		assertEquals("AddEnd: check element 3 is correct ", (Integer)129, list1.get(3));
		assertEquals("AddEnd: check size is correct ", 4, list1.size());
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		// test on empty list
		assertEquals("Size: check if size is 0", (Integer)0, (Integer)emptyList.size());
		
		// test on adding an element to list1
		list1.add(100);
		assertEquals("Size: check if size is 4 after adding", (Integer)4, (Integer)list1.size());
		
		// testing on a list of strings
		shortList.add("C");
		assertEquals("Size: check if size is 3 after adding", (Integer)3, (Integer)shortList.size());
		
		// test removing an element from longerlist
		shortList.remove(1);
		assertEquals("Size: check if size is 2 after removing", (Integer)2, (Integer)shortList.size());
		
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		// test adding on the empty list first for some corner cases that should throw exceptions
		try {
			emptyList.add(1, 5);
			fail("Check out of bounds");
		}
		
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			emptyList.add(0, null);
			fail("Check null element");
		}
		
		catch (NullPointerException e) {
		}
		
		// test adding on the empty list for contents
		emptyList.add(0, 5);
		assertEquals("AddAtIndex: check element 0 is correct ", (Integer)5, emptyList.get(0));
		
		// test adding on list1 for contents, blackbox testing
		list1.add(1, 28);
		assertEquals("AddAtIndex: check element 1 is correct ", (Integer)28, list1.get(1));
		assertEquals("AddAtIndex: check element 2 is correct ", (Integer)21, list1.get(2));
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
	    // test on the longer list for contents
		longerList.set(2, 10);
		assertEquals("Set: check if element 2 is 10 ", (Integer)10, longerList.get(2));
		
		// test on the longer list for out of bounds, corner cases
		try {
			longerList.set(10, 2);
			fail("Check out of bounds");
		}
		
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			longerList.set(3, null);
			fail("Check null element");
		}
		
		catch (NullPointerException e) {
		}
	}
}
