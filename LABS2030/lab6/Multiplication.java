package expressions;

public class Multiplication extends Expression {

	Multiplication(int left, int right) {
		super(left, right);
		super.value = super.left * super.right;	
	}

	@Override
	public void evaluate() {
		
	}
}
