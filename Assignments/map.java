
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
Course Section:E
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
		
		// Setting every entry of the boolean matrix to true, indicating that the cop car has not been deployed there
		// if any entry is false, it is not a path that can be taken by the car, if true, can be passed through
		for(int i = 0; i < row; i++) {
			for(int k = 0; k < column; k++) {
				this.map[i][k] = true;
			}
		}
		
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
		// Please complete this method
		// you should decide on what should be returned. This return statement is here to avoid compiler error.
		boolean startrow_true  = false; //
		boolean startcol_true = false;
		boolean destrow_true = false;
		boolean destcol_true = false;
		boolean isitoutofrange_row = false;
		boolean isitoutofrange_col = false;
		if (startRow > this.row || destRow > this.row)
			isitoutofrange_row = true;
		if (startCol > this.column || destCol > this.column)
			isitoutofrange_col = true;
		if (startRow >= 0)
			startrow_true = true;
		if (startCol >= 0)
			startcol_true = true;
		if (destRow >= 0)
			destrow_true = true;
		if (destCol >= 0)
			destcol_true = true;
		String solution = "";	
		if(startrow_true && startcol_true && destrow_true && destcol_true && !isitoutofrange_col && !isitoutofrange_row) {//statement is valid
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
		}	
		else {
			throw new IllegalArgumentException();

		}
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
		// Please complete this method
		// you should decide on what should be returned. This return statement is here to avoid compiler error.
		if(startRow == destRow && startCol == destCol) {
			return path;

		}else{
			if (startCol > destCol) {
				startCol--;
				path = path + "(" + startRow + "," + startCol + ")" + " " + goSouthWest(startRow, startCol, destRow, destCol, path);
			}else if (startRow > destRow) {
				startRow--;
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
	// CHANGE IT TO PRIVATE AFTERWARDS !!!!!!!!!
	private String goSouthEast (int startRow, int startCol, int destRow, int destCol , String path) {
		// Please complete this method
		// you should decide on what should be returned. This return statement is here to avoid compiler error.
		// down and right !!!
		// else if(startRow >= destRow && startCol <= destCol) {
 
		if(startRow == destRow && startCol == destCol) {
			return path;
		}else{
		
			if (startCol < destCol) {
				startCol++;
				path = path + "(" + startRow + "," + startCol + ")" + " " + goSouthEast(startRow, startCol, destRow, destCol, path);
		}	
			else if (startRow > destRow) {
				startRow--;
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
		// Please complete this method
		// you should decide on what should be returned. This return statement is here to avoid compiler error.
		if(startRow == destRow && startCol == destCol) {
			return path;

		}else{
			if (startCol < destCol) {
				startCol++;
				path = path + "(" + startRow + "," + startCol + ")" + " " + goNorthEast(startRow, startCol, destRow, destCol, path);
			}else if (startRow < destRow) {
				startRow++;
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
		// Please complete this method
		// you should decide on what should be returned. This return statement is here to avoid compiler error.
		if(startRow == destRow && startCol == destCol) {
			return path;

		}else{
			if (startCol > destCol) {
				startCol--;
				path = path + "(" + startRow + "," + startCol + ")" + " " + goNorthWest(startRow, startCol, destRow, destCol, path);
		   }else if (startRow < destRow) {
				startRow++;
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
		if(currRow == 0 || currCol == 0 || currRow == this.row - 1  || currCol == this.column - 1) {
			foundPath += " (" + currRow + "," + currCol + ")";
			return true;
		}else{
		int x = (int)(Math.random() * ((3) + 1));
		if(x == 0) {
			if(map[currRow + 1][currCol]) {
				map[currRow][currCol] = false; // current location is false on map
					foundPath += " (" + currRow + "," + currCol + ")";
					System.out.println("whatever 1");
					return findPathHelper(currRow + 1, currCol);
				}else{
					return false;
					}
				
		}else if(x == 1) {
				if(map[currRow - 1][currCol]) {
					map[currRow][currCol] = false;
					foundPath += " (" + currRow + "," + currCol + ")";
					System.out.println("whatever2");
					return findPathHelper(currRow - 1, currCol);
					
				}else{
					return false;
				}
				
		}else if(x == 2) {
				if(map[currRow][currCol + 1]) {
					map[currRow][currCol] = false;
					foundPath += " (" + currRow + "," + currCol + ")";
					System.out.println("whatever3");
					return findPathHelper(currRow, currCol + 1);
					
				}else{
					return false;
				}
		}else{
				if(map[currRow][currCol - 1]) {
					map[currRow][currCol] = false;
					foundPath += " (" + currRow + "," + currCol + ")";
					System.out.println("whatever4");
					return findPathHelper(currRow, currCol - 1);
				}else{
					return false;
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
		clearGridAndPath();
		while(!findPathHelper(startRow, startCol)) {
			clearGridAndPath();
			this.foundPath = "";
		}
		System.out.println(foundPath);
		return this.foundPath;
	}
				
}// end of class
	
	
	

		
