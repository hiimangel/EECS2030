package myUtil_Set;

//import java.util.Collection;
import java.util.Iterator;

public interface MyCollection <E> extends Iterable<E> {
	
	// Query Operations

    /**
     * Returns the number of elements in this collection.  
     *
     * @return the number of elements in this collection
     */
    int size();

    
    /**
     * Returns <tt>true</tt> if this collection contains no elements.
     *
     * @return <tt>true</tt> if this collection contains no elements
     */
    boolean isEmpty();
    
    
    /**
     * Returns <tt>true</tt> if this collection contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this collection
     * contains at least one element <tt>e</tt> such that
     * <tt>o.equals(e)</tt>.
     *
     * @param o element whose presence in this collection is to be tested. Assume o is not null.
     * 
     * @return <tt>true</tt> if this collection contains the specified
     *         element
     * 
     */
    boolean contains(E o);
    
    
    /**
     * Add element o into (the end of) the collection.
     *  
    *
    * @param o element whose presence in this collection is to be ensured. Assume o is not null. 
    * @return <tt>true</tt> if the collection changed as a result of the call
    */
    boolean add(E o);
    
    
    /**
     * Adds all of the elements in the specified collection to this collection
     * 
     */
    public void addAll(MyCollection<? extends E> c);
    
    
    /**
     * Removes a single instance of the specified element from this
     * collection, if it is present.  
     * More formally,
     * removes an element <tt>e</tt> such that
     * <tt>o.equals(e)</tt>.  If this collection does not contain the element, it is unchanged.
     * @param o element to be removed from this collection, if present. Assume o is not null.
     * @return <tt>true</tt> if an element was removed as a result of this call.
     */
    boolean remove(E o);
    

    	
    /**
     * Removes all of the elements from this collection.
     * The collection will be empty after this method returns.
     *
     */
    void clear();
    
    /**
     * Returns an iterator over the elements in this collection.  There are no
     * guarantees concerning the order in which the elements are returned
     * (unless this collection is an instance of some class that provides a
     * guarantee).
     *
     * @return an <tt>Iterator</tt> over the elements in this collection
     */
    Iterator<E> iterator();
    
    

    
    
    


}
