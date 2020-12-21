package myUtil_Set;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class MyLinkedHashSet<E> extends MyAbstractSet<E> {

	
	public LinkedHashMap <E, Object> map;  // non-private for testing purposes
	
	/** Dummy value to associate with an Object in the backing Map
    used as vlaues for all the keys in the backing Map
    */
    private static final Object PRESENT = new Object();
	
	
    /**
     * Constructs a new, empty set; 
     */
    public MyLinkedHashSet() {
    	map = new LinkedHashMap<>();
    }
    
    /**
	 * Create an empty set of default capacity
	 * 
	 */
    public MyLinkedHashSet(int initialCapacity) {
    	this.map = new LinkedHashMap<>(initialCapacity);
    }
    
    
	@Override
    public Iterator<E> iterator(){
		return this.map.keySet().iterator();
    }
       
	@Override
	public boolean contains(E o) {
		if(this.map.containsKey(o)) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public boolean add(E e) {
		if(this.contains(e)) {
			return false;
		}else{
			this.map.put(e,PRESENT);
			this.size++;
			return true;
		}
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

	@Override
	public void clear() {
		this.map.clear();
		this.size = 0;
	}
	
	
	 
}

//woah woah woah the end 
