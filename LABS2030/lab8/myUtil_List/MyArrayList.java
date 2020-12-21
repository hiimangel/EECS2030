package myUtil_List;

public class MyArrayList<E> implements MyList<E> {

	private static final int DEFAULT_CAPACITY = 10;
	
	int size;  
	E [] elementData; 
	
	/**
	 * Create an empty list of capacity capa
	 * 
	 * @param capa the initial capacity. Assume greater than 0.
	 */
	@SuppressWarnings({"unchecked"})
    public MyArrayList(int kapasite) {
		this.elementData = (E[])new Object [kapasite]; 
	}

	/**
	 * Create an empty list of default capacity
	 * 
	 */
    @SuppressWarnings({"unchecked"})
    public MyArrayList() {
		this.elementData = (E[]) new Object [DEFAULT_CAPACITY];
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0; 
	}

	@Override
	public boolean contains(E o) {
		
		for(int i = 0; i < this.size; i++) {
			if(this.elementData[i].equals(o)) {
				return true;
			}
		} 
		
		return false;
	}

	@Override
	public void add(E o) {
		if(this.size == this.elementData.length) {
			@SuppressWarnings("unchecked")
			E [] temp = (E[]) new Object [size + DEFAULT_CAPACITY];
			for(int i = 0; i < size; i++) {
				temp[i] = this.elementData[i];
			}
			temp[size] = o;
			this.elementData = temp; 
		}else{
			this.elementData[size] = o; 
		}
		size++; 
	}

	@Override
	public void remove(E o) {
		int index = this.indexOf(o);
		if(index >= 0) {
			for(int i = index; i < this.size; i++) {
				this.elementData[i] = this.elementData[i+1];
			}
		this.size--;
		}
	}

	@Override
	public E get(int index) {
		if(this.size == 0 || index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException();
		return this.elementData[index];
	}

	@Override
	public E set(int index, E element) {
		if(this.size == 0 || index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException();
		E e = this.elementData[index]; 
		this.elementData[index] = element;
		return e;
	}

	@Override
	public void add(int index, E element) {
		if(this.size == 0 || index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		for(int i = this.size -1; i >= index; i--){
			this.elementData[i + 1] = this.elementData[i];
		}
		this.elementData[index] = element;
		this.size++;
	}

	@Override
	public E remove(int index) {
		
		if(this.size == 0 || index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		E e = this.elementData[index];
		for(int i = index; i < this.size; i++) {
			this.elementData[i] = this.elementData[i+1];
		}
		size--;
		return e;
	}

	@Override
	public int indexOf(E o) {		
		for(int i = 0 ; i < this.size; i++) {
			if(this.elementData[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E o) {
		for(int i = this.size -1; i > 0; i--){
			if(this.elementData[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}

}
//yea yea yea yea