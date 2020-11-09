package expressions;
//Alp Baran Sirek
//217329251

public class DisjoinAll extends ExpressionCollector {

	
	
	@Override
	void evaluate() {
		super.value = false;
		for(Expression exp : super.expressions) {
			if((Boolean)exp.getValue()) {
				super.value = true;
				break;
			}
		}
	}
}
