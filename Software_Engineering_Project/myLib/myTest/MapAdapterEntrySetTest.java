package myTest;
import myAdapter.*;
import myAdapter.HCollection;
import myAdapter.HIterator;
import myAdapter.HMap;
import myAdapter.HSet;
import myAdapter.MapAdapter;
import myAdapter.HMap.HEntry;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapAdapterEntrySetTest {

	@Test
	public void RemoveTest() {
		MapAdapter mp = new MapAdapter();
		mp.put("z",1);
		mp.put("a", "otto");
		mp.put("b", 55);
		HSet es = mp.entrySet();
		HIterator iter = es.iterator();
		HMap.HEntry a = null;			
		while(iter.hasNext()) {
			HEntry x = (HEntry)iter.next();
			System.out.println(x.getKey());
			if((x.getKey()).equals("b")) {
				es.remove(x);
				a = x;					//tengo traccia dell'elemento x salvandolo in a (per fare il metodo es.contains(a))
			}
		}		
		assertEquals(2,es.size());					//verifico che la dimensione e' diminuita
		assertEquals(false,es.contains(a));			//verifico che l'elemento a non e' piu' presente

	}
	
	@Test
	public void Iterator1Test() {
		MapAdapter mp = new MapAdapter();
		mp.put("z",1);
		mp.put("a", "otto");
		mp.put("b", 55);
		mp.put("c", "gatto");
		HCollection es = mp.entrySet();
		HIterator iter = es.iterator();
		try {
			iter.remove();
		}
		catch(IllegalStateException e) {
			assertEquals(true,true);
		}
		HIterator iter2 = es.iterator();
		while(iter2.hasNext()) {
			HEntry x = (HEntry)iter2.next();		
			System.out.println(x.getKey());
			assertEquals(true,es.contains(x));
		}	
	}
	
	@Test
	public void Iterator2Test() {
		MapAdapter mp = new MapAdapter();
		mp.put("z",1);
		mp.put("a", "otto");
		mp.put("b", 55);
		mp.put("c", "gatto");
		HCollection es = mp.entrySet();
		HIterator iter = es.iterator();
		iter.next();
		iter.remove();
		try {
			iter.remove();   		//non si puo' fare due volte di fila iter.remove()
		}
		catch(IllegalStateException e) {
			System.out.println("entrato");
			assertEquals(true,true);
			return;
		}
	}

	@Test
	public void EntryClassTest() {
		MapAdapter mp = new MapAdapter();
		mp.put("z",1);
		mp.put("a", "otto");
		mp.put("b", 55);
		mp.put("c", "gatto");
		HCollection es = mp.entrySet();
		HIterator iter = es.iterator();
		HEntry entry = (HEntry) iter.next();
		System.out.println(entry.getKey() + " - " + entry.getValue());
		assertEquals(55,entry.setValue("cambiato"));
		assertEquals("cambiato",entry.getValue());
		

	}
}
