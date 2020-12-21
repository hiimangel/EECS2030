package myUtil_List;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestMyArrayList {

	@Test
	public void test01_initialAndGeneric() {
		
		// String type
		MyArrayList<String> arrL = new MyArrayList<>();
		int leng = ((Object[])arrL.elementData).length;// have to do this, array is different in generic
		assertEquals(10, leng);
		String exp [] = {null, null, null, null, null, null, null,null, null, null};
		assertArrayEquals(exp, arrL.elementData);
		assertEquals(0, arrL.size());
		assertTrue(arrL.isEmpty());
		
		// Integer type
		MyArrayList<Integer> arrL2 = new MyArrayList<>();
		assertArrayEquals(exp, arrL2.elementData);
		assertEquals(10, ((Object[])arrL2.elementData).length);
		assertEquals(0, arrL2.size());
		assertTrue(arrL2.isEmpty());
		
		// Double type
		MyArrayList<Double> arrL3 = new MyArrayList<>(3);
		assertSame(3, ((Object[])arrL3.elementData).length); //write this way deliberately
		String exp2 [] = {null, null, null};
		assertArrayEquals(exp2, arrL3.elementData);
		assertSame(0, arrL3.size());
		assertTrue(arrL3.isEmpty());
		
	}
	
	@Test
	public void test02_getContainIndexSet() {
		MyArrayList<String> arrL = new MyArrayList<>();
		String tmp [] = {"EECS", "2030", "Class","Example about", "Array", "List", "Array", null,null, null};
		arrL.size=7;
		arrL.elementData = tmp;
		
		assertEquals("EECS", arrL.get(0));
		assertEquals("2030", arrL.get(1));
		assertEquals("Example about", arrL.get(3)); 
		assertEquals("Class", arrL.get(2));
		assertEquals("List", arrL.get(5)); 
		
		assertTrue(arrL.contains("Array"));
		assertFalse(arrL.contains("Arrays"));
		assertTrue(arrL.contains("Class"));
		
		assertSame(0, arrL.indexOf("EECS"));
		assertSame(4, arrL.indexOf("Array"));
		assertSame(-1, arrL.indexOf("Arrays"));
		assertSame(-1, arrL.indexOf("array"));
		assertSame(6, arrL.lastIndexOf("Array"));
		
		
		String resu = arrL.set(1, "2030ABCE");
		String[] exp3 = {"EECS", "2030ABCE", "Class","Example about", "Array", "List", "Array", null, null,null};
		assertArrayEquals(exp3, arrL.elementData);
		assertSame(7, arrL.size());
		assertEquals("2030", resu);
		
		String resu2 = arrL.set(3, "XXX YYY"); //return old value
		String[] exp4 = {"EECS", "2030ABCE", "Class","XXX YYY", "Array", "List", "Array", null, null,null};
		assertArrayEquals(exp4, arrL.elementData);
		assertEquals(7, arrL.size());
		assertEquals("Example about", resu2);
	}
	
		@Test
		public void test03_getContainIndexSet_Int() {
			MyArrayList<Integer> arrL = new MyArrayList<>();
			Integer tmp [] = {7, 3, 5, 6, new Integer(4), new Integer(0), 3, null,null, null};
			arrL.size=7;
			arrL.elementData = tmp;
			
			assertEquals(new Integer(7), arrL.get(0));
			assertEquals(new Integer(3), arrL.get(1));
			assertEquals(new Integer(6), arrL.get(3)); 
			assertEquals(new Integer(5), arrL.get(2));
			assertEquals(new Integer(0), arrL.get(5)); 
			
			assertTrue(arrL.contains(5));
			assertFalse(arrL.contains(15));
			assertTrue(arrL.contains(new Integer(4)));
			
			assertSame(0, arrL.indexOf(new Integer(7)));
			assertSame(4, arrL.indexOf(4));
			assertSame(-1, arrL.indexOf(new Integer(20)));
			assertSame(-1, arrL.indexOf(new Integer(200)));
			assertSame(6, arrL.lastIndexOf(new Integer(3)));
			
			
			Integer resu = arrL.set(1, 20);  
			Integer[] exp3 = {7, 20, 5, 6, 4, 0, 3, null, null,null};
			assertArrayEquals(exp3, arrL.elementData);
			assertEquals(7, arrL.size());
			assertEquals(new Integer(3), resu);
			
			Integer resu2 = arrL.set(3, 100); 
			Integer[] exp4 = {7, 20, 5, 100, 4, 0, 3, null, null,null};
			assertArrayEquals(exp4, arrL.elementData);
			assertEquals(7, arrL.size());
			assertEquals(new Integer(6), resu2);
		}
	
		@Test
		public void test04_add_Int() {
			MyArrayList<Integer> arrL = new MyArrayList<>();
			 
			arrL.add(new Integer(7));   assertSame(1, arrL.size());
			arrL.add(3);  assertSame(2, arrL.size());
			arrL.add(new Integer(5));  arrL.add(6);
			Integer[] exp = {new Integer(7), new Integer(3), new Integer(5), new Integer(6), null, null, null, null, null,null};
			assertArrayEquals(exp, arrL.elementData);
			assertSame(4, arrL.size());
			assertFalse(arrL.isEmpty());
			assertEquals(0, arrL.indexOf(new Integer(7)));
			assertEquals(2, arrL.indexOf(5));
			assertEquals(3, arrL.indexOf(6));
			assertEquals(1, arrL.indexOf(new Integer(3)));
			
			arrL.add(2);  arrL.add(new Integer(0));
			Integer[] exp2 = {new Integer(7), new Integer(3), new Integer(5), new Integer(6), new Integer(2), new Integer(0),null, null, null,null};
			assertArrayEquals(exp2, arrL.elementData);
			assertEquals(6, arrL.size());
			assertFalse(arrL.isEmpty());
			assertEquals(2, arrL.indexOf(new Integer(5))); 
			assertEquals(5, arrL.indexOf(new Integer(0)));  
		}	
		
	@Test
	public void test05_add() {
		MyArrayList<String> arrL = new MyArrayList<>();
		 
		arrL.add("EECS");   assertSame(1, arrL.size());
		arrL.add("2030");   assertSame(2, arrL.size());
		arrL.add("Class");  arrL.add("Example about");
		String[] exp = {"EECS", "2030", "Class","Example about", null, null, null, null, null,null};
		assertArrayEquals(exp, arrL.elementData);
		assertEquals(4, arrL.size());
		assertFalse(arrL.isEmpty());
		assertEquals(0, arrL.indexOf("EECS"));
		assertEquals(2, arrL.indexOf("Class"));
		assertEquals(3, arrL.indexOf("Example about"));
		assertEquals(1, arrL.indexOf("2030"));
		
		arrL.add("Array");  arrL.add("List");
		String[] exp2 = {"EECS", "2030", "Class","Example about", "Array", "List", null, null, null,null};
		assertArrayEquals(exp2, arrL.elementData);
		assertEquals(6, arrL.size());
		assertFalse(arrL.isEmpty());
		assertEquals(2, arrL.indexOf("Class")); 
		assertEquals(5, arrL.indexOf("List"));  
	}
	
	@Test
	public void test06_addIndex() {
		MyArrayList<String> arrL = new MyArrayList<>();
		arrL.add("EECS");   arrL.add("2030");
		arrL.add("Class");  arrL.add("Example about");
		arrL.add("Array");  arrL.add("List");
				
		arrL.add(1, "XXX");
		String[] exp = {"EECS", "XXX", "2030", "Class","Example about", "Array", "List", null, null, null};
		assertArrayEquals(exp, arrL.elementData);
		assertSame(7, arrL.size());
		assertSame(0, arrL.indexOf("EECS"));
		assertSame(3, arrL.indexOf("Class"));
		assertSame(5, arrL.indexOf("Array"));
		
		arrL.add(3, "YYY");
		String exp2[] = {"EECS", "XXX", "2030", "YYY", "Class","Example about", "Array", "List", null, null};
		assertArrayEquals(exp2, arrL.elementData);
		assertSame(8, arrL.size());
		assertSame(0, arrL.indexOf("EECS"));
		assertSame(4, arrL.indexOf("Class"));
		assertSame(6, arrL.indexOf("Array"));
	}
	
		
	@Test
	public void test07_remove() {
		MyArrayList<String> arrL = new MyArrayList<>();
		
		String tmp [] = {"EECS", "2030", "Class","Example about", "Array", "List", "Array", null,null, null};
		arrL.size=7;
		arrL.elementData = tmp;
		
		assertEquals(1, arrL.indexOf("2030"));
		assertEquals(4, arrL.indexOf("Array"));  
		assertEquals(6, arrL.lastIndexOf("Array"));  
		
		arrL.remove("EECSX"); 
		String exp[] = {"EECS", "2030", "Class","Example about", "Array", "List", "Array", null,null, null};
		assertArrayEquals(exp, arrL.elementData);
		assertEquals(1, arrL.indexOf("2030"));
		assertEquals(4, arrL.indexOf("Array"));  
		assertEquals(6, arrL.lastIndexOf("Array")); 
		
		arrL.remove("EECS");  
		String exp2[] = {"2030", "Class","Example about", "Array", "List", "Array", null,null, null,null};
		assertArrayEquals(exp2, arrL.elementData);
		assertSame(6, arrL.size());
		assertEquals(0, arrL.indexOf("2030"));
		assertEquals(3, arrL.indexOf("Array"));  
		assertEquals(5, arrL.lastIndexOf("Array"));  
		
		arrL.remove("Example about");    
		String exp3[] = {"2030", "Class", "Array", "List", "Array", null, null,null, null,null};
		assertArrayEquals(exp3, arrL.elementData);
		assertSame(5, arrL.size());
		assertEquals(1, arrL.indexOf("Class"));
		assertEquals(0, arrL.indexOf("2030"));
		assertEquals(2, arrL.indexOf("Array"));  
		assertEquals(4, arrL.lastIndexOf("Array"));  
		
		arrL.remove("Array");  
		String exp4[] = {"2030", "Class", "List", "Array", null, null,null,null, null,null};
		assertArrayEquals(exp4, arrL.elementData);
		assertEquals(4, arrL.size());
		assertEquals(0, arrL.indexOf("2030"));
		assertEquals(1, arrL.indexOf("Class"));
		assertEquals(3, arrL.indexOf("Array"));  
		assertEquals(3, arrL.lastIndexOf("Array"));  
	}
	
	@Test
	public void test08_removeIndex() {
		MyArrayList<String> arrL = new MyArrayList<>();
		
		String tmp [] = {"EECS", "2030", "Class","Example about", "Array", "List", "Array", null,null, null};
		arrL.size=7;
		arrL.elementData = tmp;
		
		assertEquals(1, arrL.indexOf("2030"));
		assertEquals(4, arrL.indexOf("Array"));  
		assertEquals(6, arrL.lastIndexOf("Array"));  
		
		arrL.remove(2);  
		String exp[] = {"EECS", "2030", "Example about", "Array", "List", "Array", null,null,null, null};
		assertArrayEquals(exp, arrL.elementData);
		assertSame(6, arrL.size());
		assertEquals(1, arrL.indexOf("2030"));  
		assertEquals(3, arrL.indexOf("Array"));  
		assertEquals(5, arrL.lastIndexOf("Array"));  
		
		String resu = arrL.remove(0);  
		String exp2[] = {"2030", "Example about", "Array", "List", "Array", null,null,null, null,null};
		assertArrayEquals(exp2, arrL.elementData);
		assertSame(5, arrL.size());
		assertEquals("EECS", resu);
		assertEquals(0, arrL.indexOf("2030"));  
		assertEquals(2, arrL.indexOf("Array"));  
		assertEquals(4, arrL.lastIndexOf("Array"));  
		
		
		resu = arrL.remove(2);  
		String exp3[] = {"2030", "Example about",  "List", "Array", null,null,null,null, null,null};
		assertArrayEquals(exp3, arrL.elementData);
		assertEquals("Array", resu);
		assertEquals(4, arrL.size());
		assertEquals(0, arrL.indexOf("2030"));
		assertEquals(3, arrL.indexOf("Array")); 	
		assertEquals(3, arrL.lastIndexOf("Array"));  
	}
	
	// test expansion
	@Test
	public void test09_Expansion() {
		MyArrayList<String> arrL = new MyArrayList<>();
		// manually do this to avoid using add()
		String tmp [] = {"EECS", "2030", "Class","Example about", "Array", "List", "Array", "How", "Are","You"};
		arrL.size=10;
		arrL.elementData = tmp;
		
		arrL.add("XXX");
		String exp [] = {"EECS", "2030", "Class","Example about", "Array", "List", "Array", "How", "Are","You", 
				    "XXX", null, null, null, null, null, null, null, null, null};
		assertArrayEquals(exp, arrL.elementData);
		assertSame(20, ((Object[])arrL.elementData).length);
		assertSame(11, arrL.size());
		assertSame(10, arrL.indexOf("XXX")); 
		assertSame(1, arrL.indexOf("2030"));  
		assertSame(9, arrL.indexOf("You"));   
		assertSame(4, arrL.indexOf("Array"));  
		assertSame(6, arrL.lastIndexOf("Array"));  
		
	}
	
	@Test
	public void test10_Expansion2() {
		MyArrayList<Integer> arrL = new MyArrayList<>();
		// manually do this to avoid using add(). Auto boxing
		Integer tmp [] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
		arrL.size=20;
		arrL.elementData = tmp;
		
		arrL.add(100); // auto-boxing
		Integer exp [] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,100,null, null, null, null, null, null, null, null, null };
		assertArrayEquals(exp, arrL.elementData);
		assertSame(30, ((Object[])arrL.elementData).length);
		assertSame(21, arrL.size());
		assertSame(20, arrL.indexOf(new Integer(100)));  
		assertSame(1, arrL.indexOf(1));  
		assertSame(19, arrL.indexOf(new Integer(19)));   

	}
	
	
	// finally test index out of bound
	@Test (expected = IndexOutOfBoundsException.class)
	public void test11_GetExp0() {
		MyArrayList<String> arrL = new MyArrayList<>();
		arrL.get(0);
    }
	@Test (expected = IndexOutOfBoundsException.class)
	public void test12_GetExp() {
		MyArrayList<String> arrL = new MyArrayList<>();
		
		// manually do this to avoid using get()
		String tmp [] = {"EECS", "2030", "Class","Example about", "Array", "List", "Array", null,null, null};
		arrL.elementData = tmp;
		arrL.size=7;
		arrL.get(7);
    }
	@Test (expected = IndexOutOfBoundsException.class)
	public void test13_GetExp2() {
		MyArrayList<String> arrL = new MyArrayList<>();
		
		// manually do this to avoid using get()
		String tmp [] = {"EECS", "2030", "Class","Example about", "Array", "List", "Array", null,null, null};
		arrL.elementData = tmp;
		arrL.size=7;
		arrL.get(8);
    }
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void test14_SetExp0() {
		MyArrayList<String> arrL = new MyArrayList<>();
		arrL.set(0, "xx");
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void test15_SetExp() {
		MyArrayList<String> arrL = new MyArrayList<>();
		
		// manually do this to avoid using get()
		String tmp [] = {"EECS", "2030", "Class","Example about", "Array", "List", "Array", null,null, null};
		arrL.elementData = tmp;
		arrL.size=7;
		arrL.set(7, "xx");
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void test16_SetExp2() {
		MyArrayList<String> arrL = new MyArrayList<>();
		
		// manually do this to avoid using get()
		String tmp [] = {"EECS", "2030", "Class","Example about", "Array", "List", "Array", null,null, null};
		arrL.elementData = tmp;
		arrL.size=7;
		arrL.set(9, "xx");
    }
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void test17_AddExp0() {
		MyArrayList<String> arrL = new MyArrayList<>();
		arrL.add(0, "xx");
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void test18_AddExp() {
		MyArrayList<String> arrL = new MyArrayList<>();
		
		// manually do this to avoid using add()
		String tmp [] = {"EECS", "2030", "Class","Example about", "Array", "List", "Array", null,null, null};
		arrL.elementData = tmp;
		arrL.size=7;
		arrL.add(7, "xx");
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void test19_AddExp2() {
		MyArrayList<String> arrL = new MyArrayList<>();
		
		// manually do this to avoid using add()
		String tmp [] = {"EECS", "2030", "Class","Example about", "Array", "List", "Array", null,null, null};
		arrL.elementData = tmp;
		arrL.size=7;
		arrL.add(9, "xx");
    }
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void test20_RemoveExp() {
		MyArrayList<String> arrL = new MyArrayList<>();
		arrL.remove(0);
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void test21_RemoveExp() {
		MyArrayList<String> arrL = new MyArrayList<>();
		
		// manually do this to avoid using add()
		String tmp [] = {"EECS", "2030", "Class","Example about", "Array", "List", "Array", null,null, null};
		arrL.elementData = tmp;
		arrL.size=7;
		arrL.remove(7);
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void test22_RemoveExp2() {
		MyArrayList<String> arrL = new MyArrayList<>();
		
		// manually do this to avoid using add()
		String tmp [] = {"EECS", "2030", "Class","Example about", "Array", "List", "Array", null,null, null};
		arrL.elementData = tmp;
		arrL.size=7;
		arrL.remove(9);
    }
	
	
    
	
	
	
	
	
	
	
	

}
