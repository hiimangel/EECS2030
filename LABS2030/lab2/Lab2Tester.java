package eecs2030.lab2;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Lab2Tester {

	
	// sum
	
	@Test
	public void test1_sum_01() {
		
		int e = 0;
		for (int n=1; n<1000; n++) {
			e += n;
			assertEquals("Failed: sum(" + n + ")", e, Lab2.sum(n) );
		}
			
	}
	
	
	// isPrime
	
	@Test
	public void test2_isPrime() {
		int[] primes = { 2, 3, 5, 7, 11, 59, 113, 449, 991 };
		for (int i = 0; i < primes.length; i++) {
			int x = primes[i];
			assertTrue("" + x + " is a prime number but isPrime returned false", Lab2.isPrime(x));
		}
	}

	@Test
	public void test2_isNotPrime() {
		int[] unprimes = { 2 * 2, 5 * 7, 8 * 9, 10 * 15, 59 * 113, 991 * 997 };
		for (int i = 0; i < unprimes.length; i++) {
			int x = unprimes[i];
			assertFalse("" + x + " is not a prime number but isPrime returned true", Lab2.isPrime(x));
		}
	}

	// alternate
	@Test
	public void test3_alternate_01() {

		String e = "";
		String a = Lab2.alternate('*','-',0);

		assertEquals("Failed: alternate('*','-',0)", e, a);
	}

	
	@Test
	public void test3_alternate_02() {

		String e = "*";
		String a = Lab2.alternate('*','-',1);

		assertEquals("Failed: alternate('*','-',1)", e, a);
	}

	@Test
	public void test3_alternate_03() {

		String e = "*-";
		String a = Lab2.alternate('*','-',2);

		assertEquals("Failed: alternate('*','-',2)", e, a);
	}
	
	@Test
	public void test3_alternate_04() {

		String e = "*-*";
		String a = Lab2.alternate('*','-',3);
		System.out.println(a);

		assertEquals("Failed: alternate('*','-',3)", e, a);
	}
	
	@Test
	public void test3_alternate_05() {

		String e = "*-*-";
		String a = Lab2.alternate('*','-',4);
		System.out.println(a);
		assertEquals("Failed: alternate('*','-',4)", e, a);
	}
	
	// getParenthesis
	
	@Test
	public void test4_getParenthesis_01() {

		String e = "(xyz)";
		String a = Lab2.getParenthesis("abcd(xyz)grst");

		assertEquals("Failed: getParenthesis(\"abcd(xyz)grst\")", e, a);
	}
	
	@Test
	public void test4_getParenthesis_02() {

		String e = "(abc)";
		String a = Lab2.getParenthesis("xyz(abc)");

		assertEquals("Failed: getParenthesis(\"xyz(abc)\")", e, a);
	}
	
	@Test
	public void test4_getParenthesis_03() {

		String e = "(a)";
		String a = Lab2.getParenthesis("(a)xy");

		assertEquals("Failed: getParenthesis(\"(a)xy\")", e, a);
	}
	@Test
	public void test4_getParenthesis_04() {

		String e = "()";
		String a = Lab2.getParenthesis("xy()z");

		assertEquals("Failed: getParenthesis(\"xy()z\")", e, a);
	}
	@Test
	public void test4_getParenthesis_05() {

		String e = "(.)";
		String a = Lab2.getParenthesis("(.)1");

		assertEquals("Failed: getParenthesis(\"(.)1\")", e, a);
	}
	
	

	@Test
	public void test4_getParenthesis_06() {

		String e = "()";
		String a = Lab2.getParenthesis("()");

		assertEquals("Failed: getParenthesis(\"()\")", e, a);
	}


	@Test
	public void test4_getParenthesis_07() {

		String e = "(possible)";
		String a = Lab2.getParenthesis("not really (possible)");

		assertEquals("Failed: getParenthesis(\"not really (possible)\")", e, a);
	}
	
	
	/**
	 * decimalToHex tester
	 */
	
	@Test
	public void test5_decimalToHex_01() {

		String exp = "4";
		String act = Lab2.decimalToHex(4);

		assertEquals("Failed: decimalToHex(4)", exp, act);
	}
	
	@Test
	public void test5_decimalToHex_02() {

		String exp = "A";
		String act = Lab2.decimalToHex(10);

		assertEquals("Failed: decimalToHex(10)", exp, act);
	}
	
	@Test
	public void test5_decimalToHex_03() {

		String exp = "C";
		String act = Lab2.decimalToHex(12);

		assertEquals("Failed: decimalToHex(12)", exp, act);
	}
	
	@Test
	public void test5_decimalToHex_04() {

		String exp = "F";
		String act = Lab2.decimalToHex(15);

		assertEquals("Failed: decimalToHex(15)", exp, act);
	}

	@Test
	public void test5_decimalToHex_05() {

		String exp = "10";
		String act = Lab2.decimalToHex(16);

		assertEquals("Failed: decimalToHex(16)", exp, act);
	}

	@Test
	public void test5_decimalToHex_06() {

		String exp = "74";
		String act = Lab2.decimalToHex(116);

		assertEquals("Failed: decimalToHex(116)", exp, act);
	}
	

	@Test
	public void test5_decimalToHex_07() {

		String exp = "8E";
		String act = Lab2.decimalToHex(142);

		assertEquals("Failed: decimalToHex(142)", exp, act);
	}
	

	@Test
	public void test5_decimalToHex_08() {

		String exp = "16F7";
		String act = Lab2.decimalToHex(5879);

		assertEquals("Failed: decimalToHex(5879)", exp, act);
	}
	

}
