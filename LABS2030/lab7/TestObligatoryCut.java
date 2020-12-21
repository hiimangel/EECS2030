package studentSystem;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestObligatoryCut {

	 
	/**  about books **/
	@Test
	public void test01_ProfStringRep() {
		Prof  p1 = new Prof("John S Lee", "yu213", 5);
		 
		String exp = "[name:John S Lee, id:yu213, year:5]";
		assertEquals(exp, p1.toString());
		
		Prof  p2 = new Prof("Tracy T", "yu053", 2);
		 
		exp = "[name:Tracy T, id:yu053, year:2]";
		assertEquals(exp, p2.toString());
		
		Prof  p3 = new Prof("Son-hyang Pak", "yu8021", 6);
		 
		exp = "[name:Son-hyang Pak, id:yu8021, year:6]";
		assertEquals(exp, p3.toString());
	}
	
	@Test
	public void test02_ProfEqualHash() {
		Prof  p1 = new Prof("John S Lee", "yu213", 5);
		//System.out.println(p1.equals(p1)); 
		assertTrue(p1.equals(p1));
		
		Prof p0 = null; // null
		assertFalse(p1.equals(p0));  //should no exception
		
		Date d = new Date();
		String s = "Hello";
		assertFalse(p1.equals(d));  // should no exception
		assertFalse(p1.equals(s));  // should no exception
		
		
		Prof p2 = new Prof("John L", "yu213", 5);   // should be same
		assertTrue(p1.equals(p2));
		assertTrue(p1.hashCode() == p2.hashCode());
		 
		assertTrue(p2.equals(p1)); // reflextive
		
		Prof  p3 = new Prof("John S Lee", "yu203", 5); // id diff only
		assertFalse(p1.equals(p3));
		 
		//assertNotEquals(p1.hashCode(), p3.hashCode());   not required, but better to have
		
		Prof  p4 = new Prof("Tracy T", "yu053", 2); // id, year all diff
		assertFalse(p1.equals(p4));
		//assertNotEquals(p1.hashCode(), p4.hashCode());   not required, but better to have
		
		Prof  p5 = new Prof("John S Lee", "yu213", 8); // year diff only. may not be a good example in real life
		assertFalse(p1.equals(p5));
		//assertTrue(p1.hashCode() != p5.hashCode()); // not required, better to have
		
	}
	@Test
	public void test03_ProfEqualHash2() {
		Prof  p1 = new Prof("John S Lee", "yu213", 5);
		Prof  p2 = new Prof("John Lee", "yu213", 5);   
		Prof  p3 = new Prof("John S Lee", "yu203", 5);  
		Prof  p4 = new Prof("Tracy T", "yu053", 2);   
		Prof  p5 = new Prof("John S Lee", "yu213", 8);  
		 
		
		List<Prof> li = new ArrayList<>();
		li.add(p1); li.add(p4);  
		assertSame(2, li.size());  // added 
		
		assertTrue(li.contains(p2));
		assertFalse(li.contains(p3));
		assertFalse(li.contains(p5));		
	}
	
	@Test
	public void test04_ProfEqualHash3() {
		Prof  p1 = new Prof("John S Lee", "yu213", 5);
		Prof  p2 = new Prof("John L", "yu213", 5);
		Prof  p3 = new Prof("John S Lee", "yu203", 5);  
		Prof  p4 = new Prof("Tracy T", "yu053", 2);
		Prof  p5 = new Prof("John S Lee", "yu213", 8);
		 
		HashSet<Prof> li = new HashSet<>();
		li.add(p1); li.add(p4);    // no exception
		assertSame(2, li.size());  // added
		
		assertTrue(li.contains(p2)); // use hashing
		assertFalse(li.contains(p3)); // use hashing
		assertFalse(li.contains(p5)); // use hashing		
		li.add(p2);
		assertSame(2, li.size());  // not added
		li.add(p3);
		assertSame(3, li.size());  // added 
		li.add(p5);
		assertSame(4, li.size());  // added 
	}
	
	@Test
	public void test05_ProfCom() {
		Prof  p1 = new Prof("John S Lee", "yu213", 5);
		Prof  p2 = new Prof("John L", "yu213", 5);
		Prof  p3 = new Prof("John S Lee", "yu403", 12);  
		Prof  p4 = new Prof("Tracy T", "yu003", 5);  
		Prof  p5 = new Prof("John S Lee", "yu603", 5);  
		 
		
		int com = p1.compareTo(p2); 
		assertTrue(com == 0);
		
		com = p1.compareTo(p3); 
		assertTrue(com < 0);
		
		com = p1.compareTo(p4); 
		assertTrue(com > 0);
		
		com = p1.compareTo(p5); 
		assertTrue(com < 0);
		
		com = p3.compareTo(p1); 
		assertTrue(com > 0);
		
		com = p3.compareTo(p4); 
		assertTrue(com > 0);
		
		com = p5.compareTo(p3); 
		assertTrue(com < 0);
		
	}
	
	@Test
	public void test06_ProfCom2() { // TreeSet, use compareTo
			
		Prof  p1 = new Prof("John S Lee", "yu213", 5);
		Prof  p2 = new Prof("John L", "yu213", 5);
		Prof  p3 = new Prof("John S Lee", "yu403", 12);  
		Prof  p4 = new Prof("Tracy T", "yu003", 5);  
		Prof  p5 = new Prof("John S Lee", "yu603", 5);  
		 
		Set<Prof> li = new TreeSet<>();
		li.add(p1);   // no exception
		assertTrue(li.contains(p2));  
		assertFalse(li.contains(p3));  
		assertFalse(li.contains(p4));  
		assertFalse(li.contains(p3));  
		
		li.add(p4);   // no exception
		assertSame(2, li.size());  // added
		li.add(p3);
		assertSame(3, li.size());  // added 
		
		assertTrue(li.contains(p3)); // use compareTo
		li.add(p2);
		assertSame(3, li.size());  // not added
		li.add(p5);
		assertSame(4, li.size());  // added
		li.add(p3);
		assertSame(4, li.size());  // not added
	}
	
	@Test
	public void test07_ProfCom3() {  // Arrays, Collections sort, use compareTo
		 
		Prof  p1 = new Prof("John S Lee", "yu213", 5);
		Prof  p2 = new Prof("John L", "yu213", 2);
		Prof  p3 = new Prof("John S Lee", "yu403", 12);  
		Prof  p4 = new Prof("Tracy T", "yu003", 5);  
		Prof  p5 = new Prof("John S Lee", "yu603", 5);  
		
		Prof arr[]  = {p1,p2, p3,p4,p5};
		Arrays.sort(arr);
		Prof arrExp[] = {p2, p4, p1, p5, p3};
		assertArrayEquals(arr, arrExp);
		
		// collection
		List<Prof> li = new ArrayList<>();
		li.add(p5); li.add(p3); li.add(p2); li.add(p1); li.add(p4); // random order
		Collections.sort(li); //or li.sort(null); // use natural ordering
		List<Prof> liExp = new ArrayList<>(Arrays.asList(arrExp));
		assertEquals(li, liExp);
	}
	
	// prof comparators later
	
	/*****about books *****************************************************/
	@Test
	public void test08_BookStringRep() {
		Book b1 = new Book("OOP", 1, 2010, "EECS2030");
		 
		String exp = "(OOP, ed:1, 2010, course:EECS2030)";
		assertEquals(exp, b1.toString());
		
		Book b2 = new Book("K&R", 2, 1996, "EECS2031");
        exp = "(K&R, ed:2, 1996, course:EECS2031)";
		assertEquals(exp, b2.toString());
		
		Book b3 = new Book("Compter Science Illuminated", 6, 2016, "EECS1520"); 
		 
		exp = "(Compter Science Illuminated, ed:6, 2016, course:EECS1520)";
		assertEquals(exp, b3.toString());
	}
	
	@Test
	public void test09_BookEqualHash() {
		Book b1 = new Book("OOP", 1, 2010, "EECS2030");
		assertTrue(b1.equals(b1));
		
		Book b0 = null;  
		assertFalse(b1.equals(b0));  //should no exception
		
		Date d = new Date();
		String s = "Hello";
		assertFalse(b1.equals(d));  // should no exception
		assertFalse(b1.equals(s));  // should no exception
		
		Book b2 = new Book("K&R", 2, 1996, "EECS2031");  
        assertFalse(b1.equals(b2));
         
        
		Book b3 = new Book("OOP", 1, 2010, "EECS2030");   
		assertTrue(b1.equals(b3));
        assertEquals(b1.hashCode(), b3.hashCode());
        assertTrue(b3.equals(b1)); // Reflective
        
        Book b4 = new Book("OOP", 2, 2010, "EECS2030");   
		assertFalse(b1.equals(b4));
         
        
        Book b5 = new Book("OOP", 1, 2010, "EECS1022");   
		assertFalse(b1.equals(b5));
		
		Book b6 = new Book("Java Programming", 1, 2010, "EECS2030");   
		assertFalse(b1.equals(b6));
	}
	
	@Test
	public void test10_BookEqualHash2() {
		Book b1 = new Book("OOP", 1, 2010, "EECS2030");   
		Book b2 = new Book("K&R", 2, 1996, "EECS2031");   
       	Book b3 = new Book("OOP", 1, 2010, "EECS2030");  
		Book b4 = new Book("OOP", 2, 2012, "EECS2030");  
        Book b5 = new Book("OOP", 1, 2010, "EECS1022");   
        Book b6 = new Book("Java Programming", 1, 2010, "EECS2030");   
        
        
        ArrayList<Book> li = new ArrayList<>();
		li.add(b1); li.add(b2);  
		assertSame(2, li.size());  // added 
		
		assertTrue(li.contains(b3));
		assertFalse(li.contains(b4));
		assertFalse(li.contains(b5));
		assertFalse(li.contains(b6));
	}
	
	@Test
	public void test11_BookEqualHash3() {
		Book b1 = new Book("OOP", 1, 2010, "EECS2030");
		Book b2 = new Book("K&R", 2, 1996, "EECS2031");
       	Book b3 = new Book("OOP", 1, 2010, "EECS2030");
		Book b4 = new Book("OOP", 2, 2012, "EECS2030");
        Book b5 = new Book("OOP", 1, 2010, "EECS1022");   
        Book b6 = new Book("Java Programming", 1, 2010, "EECS2030");   
        
        HashSet<Book> li = new HashSet<>();
		li.add(b1); li.add(b2); 
		assertSame(2, li.size());   
		
		assertTrue(li.contains(b3));
		assertFalse(li.contains(b4));
		assertFalse(li.contains(b5));
		assertFalse(li.contains(b6));
		
		li.add(b4);
		assertSame(3, li.size());  
		li.add(b3);
		assertSame(3, li.size());   
		li.add(b5);
		assertSame(4, li.size());   
		li.add(b6);
		assertSame(5, li.size());   
	}
	
	@Test
	public void test12_BookComp() {
		Book b1 = new Book("OOP", 1, 2010, "EECS2030");
		Book b2 = new Book("K&R", 2, 1996, "EECS2031");    
       	Book b3 = new Book("OO Programming", 1, 2010, "EECS2030");    
		Book b4 = new Book("OOP", 2, 2010, "EECS2032");   
		Book b5 = new Book("OOP", 1, 2010, "EECS1022");   
		
        int com = b1.compareTo(b2);
        assertTrue(com > 0);
        
        
        com  = b1.compareTo(b3);
        assertTrue(com == 0);
        
        com  = b1.compareTo(b4);
        assertTrue(com < 0);
        
        com  = b1.compareTo(b5);
        assertTrue(com > 0);
        
        com  = b2.compareTo(b1);
        assertTrue(com < 0);
        
        com  = b4.compareTo(b1);
        assertTrue(com > 0);
        
        com  = b4.compareTo(b5);
        assertTrue(com > 0);
        
	}
	
	@Test
	public void test13_BookComp2() { // use tree set
		Book b1 = new Book("OOP", 1, 2010, "EECS2030");
		Book b2 = new Book("K&R", 2, 1996, "EECS2031");  
       	Book b3 = new Book("OO Prgramming", 1, 2010, "EECS2030");  
		Book b4 = new Book("OOP", 2, 2010, "EECS3101");  
		Book b5 = new Book("OOP", 1, 2010, "EECS1022");   
		
		TreeSet<Book> li = new TreeSet<>();
		li.add(b1); li.add(b2);     
		assertSame(2, li.size());   
		
		assertTrue(li.contains(b3));
		assertFalse(li.contains(b4));
		assertFalse(li.contains(b5));
		
		li.add(b4);
		assertSame(3, li.size());  
		li.add(b3);
		assertSame(3, li.size());   
		li.add(b5);
		assertSame(4, li.size());   
	}
	
	@Test
	public void test14_BookComp3() { // use Sort
		Book b1 = new Book("OOP", 1, 2010, "EECS2030");
		Book b2 = new Book("K&R", 2, 1996, "EECS2031");
       	Book b3 = new Book("OOP", 1, 2020, "EECS2030");
		Book b4 = new Book("OOP", 2, 2010, "EECS3101");
		Book b5 = new Book("OOP", 1, 2010, "EECS1022");   
		
		Book arr[]  = {b1,b2, b3,b4, b5};
		Arrays.sort(arr);
		Book arrExp[] = {b2, b5, b1,b4, b3};
		assertArrayEquals(arr, arrExp); 
		
		// collection
		List<Book> li = new ArrayList<>();
		li.add(b5); li.add(b3); li.add(b2); li.add(b4); li.add(b1); // random order
		Collections.sort(li); //or li.sort(null); // use natural ordering
		List<Book> liExp = new ArrayList<>(Arrays.asList(arrExp));
		assertEquals(li, liExp);
		
	}
	
		
	/*****about courses *****************************************************/
	@Test
	public void test15_CoursetoString() {
		
		Course c1 = new Course("EECS2031", "C programming", new Prof("Hui","yu0123", 6), 2020, 'F');
		 
		String exp = "<EECS2031, C programming, 2020F, taught by: Hui>";
		System.out.println(c1.toString());
		assertEquals(exp, c1.toString());
		
		Prof  p2 = new Prof("Tracy T", "yu053", 2);
		Prof  p3 = new Prof("Son-hyang Pak", "yu8021", 6);
		
		Course c2 = new Course("EECS1021", "Java programming", p2, 2019, 'F');
		 
		exp = "<EECS1021, Java programming, 2019F, taught by: Tracy T>";
		assertEquals(exp, c2.toString());
		
		Course c3 = new Course("EECS1520", "Intro to CS", p3, 2020,'S');
		 
		exp = "<EECS1520, Intro to CS, 2020S, taught by: Son-hyang Pak>";
		assertEquals(exp, c3.toString());
		
	}
	
	@Test
	public void test16_CourseEqualHash() {
		Course c1 = new Course("EECS2031", "C programming", new Prof("Hui","yu0123", 6), 2020, 'F');
		assertTrue(c1.equals(c1));
		
		Course c0 = null; // null
		assertFalse(c1.equals(c0));  //should no exception
		
		Date d = new Date();
		String s = "Hello";
		assertFalse(c1.equals(d));  // should no exception
		assertFalse(c1.equals(s));  // should no exception
		
		Course c2 = new Course("EECS2031", "C programming", new Prof("H Wang","yu0123", 6), 2020, 'F');
		assertTrue(c1.equals(c2));
		assertEquals(c1.hashCode(), c2.hashCode());
		assertTrue(c2.equals(c1));
		
		Course c3 = new Course("EECS2031", "ANSI C programming language", new Prof("Wang","yu0123", 6), 2020, 'F');
		assertTrue(c1.equals(c3));
		assertEquals(c1.hashCode(), c3.hashCode());
		assertTrue(c3.equals(c1));
		
		Course c4 = new Course("EECS2031", "C programming", new Prof("Hui","yu0123", 6), 2020, 'W'); 
		assertFalse(c1.equals(c4));
		
		Course c5 = new Course("EECS2031", "C programming", new Prof("Hui","yu0123", 6), 2019, 'F'); 
		assertFalse(c1.equals(c5));
		
		Course c6 = new Course("EECS2030", "OOP", new Prof("Hui","yu0123", 6), 2020, 'F');  
		assertFalse(c1.equals(c6));
	}
	
	@Test
	public void test17_CourseEqualHash2() {
			
		Course c1 = new Course("EECS2031", "C programming", new Prof("Hui","yu0123", 6), 2020, 'F');
		Course c2 = new Course("EECS2031", "C programming", new Prof("Wang","yu0123", 6), 2020, 'F');
		Course c3 = new Course("EECS2031", "ANSI C programming language", new Prof("Wang","yu0123", 6), 2020, 'F');
		Course c4 = new Course("EECS2031", "C programming", new Prof("Hui","yu0123", 6), 2020, 'W'); 
		Course c5 = new Course("EECS2031", "C programming", new Prof("Hui","yu0123", 6), 2019, 'F');  
		Course c6 = new Course("EECS2030", "OOP", new Prof("Hui","yu0123", 6), 2020, 'F');  
	
		List<Course> li = new ArrayList<>();
		li.add(c1); 
		assertSame(1, li.size());  // added 
		assertTrue(li.contains(c2));
		assertTrue(li.contains(c3));
		assertFalse(li.contains(c4));
		assertFalse(li.contains(c5));
		
		li.add(c5); li.add(c4);  
		assertSame(3, li.size());  // added 
		assertTrue(li.contains(c2));
		assertTrue(li.contains(c3));
		assertTrue(li.contains(c4));
		
	}
	
	@Test
	public void test18_CourseEqualHash3() {
	  
		Course c1 = new Course("EECS2031", "C programming", new Prof("Hui","yu0123", 6), 2020, 'F');
		Course c2 = new Course("EECS2031", "C programming", new Prof("Wang","yu0123", 6), 2020, 'F');
		Course c3 = new Course("EECS2031", "ANSI C programming language", new Prof("Wang","yu0123", 6), 2020, 'F');
		Course c4 = new Course("EECS2031", "C programming", new Prof("Hui","yu0123", 6), 2020, 'W'); 
		Course c5 = new Course("EECS2031", "C programming", new Prof("Hui","yu0123", 6), 2019, 'F'); 
		Course c6 = new Course("EECS2030", "OOP", new Prof("Hui","yu0123", 6), 2020, 'F');  
		
		HashSet<Course> li = new HashSet<>();
		li.add(c1); li.add(c5); 
		assertSame(2, li.size());  // added 
		
		assertTrue(li.contains(c2));
		assertTrue(li.contains(c3));
		assertFalse(li.contains(c4));
		assertFalse(li.contains(c6));
		li.add(c4);
		assertSame(3, li.size());   
		li.add(c2);
		assertSame(3, li.size());   
		li.add(c3);
		assertSame(3, li.size());   
		li.add(c6);
		assertSame(4, li.size());   
	}
		 
	
	
	@Test
	public void test19_CourseCom() {
		 		
		Course c1 = new Course("EECS2031", "C programming", new Prof("Hui","yu0123", 6), 2020, 'F');
		Course c2 = new Course("EECS2031", "C programming", new Prof("Wang","yu0123", 6), 2020, 'F');
		Course c3 = new Course("EECS2031", "ANSI C programming language", new Prof("Wang","yu0123", 6), 2020, 'F');
		Course c4 = new Course("EECS2031", "C programming", new Prof("Hui","yu0123", 6), 2020, 'W'); 
		Course c5 = new Course("EECS2031", "C programming", new Prof("Hui","yu0123", 6), 2019, 'F'); 
		Course c6 = new Course("EECS2030", "OOP", new Prof("Hui","yu0123", 6), 2020, 'F');  
		
		int com = c1.compareTo(c2);
        assertTrue(com == 0);
		
		com = c1.compareTo(c3);
        assertSame(com, 0);  // deliberately different ways writing
         
        com  = c1.compareTo(c4);
        assertTrue(com > 0);
        
        com  = c1.compareTo(c5);
        assertTrue(com > 0);
        
        com  = c1.compareTo(c6);
        assertTrue(com > 0);
        
        com  = c4.compareTo(c5);
        assertTrue(com > 0);
        
        com  = c4.compareTo(c6);
        assertTrue(com < 0);
        
        com  = c5.compareTo(c6);
        assertTrue(com < 0);
         
	}
	
	@Test
	public void test20_CourseCom2() {  // TreeSet
 
		Course c1 = new Course("EECS2031", "C programming", new Prof("Hui","yu0123", 6), 2020, 'F');
		Course c2 = new Course("EECS2031", "C programming", new Prof("Wang","yu0123", 6), 2020, 'F');
		Course c3 = new Course("EECS2031", "ANSI C programming language", new Prof("Wang","yu0123", 6), 2020, 'F');
		Course c4 = new Course("EECS2031", "C programming", new Prof("Hui","yu0123", 6), 2020, 'W'); 
		Course c5 = new Course("EECS2031", "C programming", new Prof("Hui","yu0123", 6), 2019, 'F'); 
		Course c6 = new Course("EECS2030", "OOP", new Prof("Hui","yu0123", 6), 2020, 'F');  
		
		TreeSet<Course> li = new TreeSet<>();
		li.add(c1); // no exception
		assertSame(1, li.size());   
		
		assertTrue(li.contains(c2));
		assertTrue(li.contains(c3));
		assertFalse(li.contains(c4));
		assertFalse(li.contains(c5));
		assertFalse(li.contains(c6));
		li.add(c6);
		assertSame(2, li.size());   
		li.add(c2);
		assertSame(2, li.size());   
		li.add(c3);  
		assertSame(2, li.size());   
		li.add(c4);
		assertSame(3, li.size());   
		li.add(c5);
		assertSame(4, li.size());  
        
		li.add(c6);
		assertSame(4, li.size());   
         
	}
	
	@Test
	public void test21_CourseCom3() { // Arrays, Collections sort
		 		
		Course c1 = new Course("EECS2031", "C programming", new Prof("Hui","yu0123", 6), 2020, 'F');
		Course c2 = new Course("EECS1520", "CS Intro", new Prof("Wang","yu0123", 6), 2020, 'F');
		Course c3 = new Course("EECS2031", "ANSI C programming language", new Prof("Wang","yu0123", 6), 2020, 'S');
		Course c4 = new Course("EECS2031", "C programming", new Prof("Hui","yu0123", 6), 2020, 'W'); 
		Course c5 = new Course("EECS2031", "C programming", new Prof("Hui","yu0123", 6), 2019, 'F'); 
		Course c6 = new Course("EECS2030", "OOP", new Prof("Hui","yu0123", 6), 2020, 'F');  
		
		Course arr[]  = {c1,c2, c3,c4, c5,c6};
		Arrays.sort(arr);
		Course arrExp[] = {c5, c4, c3, c2, c6,c1};
		assertArrayEquals(arr, arrExp);
		
		// collection
		List<Course> li = new ArrayList<>(Arrays.asList(c5, c2,c3, c1, c4, c6)); // show another way
		Collections.sort(li); //or li.sort(null); // use natural ordering
		List<Course> liExp = new ArrayList<>(Arrays.asList(arrExp));
		assertEquals(li, liExp);
      
	}
	
	/*****************************************************
	 * 
	 */
	// finally do the comparator of Prof
	@Test
	public void test22_ProfByNameComparator() {  // Naive test of comparators, usually don't use this way
		Prof  p1 = new Prof("John S Lee", "yu213", 5);
		Prof  p2 = new Prof("Andy Kim", "yu7013", 5);
		Prof  p3 = new Prof("John S Lee", "yu403", 1);  
		Prof  p4 = new Prof("Tracy T", "yu003", 5);  
		 
		
		ProfByNameComparator byNameCtr = new ProfByNameComparator();
		
		int com = byNameCtr.compare(p1, p2);
		assertTrue (com > 0);
		
		com = byNameCtr.compare(p1, p4);
		assertTrue (com < 0);
		
		com = byNameCtr.compare(p1, p3);
		assertTrue (com == 0);
		
		com = byNameCtr.compare(p2, p3);
		assertTrue (com < 0);
		
		com = byNameCtr.compare(p3, p4);
		assertTrue (com < 0);
	}
		
	
	// finally do the comparator of Prof
	@Test
	public void test23_ProfByNameComparator2() {  // Arrays, Collections sort, use compareTo
		Prof  p1 = new Prof("John S Lee", "yu213", 5);
		Prof  p2 = new Prof("Andy Kim", "yu7013", 5);
		Prof  p3 = new Prof("DuYee Lee", "yu403", 1);  
		Prof  p4 = new Prof("Tracy Tong", "yu003", 5); 
		Prof  p5 = new Prof("Tracy T", "yu003", 5); 
		
		Prof arr[]  = {p1,p2, p3,p4, p5};
		ProfByNameComparator byNameComptr = new ProfByNameComparator();
		Arrays.sort(arr, byNameComptr); // sort by name
		Prof arrExp[] = {p2, p3, p1, p5, p4};
		assertArrayEquals(arr, arrExp);
		
		// collection
		List<Prof> li = new ArrayList<>(Arrays.asList(p5, p3,p2, p4, p1)); // show another way
		Collections.sort(li, new ProfByNameComparator());  
		List<Prof> liExp = new ArrayList<>(Arrays.asList(arrExp));
		assertEquals(li, liExp);
		
		
	}
	
	@Test
	public void test24_ProfByYearComparator() {  // Naive test of comparators, usually don't use this way
		Prof  p1 = new Prof("John S Lee", "yu213", 5);
		Prof  p2 = new Prof("Andy Kim", "yu7013", 5);
		Prof  p3 = new Prof("John S Lee", "yu403", 1);  
		Prof  p4 = new Prof("Tracy T", "yu003", 8);  
		
		ProfByYearComparator byYearCtr = new ProfByYearComparator();
		
		int com = byYearCtr.compare(p1, p2);
		assertTrue (com == 0);
		
		com = byYearCtr.compare(p1, p3);
		assertTrue (com > 0);
		
		com = byYearCtr.compare(p1, p4);
		assertTrue (com < 0);
		
		com = byYearCtr.compare(p3, p1);
		assertTrue (com < 0);
		
		com = byYearCtr.compare(p3, p4);
		assertTrue (com < 0);
	}
		
	@Test
	public void test25_ProfByYearComparator() {  // Arrays, Collections sort, use compareTo
		Prof  p1 = new Prof("John S Lee", "yu213", 5);
		Prof  p2 = new Prof("Andy Kim", "yu7013", 3);
		Prof  p3 = new Prof("DuYee Lee", "yu403", 1);  
		Prof  p4 = new Prof("Tracy T", "yu003", 8);  
		Prof  p5 = new Prof("Tracy Tong", "yu003", 15); 
				
		Prof arr[]  = {p1,p2, p3,p4, p5};
		Arrays.sort(arr, new ProfByYearComparator()); // sort by year
		Prof arrExp[] = {p3, p2, p1, p4, p5};
		assertArrayEquals(arr, arrExp);
		
		// collection
		List<Prof> li = new ArrayList<>(Arrays.asList(p5, p3,p1, p4, p2 )); // show another way
		Collections.sort(li, new ProfByYearComparator());  
		List<Prof> liExp = new ArrayList<>(Arrays.asList(arrExp));
		assertEquals(li, liExp);
		
	}
		
	
	
	
	
	
	
}
