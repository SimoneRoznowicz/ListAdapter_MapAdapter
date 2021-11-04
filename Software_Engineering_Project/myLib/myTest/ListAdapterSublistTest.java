package myTest;
import myAdapter.*;


import static org.junit.Assert.*;

import org.junit.Test;

public class ListAdapterSublistTest {
	@Test
	public void SublistTest() {
		ListAdapter l = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("d");
		l.add("e");
		HList sl = l.sublist(1, 4);	
		HList sl2 = l.sublist(1, 1);
		try {
			HList sl3 = l.sublist(4, 1);	
		}
		catch(IndexOutOfBoundsException e) {
			assertEquals(true,true);
		}
		sl2.clear();
		assertEquals(true,sl2.size()==0);
		assertEquals(true,l.size()==5);				//le operazioni effettuate non modificano la lista l
		sl.clear();
		assertEquals(true,sl.size()==0);
		assertEquals(true,l.size()==2);
	}
	@Test
	public void SublistAddTest() {
		ListAdapter l = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("d");
		l.add("e");
		HList sl = l.sublist(1, 4);
		assertEquals(3,sl.size());
		sl.add(3,"xx");
		sl.add("xy");		
		System.out.println(sl.get(0) + " " + sl.get(1) + " " + sl.get(2) + " " + sl.get(3) + " " + sl.get(4) + " ");
		assertEquals("xx",sl.get(3));
		assertEquals(5,sl.size());
	}
	
	@Test
	public void SublistAddAllTest() {
		ListAdapter l = new ListAdapter();
		ListAdapter l2 = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("d");
		l.add("e");
		HList sl = l.sublist(1, 4);	
		assertEquals(false,sl.addAll(l2));		//l2 vuota, ritorna false
		l2.add("aa");
		l2.add("bb");		
		l2.add("cc");
		sl.addAll(l2);
		assertEquals(true,sl.size() == 6);		//dimensione e' la somma delle size() l2 e sl
		assertEquals("aa",sl.get(3));
		for(int i=0;i<sl.size();i++) {
			System.out.println(sl.get(i));
		}
	}
	
	@Test
	public void SublistAddAll2Test() {
		ListAdapter l = new ListAdapter();
		ListAdapter l2 = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("d");
		l.add("e");
		HList sl = l.sublist(1, 4);	
		assertEquals(false,sl.addAll(l2));		
		l2.add("aa");
		l2.add("bb");		
		l2.add("cc");
		sl.addAll(1,l2);
		assertEquals(true,sl.size() == 6);		//dimensione e' la somma delle size() l2 e sl
		assertEquals("cc",sl.get(3));
		for(int i=0;i<sl.size();i++) {
			System.out.println(sl.get(i));
		}
	}
	
	@Test
	public void SublistClearTest() {
		ListAdapter l = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("d");
		l.add("e");
		HList sl = l.sublist(1, 4);	
		sl.add("x");
		sl.clear();
		assertEquals(true,sl.size()==0);
		assertEquals(true,l.size()==2);
		assertEquals(true,l.contains("a"));
	}
	
	@Test
	public void SublistContainsAllTest() {
		ListAdapter l = new ListAdapter();
		ListAdapter l2 = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("d");
		l.add("e");
		l2.add("d");			//sublist ha b c d
		l2.add("b");		
		l2.add("c");
		HList sl = l.sublist(1, 4);	
		assertEquals(true,sl.contains("c"));   //contiene c
		l2.add("b");
 		l2.add("x");
		assertEquals(false,sl.containsAll(l2));
		try {
			assertEquals(true,sl.containsAll(null));
		}
		catch(NullPointerException e) {			//errore se passo null
			assertEquals(true,true);
		}
	}
	
	@Test
	public void SublistEqualsTest() {
		ListAdapter l = new ListAdapter();
		ListAdapter l2 = new ListAdapter();
		ListAdapter l3 = new ListAdapter();
		ListAdapter l4 = new ListAdapter();

		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("d");
		l.add("e");
		
		l2.add("d");			//ordine non uguale
		l2.add("c");		
		l2.add("b");
		
		l3.add("b");			//ordine uguale
		l3.add("c");
		l3.add("d");		
		
		l4.add("b");			//presenta elemento x
		l4.add("c");
		l4.add("x");	
		for(int i=0;i<l3.size();i++) {
			System.out.println(l3.get(i));
		}
		HList sl = l.sublist(1, 4);	
		assertEquals(false,sl.equals(l2)); //sl e l2 con uguali elementi ma ordine diverso
		assertEquals(true,sl.equals(l3));  //tutti gli elementi e ordine uguale
		assertEquals(false,sl.equals(l4));
	}
	
	@Test
	public void SublistHashCodeTest() {
		ListAdapter l = new ListAdapter();
		ListAdapter l2 = new ListAdapter();
		ListAdapter l3 = new ListAdapter();
		ListAdapter l4 = new ListAdapter();
	
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("d");
		l.add("e");
		
		l2.add("d");			//ordine non uguale
		l2.add("c");		
		l2.add("b");
		
		l3.add("b");			//ordine uguale
		l3.add("c");
		l3.add("d");		
		
		l4.add("b");			//presenta elemento x
		l4.add("c");
		l4.add("x");	
		for(int i=0;i<l4.size();i++) {
		//	System.out.println(l4.get(i));
		}
		HList sl = l.sublist(1, 4);
		assertEquals(false,sl.hashCode() == l2.hashCode()); //sl e l2 con uguali elementi ma ordine diverso
		
		assertEquals(l3.hashCode(),sl.hashCode());  //elementi e ordine uguale
		assertEquals(false,sl.hashCode() == l4.hashCode());
	}
	
