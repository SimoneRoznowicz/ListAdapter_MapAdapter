package myTest;
import myAdapter.*;
import myAdapter.HCollection;
import myAdapter.HIterator;
import myAdapter.MapAdapter;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapAdapterValuesTest {
	@Test
	public void ContainsTest() {
		MapAdapter mp = new MapAdapter();
		HCollection v = mp.values();
		mp.put("z",1);
		mp.put("a", "otto");
		mp.put("b", 55);
		assertEquals(true,v.contains(1));
		assertEquals(true,v.contains(55));
		v.clear();
		assertEquals(false,v.contains(1));
		assertEquals(false,v.contains(55));
	}
	
	@Test
	public void RemoveTest() {
		MapAdapter mp = new MapAdapter();
		MapAdapter mp2 = new MapAdapter();
		mp.put("z",1);
		mp.put("a", "otto");
		mp.put("b", 55);
		mp2.put("a", "otto");
		mp2.put("b", 55);
		HCollection v = mp.values();
		v.remove(55);
		assertEquals(2,v.size());	
		assertEquals(2,mp.size());		
		assertEquals(false,v.contains(55));				//verifico di aver rimosso l'elemento 55
		assertEquals(false,mp.containsValue(55));		//verifico che l'elemento tolto dalla MyValueCollection sia stato rimosso anche da mp
	}
	
	@Test		//questo test e' poco utile in quanto RetainAll() e' un metodo che NON viene reimplementato in MyCollectionValues e quindi basta il test eseguito per RetainAll in MyKeySet
	public void RetainAll2Test() {
		MapAdapter mp = new MapAdapter();
		MapAdapter mp2 = new MapAdapter();
		mp.put("z",1);
		mp.put("a", "otto");
		mp.put("b", 55);
		mp2.put("a", "otto");
		mp2.put("b", 55);
		HCollection v = mp.values();
		HCollection v2 = mp2.values();
		v.retainAll(v2);
		assertEquals(2,v.size());
		assertEquals(true,v.contains("otto"));			//verifico che entry associata a "otto" sia presente
		assertEquals(true,v.contains(55));				
	}
	
	@Test
	public void Iterator1Test() {
		MapAdapter mp = new MapAdapter();
		mp.put("z",1);
		mp.put("a", "otto");
		mp.put("b", 55);
		mp.put("c", "gatto");
		HCollection v = mp.values();
		HIterator iter = v.iterator();
		try {
			iter.remove();
		}
		catch(IllegalStateException e) {
			assertEquals(true,true);
		}
		HIterator iter2 = v.iterator();
		while(iter.hasNext()) {
			Object x = iter.next();
			System.out.println(x);
			assertEquals(true,v.contains(x));
		}	
	}
	
	public void Iterator2Test() {
		MapAdapter mp = new MapAdapter();
		mp.put("z",1);
		mp.put("a", "otto");
		mp.put("b", 55);
		mp.put("c", "gatto");
		HCollection v = mp.values();
		HIterator iter = v.iterator();
		HIterator iter2 = v.iterator();
		iter.next();
		iter.remove();
		try {
			iter.remove();   		//non si puo' fare due volte di fila iter.remove()
		}
		catch(IllegalStateException e) {
			assertEquals(true,true);
		}
	}
}
