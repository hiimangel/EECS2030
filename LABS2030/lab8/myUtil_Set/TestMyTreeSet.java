package myUtil_Set;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestMyTreeSet extends TestSet {
	
	@Override
	protected MySet<String> loadSet() {
		return new MyTreeSet<String>();
	}
	
	@Override
	protected MySet<Integer> loadSetInt() {
		return new MyTreeSet<Integer>();
	}
	
	
	@Test
	public void testAddTree() {
		// add a new method for ordering
		MySet <String> s = loadSet();
		s.add("Alice");
		s.add("Sue");
		s.add("Bob");
		s.add("Cindy");
		s.add("Joe");
		
		String exp = "[ Alice Bob Cindy Joe Sue ]";  // natural order
		 
		assertEquals(exp, s.toString());
	
	}
	
	@Test
	public void testSetUnionTree() {
		MySet <String> s = loadSet();
		s.add("Alice");
		s.add("Sue");
		s.add("Bob");
		
		MySet <String> s2 = loadSet();
		s2.add("Cindy");
		s2.add("Sue");
		s2.add("Joe");
		
		s.addAll(s2);
		String exp = "[ Alice Bob Cindy Joe Sue ]";  // insertion order
		assertEquals(exp, s.toString());
	} 
	
	@Test
	public void testAddTreeInt() {
		// add a new method for ordering
		MySet <Integer> s = loadSetInt();
		s.add(7);
		s.add(3);
		s.add(5);
		s.add(6);
		s.add(8);
		
		String exp = "[ 3 5 6 7 8 ]";  // natural order
		 
		assertEquals(exp, s.toString());
	
	}
	@Test
	public void testSetUnionTreeInt() {
		MySet <Integer> s = loadSetInt();
		s.add(7);
		s.add(3);
		s.add(5);
		
		MySet <Integer> s2 = loadSetInt();
		s2.add(6);
		s2.add(3);
		s2.add(5);
		
		s.addAll(s2);
		String exp = "[ 3 5 6 7 ]";  // natural order
		assertEquals(exp, s.toString());
	} 
	
	
 }