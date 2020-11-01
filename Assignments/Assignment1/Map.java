
/* PLEASE DO NOT MODIFY A SINGLE STATEMENT IN THE TEXT BELOW.
 READ THE FOLLOWING CAREFULLY AND FILL IN THE GAPS

I hereby declare that all the work that was required to 
solve the following problem including designing the algorithms
and writing the code below, is solely my own and that I received
no help in creating this solution and I have not discussed my solution 
with anybody. I affirm that I have read and understood
 the Senate Policy on Academic honesty at 
https://secretariat-policies.info.yorku.ca/policies/academic-honesty-senate-policy-on/
and I am well aware of the seriousness of the matter and the penalties that I will face as a 
result of committing plagiarism in this assignment.

BY FILLING THE GAPS,YOU ARE SIGNING THE ABOVE STATEMENTS.

Full Name: Alp Baran Sirek
Student Number: 217329251
Course Section: E
*/

package Assignment1;
import java.util.*;

/**
 * 
 * @author EECS2030 Team
 *
 */

public class Map {
	boolean [][] map; 
	private int row;
	private int column;
	String foundPath = "";
	
	/**
	 * This is the constructor that constructs the city map, 
	 * which is a grid of row by column.
	 * @param row is the number of east-west streets of the city
	 * @param column is the number of north-south streets of the city
	 */
	public Map(int row, int column) {
		this.row = row; // row initialization for the grid
		this.column = column; // column initialization for the grid
		this.map = new boolean[row][column]; // create a boolean matrix with using the row and column argument of the constructor
	}
	/**
	 * This method checks the correctness of the input parameters. If the preconditions are not met 
	 * an exception is thrown, otherwise depending to the direction, it calls 
	 * one of the four recursive functions of goSouthWest, goSouthEast, goNorthWest and goNorthEast.
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre the integer parameters should be in the range of the city grid.(i.e. [0, N) if N is the number of east-west streets and [0, M) if 
	 * M is the number of north-south streets.) 
	 * @exception IllegalArgumentException if any of the precondition did not meet.
	 */
	public String getPath (int startRow, int startCol, int destRow, int destCol , String path) {
		// initializations of booleans to check for possible errors
		boolean boolStartRow  = false; // the start Row boolean variable
		boolean boolStartCol = false; // the start Column boolean variable
		boolean boolDestRow = false; // the Destination Row boolean variable
		boolean boolDestCol = false; // the Destination Column boolean variable
		boolean boolOutOfRangeRow = false; // boolean variable to check if Row value is out of maximum range - that was defined in the constructor -
		boolean boolOutOfRangeCol = false; // boolean variable to check if Column value is out of maximum - range that was defined in the constructor -
		
		// if the startRow or destRow argument is larger than max of grid 
		// boolean variable is true so that error can be thrown in the upcoming if statement
		if (startRow > this.row || destRow > this.row) 
			boolOutOfRangeRow = true;
		// if the startCol or destCol argument is larger than max of grid
		// boolean variable is true so that error can be thrown in the upcoming if statement
		if (startCol > this.column || destCol > this.column)
			boolOutOfRangeCol = true;
		// if any of the values are bigger or equal to zero, it is acceptable input
		if (startRow >= 0)
			boolStartRow = true;
		if (startCol >= 0)
			boolStartCol = true;
		if (destRow >= 0)
			boolDestRow = true;
		if (destCol >= 0)
			boolDestCol = true;
		
		// solution to be returned, since I wanted to be specific with the if statements
		// using if else instead of any else statements, I had to initialize the string
		// that is to be returned
		
		String solution = "";	
		if(boolStartRow && boolStartCol && boolDestRow && boolDestCol && !boolOutOfRangeCol && !boolOutOfRangeRow) {//statement is valid
			// for SouthWest	(down and left)
			// 			y-axis					x-axis
			if(startRow >= destRow && startCol >= destCol) {
				solution =  this.goSouthWest(startRow, startCol, destRow, destCol, path);
			}
			// for SouthEast	(down and right)
			// 			y-axis					x-axis
			else if(startRow >= destRow && startCol <= destCol) {
				solution = this.goSouthEast(startRow, startCol, destRow, destCol, path);
			}
			// for NorthEast	(up and left)	
			// 			y-axis					x-axis
			else if(startRow <= destRow && startCol <= destCol) {
				solution = this.goNorthEast(startRow, startCol, destRow, destCol, path);
			}
			// for northwest	(up and right)	
			// 			y-axis					x-axis
			else if(startRow <= destRow && startCol >= destCol) {
				solution = this.goNorthWest(startRow, startCol, destRow, destCol, path);
			}
		}else{
			// throw exception if the initial if statements that evaluate
			// if the inputs were not valid with respect to restrictions in if statement
			// throw an exception
			throw new IllegalArgumentException();
		}
		// return the solution
		return solution;
	}

