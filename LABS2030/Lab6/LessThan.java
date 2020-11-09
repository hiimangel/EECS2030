package expressions;
//Alp Baran Sirek
//217329251

public class LessThan extends Expression {

	LessThan(int left, int right) {
		super(left, right);
		super.value = super.left < super.right;
	}
	@Override
	void evaluate() {
	}
}
