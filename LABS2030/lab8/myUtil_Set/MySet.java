package myUtil_Set;

public interface MySet<E> extends MyCollection<E> {
	
	// add no new methods
	
	// actually Set interface add specifications here, but we ignore most of them for simplicity
	/**
     * Add element o into the set, if it is not already present. 
     *  
    *
    * @param o element to be added to the list. Assume o is not null. 
    */
    boolean add(E o);
}
