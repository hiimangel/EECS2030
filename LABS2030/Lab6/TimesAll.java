package expressions;
//Alp Baran Sirek
//217329251
public class TimesAll extends ExpressionCollector {
	
	@Override
	void evaluate() {
		Integer result = 1;
		for(Expression exp : super.expressions) {
			result *= (Integer) exp.getValue();
		}
		super.value = result;
	}

}
