package expressions;
//Alp Baran Sirek
//217329251

public class AddAll extends ExpressionCollector {
	AddAll(){
		super.value = 0;
		

	}
	@Override
	 void evaluate() {
		Integer sum = 0;
		for(Expression exp : super.expressions) {
			sum += (Integer) exp.getValue();
		}
		super.value = sum;
	}
}
