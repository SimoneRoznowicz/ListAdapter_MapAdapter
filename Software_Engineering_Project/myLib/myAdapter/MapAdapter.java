package myAdapter;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

import myAdapter.HMap.HEntry;


public class MapAdapter
implements HMap{

	private class EntryClass implements HEntry {
		
		Object key;
		Object value;
		
		public EntryClass(Object k, Object v) {
			key = k;
			value = v;
		}
	
		 public boolean equals(Object o) {
			 
			 if(!(o instanceof EntryClass)) {
				 return false;
			 }
			 EntryClass oo = (EntryClass) o;
			 return (getKey()==null ? oo.getKey()==null : getKey().equals(oo.getKey()))  &&
				     (getValue()==null ? oo.getValue()==null : getValue().equals(oo.getValue()));
		 }
		 
		 public Object getKey() {
			 return key;
		 }
		 
		 public Object getValue() {
			 return value;
		 }
		 
		 public int hashCode() {				
			 return  (getKey()==null ? 0 : getKey().hashCode()) ^ (getValue()==null ? 0 : getValue().hashCode());
		 }
		 
		 public Object setValue(Object val) {
			 if(val==null) {
				 throw new NullPointerException();
			 }
			 Object temp = value;
			 value = val;
			 return temp;
		 }
	}
	
	
	Hashtable ht;
	public MapAdapter() {
		ht = new Hashtable();
	}
	
	/**
	 * Removes all mappings from this map (optional operation).
	 * @throws UnsupportedOperationException - clear is not supported by this map
	 */
	@Override
	public void clear() {
		ht.clear();
	}

	/**
	 * Returns true if this map contains a mapping for the specified key. More formally, returns true if and only if this map contains at a mapping for a key k such that (key==null ? k==null : key.equals(k)). (There can be at most one such mapping.)
	 * @param key - key whose presence in this map is to be tested.
	 * @return true if this map contains a mapping for the specified key.
	 * @throws NullPointerException - if the key is null.
	 */
	@Override
	public boolean containsKey(Object key) {
		if(key == null) {
			throw new NullPointerException();
		}
		return  ht.containsKey(key);
	}

	/**
	 * Returns true if this map maps one or more keys to the specified value. More formally, returns true if and only if this map contains at least one mapping to a value v such that (value==null ? v==null : value.equals(v)). This operation will probably require time linear in the map size for most implementations of the Map interface.
	 * @param value - value whose presence in this map is to be tested.
	 * @return true if this map maps one or more keys to the specified value.
	 * @throws NullPointerException - if the value is null 
	 */
	@Override
	public boolean containsValue(Object value) {
		if(value == null) {
			throw new NullPointerException();
		}
		return ht.contains(value);
	}
	
	/**
	 * Returns a set view of the mappings contained in this map. Each element in the returned set is a Map.Entry. The set is backed by the map, so changes to the map are reflected in the set, and vice-versa. If the map is modified while an iteration over the set is in progress, the results of the iteration are undefined. The set supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Set.remove, removeAll, retainAll and clear operations. It does not support the add or addAll operations.
	 * @return a set view of the mappings contained in this map.
	 */
	@Override
	public HSet entrySet() {				
		return new MyEntrySet();
	}

	/**
	 * Returns the value to which this map maps the specified key. Returns null if the map contains no mapping for this key. A return value of null does not necessarily indicate that the map contains no mapping for the key; it's also possible that the map explicitly maps the key to null. The containsKey operation may be used to distinguish these two cases.
	 * More formally, if this map contains a mapping from a key k to a value v such that (key==null ? k==null : key.equals(k)), then this method returns v; otherwise it returns null. (There can be at most one such mapping.)
	 * @param key - key whose associated value is to be returned.
	 * @return the value to which this map maps the specified key, or null if the map contains no mapping for this key.
	 * @throws NullPointerException - key is null.
	 */
	@Override
	public Object get(Object key) {
		if(key == null) {
			throw new NullPointerException();
		}
		return ht.get(key);
	}
	
	/**
	 * Returns the hash code value for this map. The hash code of a map is defined to be the sum of the hashCodes of each entry in the map's entrySet view. This ensures that t1.equals(t2) implies that t1.hashCode()==t2.hashCode() for any two maps t1 and t2, as required by the general contract of Object.hashCode.
	 * @return the hash code value for this map.
	 */
	@Override
	public int hashCode() {
		int hashC = 0;
		HSet keyS = keySet();
		Object[] arr = keyS.toArray();
		for(int i=0;i<size();i++) {
			hashC += (new EntryClass(arr[i],get(arr[i]))).hashCode();
		}
		return hashC;
	}
	
	/**
	 * Compares the specified object with this map for equality. Returns true if the given object is also a map and the two Maps represent the same mappings. More formally, two maps t1 and t2 represent the same mappings if t1.entrySet().equals(t2.entrySet()). This ensures that the equals method works properly across different implementations of the Map interface.
	 * @param o - object to be compared for equality with this map.
	 * @return true if the specified object is equal to this map.
	 */
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof MapAdapter)) {
			return false;
		}
		MapAdapter mpNew = (MapAdapter) o;
		if(mpNew.size() != size()) {
			return false;
		}
		HSet keyS = keySet();						
		Object[] arr = keyS.toArray();				//creo un array di k per il mapAdapter this
		HSet keyS1 = mpNew.keySet();
		Object[] arr1 = keyS1.toArray();			//creo un array di k per il mapAdapter passato
		
		for(int i=0;i<size();i++) {
			for(int j=0;j<size();j++) {
				if(arr[i].equals(arr1[j]) && get(arr[i]).equals(mpNew.get(arr1[j]))){
					break;
				}
				//se arrivo a size()-1 e ancora non ho trovato l'elemento che cerco, allora non c'e' e le mappe non sono uguali 
				if(j==(size()-1)) {
					return false;
				}
			}
			//non dovrebbe mai arrivare qui se le mappe sono uguali dato che continue interrompe il ciclo
		}
		//se esco dal ciclo esterno, ho verificato che tutti gli elementi sono uguali: le due mappe sono uguali
		return true;
	}
	
	/**
	 * Returns true if this map contains no key-value mappings.
	 * @return true if this map contains no key-value mappings.
	 */
	@Override
	public boolean isEmpty() {
		return ht.isEmpty();
	}

	/**
	 * Returns a set view of the keys contained in this map. The set is backed by the map, so changes to the map are reflected in the set, and vice-versa. If the map is modified while an iteration over the set is in progress, the results of the iteration are undefined. The set supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Set.remove, removeAll retainAll, and clear operations. It does not support the add or addAll operations.
	 * @return a set view of the keys contained in this map.
	 */
	@Override
	public HSet keySet() {												
		return new MyKeySet();	
	}

	/**
	 * Associates the specified value with the specified key in this map (optional operation). If the map previously contained a mapping for this key, the old value is replaced by the specified value. (A map m is said to contain a mapping for a key k if and only if m.containsKey(k) would return true.))
	 * @param key - key with which the specified value is to be associated.
	 * value - value to be associated with the specified key.
	 * @return previous value associated with specified key, or null if there was no mapping for key. A null return can also indicate that the map previously associated null with the specified key, if the implementation supports null values.
	 * @throws NullPointerException - this map does not permit null keys or values, and the specified key or value is null.
	 */
	@Override
	public Object put(Object key, Object value) {
		//lanciare errori?
		if(key == null) {
			throw new NullPointerException();
		}
		if(value == null) {
			throw new NullPointerException();
		}
		Object temp = get(key);
		ht.put(key, value);
		return temp;
	}

	/**
	 * Copies all of the mappings from the specified map to this map (optional operation). The effect of this call is equivalent to that of calling put(k, v) on this map once for each mapping from key k to value v in the specified map. The behavior of this operation is unspecified if the specified map is modified while the operation is in progress.
	 * @param t - Mappings to be stored in this map.
	 * @throws NullPointerException - the specified map is null, or if the specified map contains null keys or values.
	 */
	@Override
	public void putAll(HMap t) {						
		if(t == null) {
			throw new NullPointerException();
		}
		HSet newKeySet = t.keySet();
		HIterator iter = newKeySet.iterator();
		while(iter.hasNext()) {
			Object kk = iter.next(); 
			ht.put(kk,t.get(kk));
		}
	}

	/**
	 * Removes the mapping for this key from this map if it is present (optional operation). More formally, if this map contains a mapping from key k to value v such that (key==null ? k==null : key.equals(k)), that mapping is removed. (The map can contain at most one such mapping.)
	 * Returns the value to which the map previously associated the key, or null if the map contained no mapping for this key. (A null return can also indicate that the map previously associated null with the specified key if the implementation supports null values.) The map will not contain a mapping for the specified key once the call returns.
	 * @param key - key whose mapping is to be removed from the map.
	 * @return previous value associated with specified key, or null if there was no mapping for key.
	 * @throws NullPointerException - if the key is null and this map does not not permit null keys (optional).
	 */
	@Override
	public Object remove(Object key) {
		if(key == null) {
			throw new NullPointerException();
		}
		Object temp = get(key);
		ht.remove(key);
		return temp;
	}

	/**
	 * Returns the number of key-value mappings in this map. If the map contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
	 * @return the number of key-value mappings in this map.
	 */
	@Override
	public int size() {
		return ht.size();
	}

	/**
	 * Returns a collection view of the values contained in this map. The collection is backed by the map, so changes to the map are reflected in the collection, and vice-versa. If the map is modified while an iteration over the collection is in progress, the results of the iteration are undefined. The collection supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Collection.remove, removeAll, retainAll and clear operations. It does not support the add or addAll operations.
	 * @return a collection view of the values contained in this map.
	 */
	@Override
	public HCollection values() {						
		return new MyValueCollection();
	}
	
	
	private class MyEntrySet
	extends MyKeySet
	implements HSet {
		
		private class MyIterator
		implements HIterator {
			
			boolean possibleState;
			Enumeration ek;
			public MyIterator() {
				possibleState = false;
				ek = ht.keys();
			}
			
			@Override
			public boolean hasNext() {				//ok
				if(ek.hasMoreElements()) {
					return true;
				}
				return false;	
			}
			
			@Override
			public Object next() {					//ok
				possibleState = true;
				if (hasNext() == false) {
					throw new NoSuchElementException();
				}
				//ArrayIndexOutOfBoundsException from elementAt(index) cannot
				//occur in this situation, so I simply ignore it. I'm not going to write unuseful code for try and catch
				Object a = ek.nextElement();
				return new EntryClass(a,get(a));
			}

			@Override
			public void remove() {
				if (possibleState == false) {
					throw new IllegalStateException();
				}
				try {
					//CONTROLLA CHE VADA BENE FARE COSI, PERCHE HO IL DUBBIO RIMUOVERE TIPO L'ELEMENTO DOPO
					ht.remove(ek.nextElement());
				}
				catch(ArrayIndexOutOfBoundsException aioobe) {  //this exception is not present in the iterator interface
					aioobe.printStackTrace();
					return;   			
				}
				possibleState = false;
			}
		}

		@Override
		public boolean contains(Object o) {
			if(o == null) {
				throw new NullPointerException();
			}
			if(!(o instanceof EntryClass)) {
				return false;
			}
			EntryClass oo = (EntryClass) o;
			HIterator iter = iterator();
			while(iter.hasNext()) {
				EntryClass x = (EntryClass)iter.next();
				if(x.equals(oo)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public HIterator iterator() {
			HIterator iter = new MyIterator();
			return iter;
		}
		
		@Override
		public boolean remove(Object o) {//passa entry
			if(!(o instanceof EntryClass)) {
				return false;
			}			
			if(contains(o)) {
				EntryClass oo = (EntryClass) o;
				ht.remove(oo.getKey());	//rimuovo chiave e valore associato
				return true;
			}
			return false;
		}
	}
	
	
	
	//PER METODO VALUES()
	private class MyValueCollection 
	extends MyKeySet
	implements HCollection{
		private class MyIterator
		implements HIterator {
			
			boolean possibleState;
			Enumeration ek;
			
			public MyIterator() {
				possibleState = false;
				ek = ht.keys();
			}
			
			@Override
			public boolean hasNext() {				
				if(ek.hasMoreElements()) {
					return true;
				}
				return false;	
			}
			
			@Override
			public Object next() {					
				possibleState = true;
				if (hasNext() == false) {
					throw new NoSuchElementException();
				}
				Object a=ek.nextElement();
				return get(a);
			}

			@Override
			public void remove() {
				if (possibleState == false) {
					throw new IllegalStateException();
				}
				try {
					ht.remove(ek.nextElement());
				}
				catch(ArrayIndexOutOfBoundsException aioobe) {  
					aioobe.printStackTrace();
					return;   			
				}
				possibleState = false;
			}
		}

		@Override//**modificato
		public boolean contains(Object o) {
			if(o == null) {
				throw new NullPointerException();
			}
			return ht.contains(o);
		}

		@Override
		public HIterator iterator() {
			HIterator iter = new MyIterator();
			return iter;
		}
		
		@Override
		public boolean remove(Object o) {//passo il valore, elimino a k e v associati alla prima occorrenza 
			HSet kSet = keySet();
			Object[] kArr = kSet.toArray();
			for(int i=0;i<kArr.length;i++) {
				if(get(kArr[i]).equals(o)) {
					kSet.remove(kArr[i]);
					return true;
				}
			}
			return false;
		}
		
		@Override
		public boolean equals(Object o) {
			//ATTENZIONE: riscritto il codice da keySet cambiando solo instanceOf: cosi' se confronto un oggetto di tipo MyValueCollection con uno di tipo MyKeySet che hanno medesimi elementi, ritorna false (non ho testato questo codice in quanto e' il medesimo presente in MyKeySet)
			//L'algoritmo funziona anche per la classe MyEntrySet ma il problema non sussiste in quanto i singoli oggetti di tipo MyEntrySet non sono uguali a MyKeySet
			if(!(o instanceof MyValueCollection)) {
				return false;
			}
			HSet newSet = (HSet) o;
			if(size() != newSet.size()) {
				return false;
			}
			//nessun elemento nella lista puo' essere nullo
			HIterator iter = iterator();
			while(iter.hasNext()) {
				if(!newSet.contains(iter.next())) {
					return false;
				}
			}
			return true;
		}
	}
	
	
	//PER METODO KEYSET()
	private class MyKeySet 
	implements HSet{

		private class MyIterator
		implements HIterator {
			
			boolean possibleState;
			Enumeration ek;
			public MyIterator() {
				possibleState = false;
				ek = ht.keys();
			}
			
			@Override
			public boolean hasNext() {				
				if(ek.hasMoreElements()) {
					return true;
				}
				return false;	
			}
			
			@Override
			public Object next() {					
				possibleState = true;
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				return ek.nextElement();
			}

			@Override
			public void remove() {
				if (possibleState == false) {
					throw new IllegalStateException();
				}
				try {
					ht.remove(ek.nextElement());
				}
				catch(ArrayIndexOutOfBoundsException aioobe) {  
					aioobe.printStackTrace();
					return;   			
				}
				possibleState = false;
			}
		}
		
		
		@Override
		public boolean add(Object o) {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public boolean addAll(HCollection c) {
			throw new java.lang.UnsupportedOperationException();
		}

		@Override
		public void clear() {
			ht.clear();
		}

		@Override
		public boolean contains(Object o) {
			if(o == null) {
				throw new NullPointerException();
			}
			return ht.containsKey(o);
		}

		@Override
		public boolean containsAll(HCollection c) {
			if (c == null) {
				throw new NullPointerException();
			}
			Object[] arr = c.toArray();
			for(int i=0;i<c.size();i++) {
				if(!contains(arr[i])) {
					return false;
				}
			}
			return true;
		}
		
		@Override
		public boolean equals(Object o) {
			if(!(o instanceof MyKeySet)) {
				System.out.println("!!!!!!!!!!!!!!!!!");
				return false;
			}
			HSet newSet = (HSet) o;
			if(size() != newSet.size()) {
				return false;
			}
			//no element in a list can be null
			HIterator iter = iterator();
			while(iter.hasNext()) {
				if(!newSet.contains(iter.next())) {
					return false;
				}
			}
			return true;
		}
		
		@Override
		public int hashCode() {
			int hashC = 1;
			HIterator i = iterator();
			while (i.hasNext()) {
			    Object obj = i.next();
			    hashC = 31*hashC + (obj==null ? 0 : obj.hashCode());
			}
			return hashC;
		}
		 
		@Override
		public boolean isEmpty() {
			return ht.isEmpty();
		}

		@Override
		public HIterator iterator() {
			HIterator iter = new MyIterator();
			return iter;
		}

		@Override
		public boolean remove(Object o) {//passo la chiave
			if(contains(o)) {
				ht.remove(o);	//rimuovo chiave e valore associato
				return true;
			}
			return false;
		}

		@Override
		public boolean removeAll(HCollection c) {
			
			if (c == null) {
				throw new NullPointerException();
			}
			boolean ret=false;
			Object[] arr = toArray();
			System.out.println("%%% arr[index] " + arr[2]);				
			for(int i=0;i<arr.length;i++) {
				System.out.println("- index " + i);
				System.out.println("- arr[index] " + arr[i]);				
				if(c.contains(arr[i])) { 			
					System.out.println("- arr[index] ** " + arr[i]);									
					remove(arr[i]);
					ret=true;
				}
			}
			return ret;
		}

		@Override
		public boolean retainAll(HCollection c) {
			if (c == null) {
				throw new NullPointerException();
			}
			boolean ret=false;
			Object[] arr = toArray();
			for(int i=0;i<arr.length;i++) {
				if(!c.contains(arr[i])) {
					remove(arr[i]);
					
					ret = true;
				}
			}
			return ret;
		}
		
		@Override
		public int size() {
			return ht.size();
		}

		@Override
		public Object[] toArray() {
			Object[] arr = new Object[size()];
			HIterator iter = iterator();
			int i=0;
			while(iter.hasNext()) {
				arr[i] = iter.next();
				i++;
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
			HIterator iter = iterator();
			int i=0;
			while(iter.hasNext()) {
				a[i] = iter.next();
				i++;
			}
			return a;
		}
	}
}
