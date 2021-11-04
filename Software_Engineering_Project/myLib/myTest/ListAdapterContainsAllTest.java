package myTest;
import myAdapter.*;

import myAdapter.ListAdapter;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListAdapterContainsAllTest {

	@Test
	public void ContainsAllTest() {
		ListAdapter l = new ListAdapter();
		ListAdapter l2 = new ListAdapter();
		l.add(0,"a");
		l.add(0,"b");			//b--->d--->a--->c
		l.add(2,"c");
		l.add(1,"d");
		l2.add("b");			//x--->b--->d--->a--->c
		l2.add("d");
		assertEquals(true,l.contains("d"));
		assertEquals(true,l.containsAll(l2));
		l2.add("x");
		assertEquals(false,l.containsAll(l2)); //non tutti gli elementi sono contenuti
	}
}
