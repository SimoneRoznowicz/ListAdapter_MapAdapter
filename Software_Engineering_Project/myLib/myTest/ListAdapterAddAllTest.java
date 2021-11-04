package myTest;
import myAdapter.*;
import myAdapter.ListAdapter;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListAdapterAddAllTest {

	@Test
	public void AddAllTest() {
		ListAdapter l = new ListAdapter();
		ListAdapter l2 = new ListAdapter();
		l.add(0,"a");
		l.add(0,"b");			//b--->d--->a--->c
		l.add(2,"c");
		l.add(1,"d");
		l2.add("x");			//x--->b--->d--->a--->c
		l2.addAll(l);
		assertEquals("x",l2.get(0)); 
		assertEquals("b",l2.get(1));
		assertEquals("c",l2.get(4));
	}
	
	@Test
	public void AddAll2Test() {
		ListAdapter l = new ListAdapter();
		ListAdapter l2 = new ListAdapter();
		l.add(0,"a");
		l.add(0,"b");			//b--->d--->a--->c
		l.add(2,"c");
		l.add(1,"d");
		l2.add("x");			
		l2.add("y");
		l2.add("z");		
		l2.addAll(1,l);			//x--->b--->d--->a--->c--->y--->z
		assertEquals("x",l2.get(0)); 
		assertEquals("b",l2.get(1));
		assertEquals("c",l2.get(4));
		assertEquals("y",l2.get(5));
	}
}
