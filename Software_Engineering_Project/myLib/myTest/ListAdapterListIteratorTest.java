package myTest;
import myAdapter.*;

import myAdapter.HListIterator;
import myAdapter.ListAdapter;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListAdapterListIteratorTest {

	@Test
	public void ListIteratorRemoveTest() {
		ListAdapter l = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		HListIterator iter = l.listIterator();
		while(iter.hasNext()) {						
			if(iter.next().equals("a")){
				if(iter.hasNext()) {
					iter.remove();					
				}
			}	
		}
		assertEquals(true,l.size()==2);
		assertEquals(true,l.contains("b"));
		assertEquals(false,l.contains("a"));
		assertEquals(true,l.contains("c"));
		try {
			iter.remove(); 							//chiamo la funzione due volte di fila 
		}
		catch(IllegalStateException e) {
			System.out.println("entrato");
			assertEquals(true,true);
		}
	}
	
	@Test
	public void ListIteratorPreviousTest() {
		ListAdapter l = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		HListIterator iter = l.listIterator(2);
		while(iter.hasPrevious()) {						
			Object x = iter.previous();
			if(x.equals("b")){
				iter.remove();					
			}	
		}
		assertEquals(2,l.size());
		assertEquals(false,l.contains("b"));
	}
	
	@Test
	public void ListIteratorNextPreviousTest() {
		ListAdapter l = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		HListIterator iter = l.listIterator();
		assertEquals(false,iter.hasPrevious()); 
		assertEquals("a",iter.next());
		assertEquals("a",iter.previous());
	}
	
	@Test
	public void ListIteratorSetTest() {
		ListAdapter l = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
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
	}
}
