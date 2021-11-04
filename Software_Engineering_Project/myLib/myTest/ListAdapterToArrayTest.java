package myTest;
import myAdapter.*;

import myAdapter.ListAdapter;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListAdapterToArrayTest {

	@Test
	public void ToArrayTest() {
		ListAdapter l = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		Object[] arr = l.toArray();
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
		assertEquals(3,arr.length);
	}
	
	@Test
	public void ToArray2Test() {
		ListAdapter l = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		Object[] arr = new Object[10]; 
		arr = l.toArray(arr);
		for(int i=0;i<3;i++) {
			System.out.println(arr[i]);
		}
		assertEquals(10,arr.length);
	}

}
