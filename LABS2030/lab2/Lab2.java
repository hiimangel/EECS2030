package eecs2030.lab2;


/**
 * A utility class containing several recursive methods (Lab 2, F2020)
 * 
 * <pre>
 * 
 * For all methods in this API, you are forbidden to use any loops, nor 
 * String or List based methods such as "contains", or methods that use regular expressions
 * </pre>
 * 
 * @author EECS2030 Fall 2020
 *
 */
public final class Lab2 {

	// ============================================================================================
	// IMPORTANT: 	the tester file passes some tests out-of-the-box ONLY because arbitrary 
	// ==========	return values have been added to the methods to ensure Lab2.java compiles
	//
	//  				- this does not mean the method has passed a test adequately 
	//					- you will need to complete the methods properly to satisfy the tests fully
	//					- if for some reason you cannot complete a method (to satisfy all of its tests), 
	//					  please make sure each method AT LEAST returns an appropriate value 
	//					  (i.e. of the correct return type) so that your program can still compile
	//
	// ============================================================================================
	
	
	/**
	 * This is empty by design, Lab2 cannot be instantiated 
	 */
	private Lab2() {
		// empty by design
	}

			  
	

	/**
	 * Return the sum of the integers 1 through n where n is a strictly positive integer.
	 * Note that the sum might overflow if n is too large; this method does not
	 * check if the sum overflows (i.e., it's the client's problem)
	 * 
	 * 
	 * @param n a strictly positive number
	 * @return the sum 1 + 2 + ... + n
	 */
	public static int sum(int n) {
		if (n == 1) 
            return n; 
        return n + sum(n - 1); 
		
	}


	/**
	 * Determines if an integer is prime.
	 * 
	 * @param x an integer value greater than 2
	 * @return true if n is prime, false otherwise
	 */
	public static boolean isPrime(int x) {
		
		/* DO NOT ALTER THIS METHOD 
		 * 
		 * instead, complete the private helper method isPrime(int, int)
		 * that is invoked from this method (see lab2 definition for more info)
		 * 
		 */
		
		return isPrime(x, 2);
		
	}

	/**
	 * A privately defined helper method for isPrime(int), to recursively determine if an integer is prime.
	 * 
	 * @param x an integer value greater than 2
	 * @param divisor an integer to consider as a divisor for x
	 * @return true if x is prime, false otherwise
	 */
	private static boolean isPrime(int x, int divisor) {


	       if(x == divisor){
	           return true;
	       }
	       if(x % divisor == 0)
	           return false;
	       
	       return isPrime(x, divisor+1);

	}


	/**
	 * Returns a string of length n formed by alternating the
	 * characters first and second. E.g.
	 * 
	 * <pre>
	 * <code>alternate('*', '-', 0)</code> returns the string ""
	 * <code>alternate('*', '-', 1)</code> returns the string "*"
	 * <code>alternate('*', '-', 2)</code> returns the string "*-"
	 * <code>alternate('*', '-', 3)</code> returns the string "*-*"
	 * <code>alternate('*', '-', 4)</code> returns the string "*-*-"
	 * </pre>
	 * 
	 * @pre. n is strictly greater than or equal to zero
	 * @param first the first character of the repeating pattern
	 * @param second the second character of the repeating pattern
	 * @param n the length of the desired string
	 * @return the string of alternating first and second characters
	 */
	public static String alternate(char first, char second, int n) {
		if (n == 0)
			return "";
		else if (n == 1)
			return Character.toString(first);
		else 
			return Character.toString(first) + Character.toString(second) + alternate(first, second, n-2);
	}
	

	/**
	 * getParenthesis returns the component within a given string that is
	 * enclosed in parenthesis
	 * 
	 * <pre>
	 * The method assumes there is only a single pair of parenthesis in the
	 * string, and computes the new string recursively, such that the new string
	 * is only made of the parenthesis and their contents. E.g. 
	 *
	 * <code>getParenthesis("abcd(xyz)qrst")</code> will return "(xyz)"
	 * <code>getParenthesis("xyz(abc)")</code> will return "(abc)"
	 * <code>getParenthesis("(a)xy")</code> will return "(a)"
	 * <code>getParenthesis("xy()z")</code> will return "()"
	 * <code>getParenthesis("(.)1")</code> will return "(.)"
	 * </pre>
	 * 
	 * @param str the input string (with one pair of parenthesis embedded within)
	 * @return a string made only of the parenthesis and their contents (from str)
	 */
	public static String getParenthesis(String str) {

		  if(str.charAt(0) != '(' || str.charAt(str.length()-1)!=')'){
	           if(str.charAt(0) == '('){
	               str = str.substring(0, str.length()-1);
	           }
	           else if(str.charAt(str.length()-1) == ')'){
	               str = str.substring(1, str.length());
	           }   else{
	               str = str.substring(1, str.length()-1);
	           }
	           return getParenthesis(str); 
	       } else{
	           return "(" + str.substring(1, str.length()-1) + ")";

	}
}



	/**
	 * Write out the hexadecimal (base 16) representation of a given decimal number 
	 * (as a string). In a hexadecimal string, there are 16 digits: 0-9, then A,B,C,D,E,F
	 * representing 10-15 respectively.
	 * 
	 * <pre>
	 * E.g. The hexadecimal code: <br>
	 * 
	 * <code>"A32E" = A*16^3 + 3*16^2 + 2*16^1 + E*16^0 </code><br>
	 * <code>	    = 10*16^3 + 3*16^2 + 2*16^1 + 14*16^0 </code><br>
	 * <code>	    = 40960 +  768 + 32 + 14 </code><br>
	 * <code>	    = 41774 </code><br>
	 * </pre>
	 * 
	 * This method should decompose the decimal (integer) value recursively (no loops 
	 * allowed), by dividing the decimal value 16, and checking the remainder and the 
	 * residual (modulus).
	 * 
	 * In this method, the output hexadecimal (string) should not have leading zeros 
	 * (e.g. 255 = "FF").
	 * 
	 * 
	 *  <pre>
	 * decimalToHex(4) returns "4"
	 * decimalToHex(10) returns "A"
	 * decimalToHex(12) returns "C"
	 * decimalToHex(15) returns "F"
	 * decimalToHex(16) returns "10"
	 * decimalToHex(116) returns "74"
	 * decimalToHex(142) returns "8E"
	 * decimalToHex(5879) returns "16F7" 
	 * </pre>
	 * 
	 * @param value a decimal value to convert to a hexadecimal string
	 * @return a hexidecimal string representing the value (with no leading zeros)
	 */
	public static String decimalToHex(int value) {

		// you may consider using this string array as a lookup for hex digits >9
		String [] hexCodes = {"A","B","C","D","E","F"};


        if(value < 10) 
            return String.valueOf(value);
    
        else if(value < 16){
        	
        if (value == 10)
        	return "A";
        else if(value == 11)
        	return "B";
        else if(value == 12)
        	return "C";
        else if(value == 13)
        	return "D";
        else if(value == 14)
        	return "E";
        else if(value == 15)
        	return "F";
        else
        	return "";     
        }

        else 
            return decimalToHex(value / 16)+""+decimalToHex(value % 16);



	}









}
