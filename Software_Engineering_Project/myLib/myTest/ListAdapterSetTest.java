package myTest;
import myAdapter.*;
import myAdapter.ListAdapter;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListAdapterSetTest {

	@Test
	public void SetTest() {
		ListAdapter l = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		l.set(1,"xy");			//sostituisce b con xy
		assertEquals("xy",l.get(1));
		assertEquals(true,l.size()==3);	//size() rimane invariato
		assertEquals(false,l.contains("b"));
		try {
			l.set(3,"yy");
		}
		catch(IndexOutOfBoundsException e) {
			assertEquals(true,true);
		}
	}

}
