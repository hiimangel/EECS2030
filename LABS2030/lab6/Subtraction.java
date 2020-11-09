package expressions;

public class Subtraction extends Expression {

	Subtraction(int left, int right) {
		super(left, right);
		super.value = super.left - super.right;	
	}

	@Override
	public void evaluate() {
		
	}
}