	/**
	 * This method returns a path from the source (startRow, startCol) to the destination (destRow, destCol).
	 * Please note that the returning path does not include the starting point.  
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow >= destRow </code> and <code> startCol >= destCol </code>
	 */
	
	private String goSouthWest (int startRow, int startCol, int destRow, int destCol , String path) {
		// base return statement for recursion 
		if(startRow == destRow && startCol == destCol) {
			return path;
		// if the base isn't satisfied, recursively return the coordinate + method for the next step
	    }else{
			if (startCol > destCol) { // if startCol is bigger than desired Column,  
				startCol--;  // negative increment until the desired value is obtained one by one + method for the next step
				path = path + "(" + startRow + "," + startCol + ")" + " " + goSouthWest(startRow, startCol, destRow, destCol, path); 
			}else if (startRow > destRow) { // if startRow is bigger than desired Column
				startRow--; // negative increment until the desired value is obtained one by one + method for the next step
				path = path + "(" + startRow + "," + startCol + ")" + " " + goSouthWest(startRow, startCol, destRow, destCol, path);
			}
		}
		return path;
	}
	
	/**
	 * This method returns a path from the source (startRow, startCol) to the destination (destRow, destCol).
	 * Please note that the returning path does not include the starting point. 
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow >= destRow </code> and <code> startCol <= destCol </code>
	 */
	private String goSouthEast (int startRow, int startCol, int destRow, int destCol , String path) {
		// base return statement for recursion 
		if(startRow == destRow && startCol == destCol) { 
			return path;
		// if the base isn't satisfied, recursively return the coordinate + method for the next step
		}else{
			if (startCol < destCol) { // if startCol is smaller than desired Column
				startCol++; // increment one by one and return the location + recursive method call of the next location
				path = path + "(" + startRow + "," + startCol + ")" + " " + goSouthEast(startRow, startCol, destRow, destCol, path);
		}
			else if(startRow > destRow) { // if startRow is bigger than the desired location of Row
				startRow--; // negative increment one by one and return the location + recursive method call of the next location
				path = path + "(" + startRow + "," + startCol + ")" + " " + goSouthEast(startRow, startCol, destRow, destCol, path);
			}
		}
		return path;	
	}
	
	/**
	 * This method returns a path from the source (startRow, startCol) to the destination (destRow, destCol).
	 * Please note that the returning path does not include the starting point. 
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow <= destRow </code> and <code> startCol >= destCol </code>
	 */
	private String goNorthEast (int startRow, int startCol, int destRow, int destCol , String path) {
		if(startRow == destRow && startCol == destCol) {
			return path;
			// if the base isn't satisfied, recursively return the coordinate + method for the next step
		}else{
			if (startCol < destCol) { // if startCol is smaller than desired location of Column
				startCol++; // increment one by one and return the location + recursive method call of the next location
				path = path + "(" + startRow + "," + startCol + ")" + " " + goNorthEast(startRow, startCol, destRow, destCol, path);
			}else if (startRow < destRow) { // if startRow is smaller than desired Row,
				startRow++; // increment one by one and return the location + recursive method call of the next location
				path = path + "(" + startRow + "," + startCol + ")" + " " + goNorthEast(startRow, startCol, destRow, destCol, path);
			}
		}
		return path;
	}

	/**
	 * This method returns a path from the source (startRow, startCol) to the destination (destRow, destCol).
	 * Please note that the returning path does not include the starting point. 
	 * @param startRow is the starting row of the path 
	 * @param startCol is the starting column of the path
	 * @param destRow is the destination row
	 * @param destCol is the destination column
	 * @param path is the path that is constructed while the recursive method is called. In first round,  it will be "".
	 * @return returns a string representing the path to the destination. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 * @pre <code> startRow >= destRow </code> and <code> startCol <= destCol </code>
	 */
	private String goNorthWest (int startRow, int startCol, int destRow, int destCol , String path) {
		if(startRow == destRow && startCol == destCol) {
			return path;
			// if the base isn't satisfied, recursively return the coordinate + method for the next step
		}else{
			if (startCol > destCol) { // if startCol is bigger than desired location of Column
				startCol--; // negative increment one by one and return the location + recursive method call of the next location
				path = path + "(" + startRow + "," + startCol + ")" + " " + goNorthWest(startRow, startCol, destRow, destCol, path);
		   }else if (startRow < destRow) { // if startRow is smaller than desired Row,
				startRow++; // increment one by one and return the location + recursive method call of the next location
				path = path + "(" + startRow + "," + startCol + ")" + " " + goNorthWest(startRow, startCol, destRow, destCol, path);
			}
		}
		return path;
	}
	
	
	private void clearGridAndPath() {
		for(int y = 0; y < row; y++) {
			for(int x = 0; x < column; x++) {
				// false means position has been visited so resetting the grid by setting every entry to true
				this.map[y][x] = true;
			}
		}
	}
	
