package expressions;

/*
 * Your Tasks:
 * 1. Read the problem description and inheritance hierarchy diagram in the instructions.
 * 2. Add the necessary subclasses and implement the required methods.
 * 3. Study tests in TestExpressions.java to understand what's required to help your implementation. 
 */
//Alp Baran Sirek
//217329251

public class Expression {
	
	protected static int numExpressionCreated;
	
	/** two operands of this expression 
	**/
	protected int left;
	protected int right;
	
	/**
	 * Value of evaluation result.
	 * Dynamically, value may be an Integer or a Boolean.
	 */
	protected Object value;
	
	/**
	 * Initialize an expression.
	 * @param left left integer value
	 * @param right right integer value
	 */
	Expression(int left, int right) {
		// COMPLETE THIS
		this.left = left;
		this.right = right;
		Expression.numExpressionCreated++;
	}
	/**
	 * Obtain the evaluation result.
	 * @return evaluation result of this expression, after the latest call to evaluate()
	 */
	Object getValue() {
		return value;
		
		// COMPLETE THIS 
	}
	
	void evaluate() {
		// COMPLETE THIS 
		// Hint: Leave this method as is, and override it in all subclasses such as Addition, GreaterThan.
		System.out.println("whatever, this shouldnt occur");
	}
}
