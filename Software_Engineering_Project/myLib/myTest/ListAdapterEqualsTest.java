package myTest;
import myAdapter.*;

import myAdapter.ListAdapter;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListAdapterEqualsTest {

	@Test
	public void EqualsTest() {
		ListAdapter l = new ListAdapter();
		ListAdapter l2 = new ListAdapter();
		ListAdapter l3 = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		l2.add("c");			
		l2.add("b");
		l2.add("a");
		l3.add("a");		
		l3.add("b");
		l3.add("c");
		assertEquals(false,l.equals(l2)); //infatti l'ordine non e' uguale
		assertEquals(true,l.equals(l3));
		l3.add("x");
		assertEquals(false,l.equals(l3));
	}

}
