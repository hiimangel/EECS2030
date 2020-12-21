package myUtil_Set;

import static org.junit.Assert.*;

import org.junit.Test;

public abstract class TestSet {
	
	protected abstract MySet<String> loadSet();
	protected abstract MySet<Integer> loadSetInt();
	
	@Test
	public void testEmptySize() {
		MySet<String> s = loadSet();
		assertEquals(0, s.size());
		assertTrue(s.isEmpty());
	}
	
	@Test
	public void testOneItemSizeContain() {
		MySet<String> s = loadSet();
		s.add("A");
		assertSame(1, s.size());
		assertFalse(s.isEmpty());
		assertTrue(s.contains("A"));
		assertFalse(s.contains("a"));
	}
	
	@Test
	public void testOneItemSizeContainInt() {
		MySet<Integer> s = loadSetInt();
		s.add(new Integer(2));
		assertSame(1, s.size());
		assertFalse(s.isEmpty());
		assertTrue(s.contains(2));
		assertFalse(s.contains(4));
	}
	
	@Test
	public void testFiveItemAddSize() {
		MySet <String> s = loadSet();
		s.add("Alice");
		s.add("Sue");
		s.add("Bob");
		s.add("Cindy");
		s.add("Joe");
		
		assertSame(5, s.size());
		assertTrue(s.contains("Alice"));
		assertTrue(s.contains("Sue"));
		assertTrue(s.contains("Bob"));
		assertTrue(s.contains("Cindy"));
		assertTrue(s.contains("Joe"));
		assertTrue( !s.contains("Judy"));
		
		// add duplicate
		s.add("Cindy");
		s.add("Joe");
		assertSame(5, s.size());
		
		
		s.clear();
		assertSame(0, s.size());
		assertTrue(s.isEmpty());
			
	}
	
	
	
	@Test
	public void testRemove() {
		MySet <String> s = loadSet();
		s.add("Alice");
		s.add("Sue");
		s.add("Bob");
		s.add("Cindy");
		s.add("Joe");
		
		s.remove("Judy");
		assertSame(5, s.size());
		
		 
		s.remove("Cindy");
		assertSame(4, s.size());
		assertTrue( !s.contains("Cindy"));
		
		s.remove("Joe");
		assertEquals(3, s.size());
		assertFalse( s.contains("Joe"));
	} 
	
	@Test
	public void testSetUnion() {
		MySet <String> s = loadSet();
		s.add("Alice");
		s.add("Sue");
		s.add("Bob");
		
		MySet <String> s2 = loadSet();
		s2.add("Cindy");
		s2.add("Sue");
		s2.add("Joe");
		
		s.addAll(s2);
		assertEquals(5, s.size());
		assertTrue( s.contains("Alice"));
		assertTrue( s.contains("Sue"));
		assertTrue( s.contains("Bob"));
		assertTrue( s.contains("Cindy"));
		assertTrue( s.contains("Joe"));
	} 
	
	@Test
	public void testSetUnionInt() {
		MySet <Integer> s = loadSetInt();
		s.add(7);
		s.add(3);
		s.add(5);
		
		MySet <Integer> s2 = loadSetInt();
		s2.add(6);
		s2.add(5);
		s2.add(3);
		
		s.addAll(s2);
		assertSame(4, s.size());
	} 
	
	
		 
	
}

