package myTest;
import myAdapter.*;
import myAdapter.ListAdapter;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListAdapterRemoveAllTest {

	@Test
	public void RemoveAllTest() {
		ListAdapter l = new ListAdapter();
		ListAdapter l2 = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("d");
		System.out.println(l.size());
		l2.add("c");			
		l2.add("a");
		l2.add("b");
		l2.add("x");
		l.removeAll(l2);
		assertEquals(false,l.contains("a"));
		assertEquals(false,l.contains("b"));
		System.out.println(l.size());
		assertEquals(true,l.size()==1);
	}

}
