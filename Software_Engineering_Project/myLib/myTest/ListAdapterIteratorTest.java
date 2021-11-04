package myTest;
import myAdapter.*;

import myAdapter.HIterator;
import myAdapter.ListAdapter;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListAdapterIteratorTest {

	@Test
	public void IteratorTest() {
		ListAdapter l = new ListAdapter();
		l.add("a");
		l.add("b");		
		l.add("c");
		HIterator iter = l.iterator();
		while(iter.hasNext()) {						
			if(iter.next()=="b"){
				iter.remove();					
			}	
		}
		assertEquals(2,l.size());

		assertEquals(true,l.contains("a"));
		assertEquals(false,l.contains("b"));
		assertEquals(true,l.contains("c"));
		try {
			iter.remove(); //chiamo la funzione due volte di fila 
		}
		catch(IllegalStateException e) {
			assertEquals(true,true);
		}
	}

}
