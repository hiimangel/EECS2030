package myUtil_Set;
public class TestMyHashSet extends TestSet {
	
	@Override
	protected MySet<String> loadSet() {
		return new MyHashSet<String>();
	}
	
	
	@Override
	protected MySet<Integer> loadSetInt() {
		return new MyHashSet<Integer>();
	}
	
}