	@Test
	public void IndexOfTest() {
		ListAdapter l = new ListAdapter();
		ListAdapter l2 = new ListAdapter();
		l.add("b");
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("a");
		l.add("d");
		HList sl = l.sublist(1, 5);	
		assertEquals(0,sl.indexOf("a"));	
	}
	
	@Test
	public void SublistLastIndexOfTest() {
		ListAdapter l = new ListAdapter();
		l.add("b");
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("a");
		l.add("d");
		HList sl = l.sublist(1, 5);	
		assertEquals(3,sl.lastIndexOf("a"));	
	}
	
	@Test
	public void SublistRemoveTest() {
		ListAdapter l = new ListAdapter();
		l.add("b");
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("a");
		l.add("d");
		HList sl = l.sublist(1, 6);	
		assertEquals("a",sl.remove(0));	
		sl.remove("b");
		sl.remove("a");
		assertEquals(2,sl.size());//testato i due tipi di remove
		assertEquals(false,sl.contains("b"));		
	}
	
	@Test
	public void SublistRemoveAllTest() {
		ListAdapter l = new ListAdapter();
		ListAdapter l2 = new ListAdapter();
		l.add("b");
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("a");
		l.add("d");
		
		l2.add("a");			
		l2.add("c");		
		l2.add("x");
		HList sl = l.sublist(1, 6);	
		sl.removeAll(l2);
		assertEquals(2,sl.size());
		assertEquals(true,sl.contains("d"));	
	}
	
	@Test
	public void SublistRetainAllTest() {
		ListAdapter l = new ListAdapter();
		ListAdapter l2 = new ListAdapter();
		l.add("b");
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("a");
		l.add("d");
		
		l2.add("d");			
		l2.add("c");
		l2.add("x");
		
		HList sl = l.sublist(1, 6);	
		sl.retainAll(l2);
		System.out.println(sl.size());
		assertEquals(2,sl.size());
		assertEquals(true,sl.contains("d"));	
	}
	
	
	@Test
	public void SublistSetTest() {
		ListAdapter l = new ListAdapter();
		l.add("b");
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("a");
		l.add("d");
		HList sl = l.sublist(1, 6);	
		sl.set(1, "x");
		sl.set(4, "y");
		assertEquals(true,sl.contains("x"));
		assertEquals(true,sl.size()==5);
	}
	
	
	@Test
	public void SublistToArrayTest() {
		ListAdapter l = new ListAdapter();
		l.add("b");
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("a");
		l.add("d");
		HList sl = l.sublist(1, 6);	
		Object[] arr = sl.toArray();
		assertEquals(true,arr.length==5);
		assertEquals(true,arr[0]=="a");
		assertEquals(true,arr[2]=="c");
		assertEquals(true,arr[4]=="d");
		//toArray(Object[] o)
		Object[] arrNew = new Object[10];
		arrNew = sl.toArray(arrNew);
		assertEquals(true,arrNew.length==10);
		assertEquals(true,arr[0]=="a");
		assertEquals(true,arr[2]=="c");
		assertEquals(true,arr[4]=="d");
	}
	
	@Test
	public void Sublist2Test() {
		ListAdapter l = new ListAdapter();
		l.add("b");
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("a");
		l.add("d");
		HList sl = l.sublist(1, 6);	
		HList ssl = sl.sublist(1, 4);
		assertEquals(3,ssl.size());
		ssl.add("x");
		ssl.remove("c");
		ssl.remove("a");
		assertEquals(2,ssl.size());
		assertEquals("b",ssl.get(0));
		ssl.clear();
		assertEquals(0,ssl.size());
	}
	
	
	@Test
	public void SublistIteratorTest() {
		ListAdapter l = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("d");
		l.add("e");
		HList sl = l.sublist(1, 4);	
		assertEquals(3,sl.size());
		assertEquals(false,sl.contains("e"));
		HIterator iter = sl.iterator();
		while(iter.hasNext()) {						
			if(iter.next()=="d"){
				iter.remove();				
			}
		}
		assertEquals(2,sl.size());
		assertEquals(false,sl.contains("d"));
		assertEquals(true,sl.contains("c"));
		assertEquals(true,sl.contains("b")); 		

		try {
			iter.remove(); //chiamo la funzione due volte di fila 
		}
		catch(IllegalStateException e) {
			assertEquals(true,true);
		}
	}
	
	@Test
	public void SublistListIteratorPreviousTest() {
		ListAdapter l = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("d");
		l.add("e");
		HList sl = l.sublist(2, 5);	
		HListIterator iter = sl.listIterator(3);
		while(iter.hasPrevious()) {	
			if(iter.previous().equals("e")){
				iter.remove();					
			}
		}
		assertEquals(2,sl.size());
		assertEquals(false,sl.contains("e"));
	}
	
	@Test
	public void SublistListIteratorNextPreviousTest() {
		ListAdapter l = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("d");
		l.add("e");
		HList sl = l.sublist(1, 4);	
		HListIterator iter = l.listIterator();
		assertEquals("a",iter.next()); 
		assertEquals("a",iter.previous()); 
	}
	
	
	@Test
	public void SublistListIteratorSetTest() {
		ListAdapter l = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("d");
		l.add("e");
		HList sl = l.sublist(1, 4);	
		HListIterator iter = l.listIterator();
		try {
			iter.set("x");
		}
		catch(IllegalStateException e) {
			assertEquals(true,true);
		}
		System.out.println(iter.next());
		iter.set("x");
		assertEquals("x",iter.next());
		assertEquals("x",iter.previous());
	}
}
