package myAdapter;


import java.util.*;

public class ListAdapter 
implements HList
{	
	//SOTTOCLASSE
	
	private class MyIterator
	implements HIterator {
		
		int index;
		boolean possibleState;
		
		public MyIterator() {
			index = 0;
			possibleState = false;
		}
		
		@Override
		public boolean hasNext() {
			if((index+1)>size()) {
				return false;
			}
			return true;
		}

		@Override
		public Object next() {
			possibleState = true;
			if (hasNext() == false) {
				throw new NoSuchElementException();
			}
			return vector.elementAt(index++);
		}

		@Override
		public void remove() {
			if (possibleState == false) {
				throw new java.lang.IllegalStateException();
			}
			try {
				vector.removeElementAt(--index);
			}
			catch(ArrayIndexOutOfBoundsException aioobe) {  //questa eccezione non e' presente nell'interfaccia dell'iteratore
				aioobe.printStackTrace();
				return;   			
			}
			possibleState = false;
		}
	}
	
	
	//SOTTOCLASSE
	
	private class MyListIterator
	implements HListIterator {
		
		int index;
		boolean possibleState;
		boolean nex;
		boolean prev;
		
		public MyListIterator(int index2) {
			index = index2;
			possibleState = false;
			nex = false;
			prev = false;
		}

		public MyListIterator() {
			index = 0;
			possibleState = false;
		}

		@Override
		public void add(Object e) {
			if(e == null) {
				throw new IllegalArgumentException();
			}
			vector.insertElementAt(e,index);
			possibleState = false;
		}

		@Override
		public boolean hasNext() {
			if((index+1)>size()) {
				return false;
			}
			return true;
		}

		@Override
		public boolean hasPrevious() {
			if((index-1)<0) {
				return false;
			}
			return true; 
		}

		@Override
		public Object next() {
			possibleState = true;
			if (hasNext() == false) {
				throw new NoSuchElementException();
			}
			nex = true;

			prev = false;
			return vector.elementAt(index++);
		}

		@Override
		public int nextIndex() {
			return index+1;
		}

		@Override
		public Object previous() {
			possibleState = true;
			if (hasPrevious() == false) {
				throw new NoSuchElementException();
			}
			nex = false;
			prev = true;
			return vector.elementAt(--index);
		}

		@Override
		public int previousIndex() {			
			return index-1;
		}

		@Override
		public void remove() {
			if (possibleState == false) {
				throw new java.lang.IllegalStateException();
			}
			try {
				if(nex) {
					vector.removeElementAt(--index);
				}
				else {
					vector.removeElementAt(index);
				}
			}
			catch(ArrayIndexOutOfBoundsException aioobe) {   //questa eccezione non e' presente nell'interfaccia di ListIterator
				aioobe.printStackTrace();
				return;   			
			}
			
			possibleState = false;
			nex = false;
			prev = false;
		}

		@Override
		public void set(Object o) {
			if(o == null){
				throw new IllegalArgumentException();
			}
			if (possibleState == false) {
				throw new java.lang.IllegalStateException();
			}
			try {
				if(nex) {
					vector.setElementAt(o,--index);
				}
				else {
					vector.setElementAt(o,index);					
				}
			}
			catch(ArrayIndexOutOfBoundsException aioobe) {   //questa eccezione non e' presente nell'interfaccia di ListIterator
				aioobe.printStackTrace();  			
			}
			nex = false;
			prev = false;
		}
	}
	
	Vector vector;
	public ListAdapter(){
		vector = new Vector();
	}
	
	/**
	 * Inserts the specified element at the specified position in this list. Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
	 * @param index - index at which the specified element is to be inserted.
	 * @param element - element to be inserted.
	 * @throws IndexOutOfBoundsException - if the index is out of range (index &lt; 0 || index &gt; size()).
	 * @throws NullPointerException - if the specified element is null.
	 */
	@Override
	public void add(int index, Object element) {
		if(element == null) {
			throw new NullPointerException();
		}
		if(index<0 || index>size()) {
			throw new IndexOutOfBoundsException();
		}
		vector.insertElementAt(element, index);
	}
	
	
	/**
	 * Appends the specified element to the end of this list.
	 * Lists that support this operation may place limitations on what elements may be added to this list. In particular, this lists will refuse to add null elements.
	 * @param o - element to be appended to this list.
	 * @return true (as per the general contract of the Collection.add method).
	 */
	@Override
	public boolean add(Object o) {
		add(size(),o);
		return false;
	}
	
	/**
	 * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator. The behavior of this operation is unspecified if the specified collection is modified while the operation is in progress. (Note that this will occur if the specified collection is this list, and it's nonempty.)
	 * @param c - collection whose elements are to be added to this list.
	 * @return true if this list changed as a result of the call.
	 * @throws NullPointerException - if the specified collection contains one or more null elements, or if the specified collection is null.
	 */
	@Override
	public boolean addAll(HCollection c) {
		if(c.isEmpty()) {
			return false;		
		}
		Object[] arr = c.toArray();
		for(int i=0;i<c.size();i++) {
			add(arr[i]);
		}
		return true;	
	}

	/**
	 * Inserts all of the elements in the specified collection into this list at the specified position. Shifts the element currently at that position (if any) and any subsequent elements to the right (increases their indices). The new elements will appear in this list in the order that they are returned by the specified collection's iterator. The behavior of this operation is unspecified if the specified collection is modified while the operation is in progress. (Note that this will occur if the specified collection is this list, and it's nonempty.)
	 * @param index - index at which to insert first element from the specified collection.
	 * @param c - elements to be inserted into this list.
	 * @return true if this list changed as a result of the call.
	 * @throws IndexOutOfBoundsException - if the index is out of range (index &lt; 0 || index &gt; size()).
	 * @throws NullPointerException - if the specified collection contains one or more null elements, or if the specified collection is null.
	 */
	@Override
	public boolean addAll(int index, HCollection c) {
		if(c.isEmpty()) {
			return false;		
		}
		Object[] arr = c.toArray();
		for(int i=0;i<c.size();i++) {
			add(index,arr[i]);
			index++;
		}
		return true;
	}
	
	/**
	 * Removes all of the elements from this list. This list will be empty after this call returns (unless it throws an exception).
	 */
	@Override
	public void clear() {
		vector.removeAllElements();
	}
	
	/**
	 * Returns true if this list contains the specified element. More formally, returns true if and only if this list contains at least one element e such that (o==null ? e==null : o.equals(e)).
	 * @param o - element whose presence in this list is to be tested.
	 * @return true if this list contains the specified element.
	 * @throws NullPointerException - if the specified element is null.
	 */
	@Override
	public boolean contains(Object o) {
		if(o == null) {
			throw new NullPointerException();
		}
		return vector.contains(o);
	}
	
	/**
	 * Returns true if this list contains all of the elements of the specified collection.
	 * @param c - collection to be checked for containment in this list.
	 * @return true if this list contains all of the elements of the specified collection.
     * @throws NullPointerException - if the specified collection is null.
	 */
	@Override
	public boolean containsAll(HCollection c) {
		if (c == null) {
			throw new NullPointerException();
		}
		Object[] arr = c.toArray();
		for(int i=0;i<c.size();i++) {
			if(contains(arr[i]) == false) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Compares the specified object with this list for equality. Returns true if and only if the specified object is also a list, both lists have the same size, and all corresponding pairs of elements in the two lists are equal. (Two elements e1 and e2 are equal if (e1==null ? e2==null : e1.equals(e2)).) In other words, two lists are defined to be equal if they contain the same elements in the same order. This definition ensures that the equals method works properly across different implementations of the List interface.
	 * @param o - the object to be compared for equality with this list.
	 * @return true if the specified object is equal to this list.
	 */
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof ListAdapter)) {
			return false;
		}
		HList newList = (ListAdapter) o;
		if(size() != newList.size()) {
			return false;
		}
		//no element in a list can be null
		for(int i=0;i<size();i++) {
			if(!get(i).equals(newList.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns the element at the specified position in this list.
	 * @param index - index of element to return.
	 * @return the element at the specified position in this list.
	 * @throws IndexOutOfBoundsException - if the index is out of range (index &lt; 0 || index &gt;= size()).
	 */
	@Override
	public Object get(int index){
		if(index<0 || index>=size()) {
			throw new IndexOutOfBoundsException();
		}
		return vector.elementAt(index);
	}
	
	/**
	 * Returns the hash code value for this list. The hash code of a list is defined to be the result of the following calculation:
  	 * hashCode = 1;
     * Iterator i = list.iterator();
     * while (i.hasNext()) {
     * Object obj = i.next();
     * hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
     * }
     * This ensures that list1.equals(list2) implies that list1.hashCode()==list2.hashCode() for any two lists, list1 and list2, as required by the general contract of Object.hashCode.
     * @return the hash code value for this list.
	 */
	@Override
	public int hashCode() {								
		int hashC = 1;
		HIterator i = iterator();
		while (i.hasNext()) {
		    Object obj = i.next();
			System.out.println(obj);
		    hashC = 31*hashC + (obj==null ? 0 : obj.hashCode());
		}
		return hashC;
	}
	
	/**
	 * Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element. More formally, returns the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index.
	 * @param o - element to search for.
	 * @return the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
	 * @throws NullPointerException - if the specified element is null.
	 */
	@Override
	public int indexOf(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		return vector.indexOf(o);
	}
	
	/**
	 * Returns true if this map contains no key-value mappings.
	 * @return true if this map contains no key-value mappings.
	 */
	@Override
	public boolean isEmpty() {
		return vector.isEmpty();
	}
	
	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 * @return an iterator over the elements in this list in proper sequence.
	 */
	@Override
	public HIterator iterator() { //
		HIterator iter = new MyIterator();
		return iter;
	}
	
	/**
	 * Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element. More formally, returns the highest index i such that (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index.	 * 
	 * @param o - element to search for.
	 * @return the index in this list of the las occurrence of the specified element, or -1 if this list does not contain this element.
	 * @throws NullPointerException - if the specified element is null and this list does not support null elements (optional).
	 */
	@Override
	public int lastIndexOf(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		return vector.lastIndexOf(o);
	}
	
	/**
	 * Returns a list iterator of the elements in this list (in proper sequence).
	 * @return a list iterator of the elements in this list (in proper sequence).
	 */
	@Override
	public HListIterator listIterator() {
		HListIterator hListIterator = new MyListIterator();
		return hListIterator;
	}

	/**
	 * Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list. The specified index indicates the first element that would be returned by an initial call to the next method. An initial call to the previous method would return the element with the specified index minus one.
	 * @param index - index of first element to be returned from the list iterator (by a call to the next method).
	 * @return a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
	 * @throws IndexOutOfBoundsException - if the index is out of range (index &lt; 0 || index &gt; size()).
	 */
	@Override
	public HListIterator listIterator(int index) {
		if(index<0 || index>vector.size()) {
			throw new IndexOutOfBoundsException();
		}
		HListIterator hListIterator = new MyListIterator(index);
		return hListIterator;
	}
	
	/**
	 * Removes the element at the specified position in this list. Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list.
	 * @param index - the index of the element to removed.
	 * @return the element previously at the specified position.
	 * @throws IndexOutOfBoundsException - if the index is out of range (index &lt; 0 || index &gt; size()).
	 */
	@Override
	public Object remove(int index) {
		if(index<0 || index>=vector.size()) {
			throw new IndexOutOfBoundsException();
		}
		Object temp = get(index);
		vector.removeElementAt(index);
		return temp;
	}
	
	/**
	 * Removes the first occurrence in this list of the specified element. If this list does not contain the element, it is unchanged. More formally, removes the element with the lowest index i such that (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists).
	 * @param o - element to be removed from this list, if present.
	 * @return true if this list contained the specified element.
	 * @throws NullPointerException - if the specified element is null. 
	 */
	@Override
	public boolean remove(Object o) {
		if (o == null) {
			throw new NullPointerException();
		}
		return vector.removeElement(o);
	}
	
	/**
	 * Removes from this list all the elements that are contained in the specified collection.
	 * @param c - collection that defines which elements will be removed from this list.
	 * @return true if this list changed as a result of the call.
	 * @throws NullPointerException - if the specified collection is null.
	 */
	@Override
	public boolean removeAll(HCollection c) {
		if (c == null) {
			throw new NullPointerException();
		}
		boolean ret = false;
		for(int i=0;i<size();i++) {
			if(c.contains(get(i))) { //se c non contiene l'oggetto in pos i di list, allora elimino l'elemento arr[i]
				remove(get(i));
				i--;
				ret = true;
			}
		}
		return ret;
	}

	/**
	 * Retains only the elements in this list that are contained in the specified collection. In other words, removes from this list all the elements that are not contained in the specified collection.
	 * @param c - collection that defines which elements this set will retain.
	 * @return true if this list changed as a result of the call.
	 * @throws NullPointerException - if the specified collection is null.
	 */
	@Override
	public boolean retainAll(HCollection c) {
		if(c==null) {
			throw new NullPointerException();
		}
		boolean ret = false;
		for(int i = 0; i< size(); i++){
			if(!c.contains(get(i))){
				remove(get(i));
				i--;
				ret = true;
			}
		}
		return ret;
	}
	
	/**
	 * Replaces the element at the specified position in this list with the specified element.
	 * @param index - index of element to replace.
	 * @param element - element to be stored at the specified position.
	 * @return the element previously at the specified position.
	 * @throws IndexOutOfBoundsException - if the index is out of range (index &lt; 0 || index &gt;= size()).
	 * @throws NullPointerException - if the specified element is null
	 */
	@Override
	public Object set(int index, Object element) {
		if(element == null){
			throw new NullPointerException();
		}
		if(index<0 || index>=size()) {
			throw new IndexOutOfBoundsException();
		}
		Object temp = get(index);
		vector.setElementAt(element,index);
		return temp;
	}
	
	/**
	 * Returns the number of key-value mappings in this map. If the map contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
	 * @return the number of key-value mappings in this map.
	 */
	@Override
	public int size() {
		return vector.size();
	}

	/**
	 * Returns an array containing all of the elements in this list in proper sequence. Obeys the general contract of the Collection.toArray method.
	 * @return an array containing all of the elements in this list in proper sequence.
	 */
	@Override
	public Object[] toArray() {
		Object[] arr = new Object[size()];
		for(int i=0;i<size();i++) {
			arr[i] = vector.elementAt(i);
		}
		return arr;
	}
	
	/**
	 * Returns an array containing all of the elements in this list in proper sequence; the runtime type of the returned array is that of the specified array. Obeys the general contract of the Collection.toArray(Object[]) method.
	 * @param a - the array into which the elements of this list are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose.
	 * @return an array containing the elements of this list.
	 * @throws NullPointerException - if the specified array is null.
	 */
	@Override
	public Object[] toArray(Object[] a) {
		if(a == null) {
			throw new NullPointerException();
		}
		if (a.length < size()) {
			a = new Object[size()];
		}
		for(int i=0;i<size();i++) {
			a[i] = vector.elementAt(i);
		}
		return a;
	}
	
	/**
	 * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive. (If fromIndex and toIndex are equal, the returned list is empty.) The returned list is backed by this list, so non-structural changes in the returned list are reflected in this list, and vice-versa. The returned list supports all of the optional list operations supported by this list.
	 * This method eliminates the need for explicit range operations (of the sort that commonly exist for arrays). Any operation that expects a list can be used as a range operation by passing a subList view instead of a whole list. For example, the following idiom removes a range of elements from a list:
     * list.subList(from, to).clear();
	 * Similar idioms may be constructed for indexOf and lastIndexOf, and all of the algorithms in the Collections class can be applied to a subList.
	 * The semantics of the list returned by this method become undefined if the backing list (i.e., this list) is structurally modified in any way other than via the returned list. (Structural modifications are those that change the size of this list, or otherwise perturb it in such a fashion that iterations in progress may yield incorrect results.)
	 * @param fromIndex - low endpoint (inclusive) of the subList.
	 * @param toIndex - high endpoint (exclusive) of the subList.
	 * @return a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
	 * @throws IndexOutOfBoundsException - if the index is out of range (index &lt; 0 || index &gt; size()).
	 */
	@Override
	public HList sublist(int fromIndex, int toIndex) {
		if(fromIndex<0 || toIndex>size() || fromIndex>toIndex) {
			throw new IndexOutOfBoundsException();
		}
		return new Sublist(fromIndex,toIndex,this);
	}
	
	private class Sublist
	extends ListAdapter
	implements HList{
		int from;
		int to;
		ListAdapter oL;
		public Sublist(int fromIndex, int toIndex,ListAdapter originalList) {
			from = fromIndex;
			to = toIndex;
			System.out.println("in origine to e' " + to);
			oL = originalList;
		}
		
		
		//SOTTOCLASSE
		
		private class MyIterator
		implements HIterator {
			
			int index;
			boolean possibleState;
			
			public MyIterator() {
				index = from;
				possibleState = false;
			}
			
			@Override   
			public boolean hasNext() {			
				if((index+1)>to) {
					System.out.println("bohhhhhhhhh");					
					return false;
				}
				return true;
			}

			@Override
			public Object next() {				
				possibleState = true;
				System.out.println("POSSIBLESTATE MESSO A: " + possibleState);
				if (hasNext() == false) {
					throw new NoSuchElementException();
				}
				//return get(index++);
				return oL.vector.elementAt(index++);
			}

			@Override
			public void remove() {				
				if (possibleState == false) {
					System.out.println("ENTRAA");
					throw new java.lang.IllegalStateException();
				}
				//remove(--index);
				oL.vector.removeElementAt(--index);
				to--;
				possibleState = false;
				System.out.println("POSSIBLESTATE MESSO A: " + possibleState);

			}
			
		}
		
		
		//SOTTOCLASSE
		
		private class MyListIterator 
		implements HListIterator{

			int index;
			boolean possibleState;
			boolean nex;
			boolean prev;
			
			public MyListIterator(int index2) {			
				index = index2+from;
				possibleState = false;
			}

			public MyListIterator() {					
				index = from;
				possibleState = false;
			}

			@Override
			public void add(Object e) {				
				if(e == null) {
					throw new IllegalArgumentException();
				}
				oL.vector.insertElementAt(e,index);
				possibleState = false;
			}

			@Override
			public boolean hasNext() {				
				if((index+1)>to) {
					return false;
				}
				return true;
			}

			@Override
			public boolean hasPrevious() {				
				if((index-1)<from) {
					return false;
				}
				return true; 
			}

			@Override
			public Object next() {						
				possibleState = true;
				if (hasNext() == false) {
					throw new NoSuchElementException();
				}
				nex = true;
				prev = false;
				return oL.vector.elementAt(index++);
			}

			@Override
			public int nextIndex() {					
				return index+1;
			}

			@Override
			public Object previous() {					
				possibleState = true;
				if (hasPrevious() == false) {
					throw new NoSuchElementException();
				}
				nex = false;
				prev = true;
				return oL.vector.elementAt(--index);
			}

			@Override
			public int previousIndex() {				
				return index-1;
			}

			@Override
			public void remove() {						
				if (possibleState == false) {
					System.out.println("ENTRAA");

					throw new java.lang.IllegalStateException();

				}
				try {
					if(nex) {
						oL.vector.removeElementAt(--index);
						to--;
						System.out.println("ENTRAA");

					}
					else {
						oL.vector.removeElementAt(index);
						to--;
						System.out.println("ENTRAA");

					}				}
				catch(ArrayIndexOutOfBoundsException aioobe) {   //questa eccezione non e' presente nell'interfaccia di ListIterator
					aioobe.printStackTrace();
					return;   			
				}
				possibleState = false;
				nex = false;
				prev = false;
			}

			@Override
			public void set(Object o) {				
				if(o == null){
					throw new IllegalArgumentException();
				}
				if (possibleState == false) {
					throw new  java.lang.IllegalStateException();
				}
				try {
					if(nex) {
						oL.vector.setElementAt(o,--index);
					}
					else {
						oL.vector.setElementAt(o,index);					
					}				}
				catch(ArrayIndexOutOfBoundsException aioobe) {   //questa eccezione non e' presente nell'interfaccia di ListIterator
					aioobe.printStackTrace();  			
				}
				nex = false;
				prev = false;
			}
		}
		
		
		@Override
		public void add(int index, Object element) {
			
			if(element == null) {
				throw new NullPointerException();
			}
			if(index<0 || index+from>to) {
				throw new IndexOutOfBoundsException();
			}
			oL.vector.insertElementAt(element, index+from);
			to++;
		}
		
		@Override
		public boolean add(Object o) {					
			System.out.println("to " + to);
			add(to-from,o);
			return true;
		}
		
		@Override
		public boolean addAll(HCollection c) {					
			if(c.isEmpty()) {
				return false;	
			}
			Object[] arr = c.toArray();
			for(int i=0;i<c.size();i++) {
				add(arr[i]);		
			}
			return true;	
		}

		@Override
		public boolean addAll(int index, HCollection c) {
			if(index<0 || index+from>to) {
				throw new IndexOutOfBoundsException();
			}
			if(c.isEmpty()) {
				return false;	
			}
			Object[] arr = c.toArray();
			for(int i=0;i<c.size();i++) {
				add(index,arr[i]);
				index++;
			}
			return true;
		}
		
		@Override
		public void clear() {								
			while(from != to) {
				System.out.println("- "+ (to-1));
				remove(to-from-1);
			}
		}
		
		@Override
		public boolean contains(Object o) {				
			if(o == null) {
				throw new NullPointerException();
			}
			for(int i=0;i<size();i++) {
				if(get(i).equals(o)) {
					return true;
				}
			}
			return false;
		}
		
		@Override
		public boolean containsAll(HCollection c) {		
			if (c == null) {
				throw new NullPointerException();
			}
			Object[] arr = c.toArray();
			for(int i=0;i<arr.length;i++) {
				if(!contains(arr[i])) {
					return false;
				}
			}
			return true;
		}
		
		@Override
		public boolean equals(Object o) {				
			if(!(o instanceof ListAdapter)) {
				return false;
			}
			HList newList = (ListAdapter) o;
			if(size() != newList.size()) {
				return false;
			}
			int k=0;
			for(int i=0;i<size();i++) {
				if(!(get(i).equals(newList.get(k)))) {
					return false;
				}
				k++;
			}
			return true;
		}
		
		@Override
		public Object get(int index){					//ok
			if(index<0 || index+from>=to) {
				throw new IndexOutOfBoundsException();
			}
			return oL.vector.elementAt(index+from);
		}
		
		@Override
		public int hashCode() {							//ok                  
			int hashC = 1;
			HIterator i = iterator();
			while (i.hasNext()) {
			    Object obj = i.next();
			    System.out.println(obj);
			    hashC = 31*hashC + (obj==null ? 0 : obj.hashCode());
			}
			return hashC;
		}
		
		@Override
		public int indexOf(Object o) {					
			if(o == null) {
				throw new NullPointerException();
			}
			for(int i=0;i<size();i++) {
				if(get(i).equals(o)) {
					return i;
				}
			}
			return -1;
		}
		
		@Override
		public boolean isEmpty() {						
			return from == to;
		}
		
		@Override
		public HIterator iterator() { 					
			HIterator iter = new MyIterator();
			return iter;
		}
		
		@Override
		public int lastIndexOf(Object o) {				
			if(o == null) {
				throw new NullPointerException();
			}
			int ret = -1;
			for(int i=0;i<size();i++) {
				System.out.println(get(i));
				if(get(i).equals(o)) {
					ret=i;
				}
			}
			return ret;
		}
		
		@Override
		public HListIterator listIterator() {			
			HListIterator hListIterator = new MyListIterator();
			return hListIterator;
		}

		@Override
		public HListIterator listIterator(int index) {	
			if(index<0 || index+from>to) {
				throw new IndexOutOfBoundsException();
			}
			HListIterator hListIterator = new MyListIterator(index);
			return hListIterator;
		}
		
		@Override
		public Object remove(int index) {				
			if(index<0 || index+from>=to) {
				System.out.println("**" + index);
				throw new IndexOutOfBoundsException();
			}
			Object temp = get(index);
			oL.vector.removeElementAt(index+from);
			to--;
			return temp;
		}
		
		@Override
		public boolean remove(Object o) {				
			if (o == null) {
				throw new NullPointerException();
			}
			//remove non va out of bound perche'indexOf restituisce indice sempre valido
			//(non -1 perche' contains(o) deve aver restituito true)
			if(contains(o)) {//se c'e l'elemento, identifico l'indice e poi lo elimino
				int ind = indexOf(o);
				remove(ind);
				return true;
			}
			return false;
		}
		
		@Override
		public boolean removeAll(HCollection c) {		
			if (c == null) {
				throw new NullPointerException();
			}
			boolean ret = false;
			for(int i=0;i<size();i++) {
				if(c.contains(get(i))) { 
					remove(get(i));
					i--;
					ret = true;
				}
			}
			return ret;
		}

		@Override
		public boolean retainAll(HCollection c) {			
			
			if (c == null) {
				throw new NullPointerException();
			}
			boolean ret = false;
			for(int i=0;i<size();i++) {
				if(!c.contains(get(i))) { 
					remove(get(i));
					i--;
					ret = true;
				}
			}
			return ret;	
		}
		
		@Override
		public Object set(int index, Object element) {	
			if(element == null){
				throw new NullPointerException();
			}
			if(index<from || index+from>=to) {
				throw new IndexOutOfBoundsException();
			}
			Object temp = get(index);
			oL.vector.setElementAt(element,index+from);
			return temp;
		}
		
		@Override
		public int size() {								
			return to-from;
		}
		
		@Override
		public Object[] toArray() {						
			Object[] arr = new Object[size()];
			int k=0;
			for(int i=0;i<size();i++) {
				arr[k] = get(i);
				k++;
			}
			return arr;
		}
		
		@Override
		public Object[] toArray(Object[] a) {			
			if(a == null) {
				throw new NullPointerException();
			}
			if (a.length < size()) {
				a = new Object[size()];
			}
			int k=0;
			for(int i=0;i<size();i++) {
				a[k] = get(i);
				k++;
			}
			return a;
		}
		
		@Override
		public HList sublist(int fromIndex, int toIndex) {	
			if(fromIndex<from || toIndex>to || fromIndex>toIndex) {
				throw new IndexOutOfBoundsException();
			}
			return new Sublist(fromIndex,toIndex,oL);
		}	
	}	
}
