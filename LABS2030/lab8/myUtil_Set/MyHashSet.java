package myUtil_Set;

import java.util.HashMap;
import java.util.Iterator;

public class MyHashSet<E> extends MyAbstractSet<E> {

	
	private HashMap <E, Object> map;   
	
	
    private static final Object PRESENT = new Object();
	
	
   
    public MyHashSet() {
        map = new HashMap<>();
    }
    
   
    public MyHashSet(int initialCapacity) {
        super(); 
    	this.map = new HashMap<>(initialCapacity);
    }
    
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
//end end end woah
