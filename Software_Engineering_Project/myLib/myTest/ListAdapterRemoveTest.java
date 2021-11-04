package myTest;
import myAdapter.*;
import myAdapter.ListAdapter;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListAdapterRemoveTest {

	@Test
	public void RemoveTest() {
		ListAdapter l = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("d");
		assertEquals(true,l.remove("a"));
		assertEquals(false,l.remove("x"));
		l.remove("d");
		l.remove("b");
		l.remove("c");
		assertEquals(true,l.size()==0);
	}

	@Test
	public void Remove2Test() {
		ListAdapter l = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		l.add("d");
		assertEquals("d",l.remove(3));
		l.remove(0);
		l.remove(1);
		l.remove(0);
		assertEquals(true,l.size()==0);
	}
}
