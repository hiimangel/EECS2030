package myUtil_Set;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class MyTreeSet<E> extends MyAbstractSet<E> {

	
	 
	protected TreeMap <E, Object> map;   // non-private for testing purposes
	
	// Dummy value to associate with an Object in the backing Map
    private static final Object PRESENT = new Object();
	
	
    /**
     * Constructs a new, empty set;  
     */
    public MyTreeSet() {
    	map = new TreeMap<>();
    }
    
    
    // addAll
	@Override
    public Iterator<E> iterator(){
		return this.map.keySet().iterator();
    }
       
	@Override
	public boolean contains(E o) {
		if(this.map.containsKey(o)) {
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public boolean add(E e) {
		if(this.contains(e)) {
			return false;
		}else{
			this.map.put(e, PRESENT);
			this.size++;
			return true;
		}
	}
	
	@Override
	public void clear() {
		this.map.clear();
		this.size = 0;
	}
	
	@Override
	public boolean remove(E o) {
		if(this.contains(o)){
			this.map.remove(o);
			this.size--;
			return true;
		}
		return false;
	}
}
//woah woah woah yeeeee