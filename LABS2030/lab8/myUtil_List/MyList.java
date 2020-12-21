package myUtil_List;

public interface MyList<E> extends MyCollection<E> {
	
	/**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    E get(int index);
    
    
    /**
    * Replaces the element at the specified position in this list with the
    * specified element
    *
    * @param index index of the element to replace
    * @param element element to be stored at the specified position. Assume not null.
    * @return the element previously at the specified position
    * @throws IndexOutOfBoundsException if the index is out of range
    *         
    */
   E set(int index, E element);
   
   
   /**
    * Inserts the specified element at the specified position in this list. 
    *  Shifts the element currently at that position
    * (if any) and any subsequent elements to the right (their
    * indices increase by one).
    *
    * @param index index at which the specified element is to be inserted
    * @param element element to be inserted. Assume not null.
    * @throws IndexOutOfBoundsException if the index is out of range
    *   
    */
   void add(int index, E element);
   
   
   
   /**
    * Removes the element at the specified position in this list. 
    * Shifts any subsequent elements to the left (their indices decrease).  
    * Returns the element that was removed from the
    * list.
    *
    * @param index the index of the element to be removed
    * @return the element previously at the specified position
    * @throws IndexOutOfBoundsException if the index is out of range
    *     
    */
   E remove(int index);
   
   
   
   // Search Operations

   /**
    * Returns the index of the first occurrence of the specified element
    * in this list, or -1 if this list does not contain the element.
    * More formally, returns the lowest index <tt>i</tt> such that
    * <tt>o.equals(get(i))</tt>,
    * or -1 if there is no such index.
    *
    * @param o element to search for. Assume o is not null
    * @return the index of the first occurrence of the specified element in
    *         this list, or -1 if this list does not contain the element
    * */
   int indexOf(E o);


   /**
    * Returns the index of the last occurrence of the specified element
    * in this list, or -1 if this list does not contain the element.
    * More formally, returns the highest index <tt>i</tt> such that
    * <tt>o.equals(get(i))</tt>,
    * or -1 if there is no such index.
    *
    * @param o element to search for. Assume o is not null.
    * @return the index of the last occurrence of the specified element in
    *         this list, or -1 if this list does not contain the element
    * 
    */
   int lastIndexOf(E o);

   
   

	
}
