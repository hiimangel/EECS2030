package expressions;
//Alp Baran Sirek
//217329251
public class Equal extends Expression {

	Equal(int left, int right) {
		super(left, right);
		super.value = super.left == super.right;
	}
	@Override
	void evaluate() {
	}
}
