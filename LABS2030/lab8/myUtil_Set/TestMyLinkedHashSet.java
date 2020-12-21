package myUtil_Set;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestMyLinkedHashSet extends TestSet {
	
	@Override
	protected MySet<String> loadSet() {
		return new MyLinkedHashSet<String>();
	}
	
	@Override
	protected MySet<Integer> loadSetInt() {
		return new MyLinkedHashSet<Integer>();
	}
	
	@Test
	public void testAddLinkedInt() {
		// add a new method for ordering
		MySet <Integer> s = loadSetInt();
		s.add(7);
		s.add(3);
		s.add(5);
		s.add(6);
		s.add(8);
		
		String exp = "[ 7 3 5 6 8 ]";  // insertion order
		 
		assertEquals(exp, s.toString());
	
	}
	
	@Test
	public void testAddLinked() {
		// add a new method for ordering
		MySet <String> s = loadSet();
		s.add("Alice");
		s.add("Sue");
		s.add("Bob");
		s.add("Cindy");
		s.add("Joe");
		
		String exp = "[ Alice Sue Bob Cindy Joe ]";  // insertion order
		 
		assertEquals(exp, s.toString());
	
	}
	
	@Test
	public void testSetUnionLinked() {
		MySet <String> s = loadSet();
		s.add("Alice");
		s.add("Sue");
		s.add("Bob");
		
		MySet <String> s2 = loadSet();
		s2.add("Cindy");
		s2.add("Sue");
		s2.add("Joe");
		
		s.addAll(s2);
		String exp = "[ Alice Sue Bob Cindy Joe ]";  // insertion order
		assertEquals(exp, s.toString());
	} 
	
 }