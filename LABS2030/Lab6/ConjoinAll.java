package expressions;
//Alp Baran Sirek
//217329251
public class ConjoinAll extends ExpressionCollector {
	
	
	
	@Override
	void evaluate() {
		super.value = true;
		for(Expression exp : super.expressions) {
			if(!(Boolean)exp.getValue()) {
				super.value = false;
				break;
			
			}
		}
	}
}
