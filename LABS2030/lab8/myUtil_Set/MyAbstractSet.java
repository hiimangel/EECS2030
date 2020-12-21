package myUtil_Set;

import java.util.Collection;
import java.util.Iterator;

public abstract class MyAbstractSet<E> implements MySet<E> {

	 
	// some attributes common to subclasses
	int size;   
	
	@Override
	public int size() {
		return this.size;
	}
	

	@Override
	public boolean isEmpty() {
		return this.size ==0;
	}
	
	public void addAll(MyCollection<? extends E> c) {
	    // MyCollection implements Iterable, so can use for each loop or iterator
		for (E e: c) {
			add(e);
		}
		
	};

	// methods to be implemented by subclassess, implementation dependent
	@Override
	public abstract boolean contains(E o);
	 

	@Override
	public abstract boolean add(E e);
	 
	@Override
	public abstract boolean remove(E o);
	 
	@Override
	public abstract void clear();
	
	
 
	
 
	// override new method
	public String toString() {
		String s = "[ ";
		for (E e: this)
			s += e + " ";
		s += "]";
		
		return s;

	}

}