	private boolean findPathHelper(int currRow, int currCol) {
		// base function for the recursive algorithm
		// if the car is out of the city 
		if(currRow == 0 || currCol == 0 || currRow == this.row - 1  || currCol == this.column - 1) {
			// add the last element of the path into the list and return true going back in the recursive methods that are called
			foundPath += " (" + currRow + "," + currCol + ")";
			return true; 
			
		}else{ // if the base case isn't true, if the car is not out of the city yet
			// randomly generate an integer from 0 to 3
			// the randomly generated integer responds to either up, down, left or right
			int x = (int)(Math.random() * ((3) + 1)); 
			
			if(x == 0) { // if random integer is 0, try to go up
				
				if(map[currRow + 1][currCol]) { // only go up if the car has not been there before (the entry in the boolean matrix is false)		
					map[currRow][currCol] = false; // current location is marked false on map because we were able to move
						foundPath += " (" + currRow + "," + currCol + ")"; // add the Path into the foundPath List
						return findPathHelper(currRow + 1, currCol); // return the next location's boolean state for the car, recursively. 
				}else{
						return false; // if false is returned, the list and the matrix are reset. This causes it to call findPath with the original/initial arguments
					 }
				
	 }else if(x == 1) { // if random integer is one, try to go down
			
				if(map[currRow - 1][currCol]) {  // only go down if the car has not been there before (the entry in the boolean matrix is false)		
						map[currRow][currCol] = false; // current location is marked false on map because we were able to move
						foundPath += " (" + currRow + "," + currCol + ")"; // add the Path into the foundPath List
						return findPathHelper(currRow - 1, currCol); // return the next location's boolean state for the car, recursively. 
				}else{
						return false; // if false is returned, the list and the matrix are reset. This causes it to call findPath with the original/initial arguments
					 }
				
		}else if(x == 2) { // if random integer is two, try to go right
			
				if(map[currRow][currCol + 1]) { // only go right if the car has not been there before (the entry in the boolean matrix is false)
					map[currRow][currCol] = false; // current location is marked false on map because we were able to move
					foundPath += " (" + currRow + "," + currCol + ")"; // add the Path into the foundPath List
					return findPathHelper(currRow, currCol + 1); // return the next location's boolean state for the car, recursively. 
				}else{
					 	return false; // if false is returned, the list and the matrix are reset. This causes it to call findPath with the original/initial arguments
					 }
				
		}else{ // only one more value is left out of if/if-else statements which is 3. if random integer is 3, try to go left
			
				if(map[currRow][currCol - 1]) { // only go left if the car has not been there before (the entry in the boolean matrix is false)
					map[currRow][currCol] = false; // current location is marked false on map because we were able to move
					foundPath += " (" + currRow + "," + currCol + ")"; // add the Path into the foundPath List
					return findPathHelper(currRow, currCol - 1);  // return the next location's boolean state for the car, recursively. 
				}else{
					return false; // if false is returned, the list and the matrix are reset. This causes it to call findPath with the original/initial arguments
				}
			}	
		}
	}	
	
	/**
	 * This method find a path from (startRow, startCol) to a border point of the city. 
	 * Please note that the starting point should be included in the path.
	 * @param startRow is the starting row of the path
	 * @param startCol is the starting column of the path
	 * @return is a path from (starting row, staring col) to a border point of the city. The format of the output is (x1,y1) (x2,y2) (x3,y3)...
	 */
	public String findPath (int startRow, int startCol) {
		clearGridAndPath(); // Setting every entry in the matrix to true for the game to begin
		// indirect recursion, while our boolean return type helper function keeps returning true, meaning that it didn't hit an intersection it was at before, skip the while condition
		// but if it return false, everything is set to default and the original arguments are put back in the helper function so that the game starts over from the beginning
		while(!findPathHelper(startRow, startCol)) { 
			clearGridAndPath(); // set every entry in the matrix to true, reset how the car impacted the boolean matrix (map)
			this.foundPath = ""; // set our foundPath variable to an empty string so that the returned String is only the path that passed the experiment
		}
		return this.foundPath; // while loop is done, car was able to get out of the city, return the String representation of the path car traveled
	}
				
}// end of class
	
	
