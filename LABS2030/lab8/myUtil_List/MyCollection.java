package myUtil_List;

public interface MyCollection <E>{
	
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
     *         element, <tt>false</tt> otherwise
     * 
     */
    boolean contains(E o);
    
    
    /**
     * Add element o into (the end of) the collection.
     *  
    *
    * @param o element whose presence in this collection is to be ensured. Assume o is not null. 
    */
    void add(E o);
    
    
    /**
     * Removes a single instance of the specified element from this
     * collection, if it is present.  
     * More formally, 
     * removes an element <tt>e</tt> such that
     * <tt>o.equals(e)</tt>. If
     * this collection contains one or more such elements,   
     * removes the first occurrence of the specified element. If this collection does not contain the element, it is unchanged.
     * @param o element to be removed from this collection, if present. Assume o is not null.
     * 
     */
    void remove(E o);

    
    
    


}
