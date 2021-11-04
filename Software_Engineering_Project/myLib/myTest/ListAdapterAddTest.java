package myTest;
import myAdapter.*;
import myAdapter.ListAdapter;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListAdapterAddTest {

	@Test
	public void AddTest() {
		ListAdapter l = new ListAdapter();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add("g");
		assertEquals(1,l.get(0));
		assertEquals(2,l.get(1));
		assertEquals("g",l.get(3));
		try {
			assertEquals(null,l.add(null)); 
		}
		catch(NullPointerException e) {
			assertEquals(true,true);
		}
	}
	
	@Test
	public void Add1Test() {
		ListAdapter l = new ListAdapter();
		l.add(0,"a");
		l.add(0,"b");			//b--->d--->a--->c
		l.add(2,"c");
		l.add(1,"d");
		assertEquals("b",l.get(0)); 
		assertEquals("d",l.get(1));
		assertEquals("c",l.get(3));
		try {
			assertEquals(null,l.add(null)); 
		}
		catch(NullPointerException e) {
			assertEquals(true,true);
		}
	}
	
}
