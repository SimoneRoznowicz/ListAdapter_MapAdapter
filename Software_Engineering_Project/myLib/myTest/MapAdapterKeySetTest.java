package myTest;
import myAdapter.*;
import myAdapter.HIterator;
import myAdapter.HSet;
import myAdapter.MapAdapter;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapAdapterKeySetTest {
	
	@Test
	public void keySetModifyMapAdapterTest() {
		MapAdapter mp = new MapAdapter();
		HSet keyS = mp.keySet();
		assertEquals(true,keyS.isEmpty());
		mp.put(1,1);
		mp.put(2, "otto");
		mp.put(3, "finale");
		mp.put(4, "fino");
		mp.put(5, "pollo");
		mp.put(6, "gatto");
		mp.put(7, "dramma");
		keyS = mp.keySet();
		System.out.println("Final size is: " + mp.size());
		System.out.println("[1] == " + mp.get(1) + "  " + "[2] == " + mp.get(2) + "  " + 
				"[3] == " + mp.get(3) + "  " +  "[4] == " + mp.get(4) + "  " +  "[5] == " + 
				mp.get(5) + "  " +  "[6] == " + mp.get(6) + "  " +  "[7] == " + mp.get(7));
		Object[] arr = keyS.toArray();
		for(int i=0;i<keyS.size();i++) {
			System.out.println("[" + arr[i] + "] == "+ mp.get(arr[i]));
		}
		assertEquals(7, keyS.size());
	}
	
	@Test
	public void keySetModifyKeySetTest() {
		MapAdapter mp = new MapAdapter();
		HSet keyS = mp.keySet();
		mp.put(1,1);
		mp.put(2, "otto");
		mp.put(3, "finale");
		mp.put(4, "fino");
		mp.put(5, "pollo");
		mp.put(6, "gatto");
		mp.put(7, "dramma");
		keyS = mp.keySet();
		System.out.println("Initial size is: " + mp.size()); 
		keyS.remove(6);			//rimuovo chiave dal set, dovrebbe rimuoversi chiave e valore da mp
		System.out.println("Final size after remove is: " + mp.size()); 
		assertEquals(null, mp.get(6));			//nome_hashTable.remove() ritorna null se la chiave non e' presente
	}
	
	@Test
	public void keySetClearTest() {
		MapAdapter mp = new MapAdapter();
		mp.put(1,1);
		mp.put(2, "otto");
		mp.put(3, "finale");
		mp.put(4, "fino");
		mp.put(5, "pollo");
		mp.put(6, "gatto");
		mp.put(7, "dramma");
		HSet keyS = mp.keySet();
		keyS.clear();
		System.out.println(keyS.size()); 
		assertEquals(0,keyS.size());			
		assertEquals(0,mp.size());
		assertEquals(null,mp.get(3));
	}
	
	@Test
	public void keySetModifyKeyContainsAllTest() {
		MapAdapter mp = new MapAdapter();
		MapAdapter mp1 = new MapAdapter();
		mp.put(1,1);
		mp.put(2, "otto");
		mp.put(3, "finale");
		mp.put(4, "fino");
		mp.put(5, "pollo");
		mp.put(6, "gatto");
		mp.put(7, "dramma");
		mp1.put(5, "tetto");
		mp1.put(2, "lago");
		HSet keyS = mp.keySet();
		HSet keyS1 = mp1.keySet();
		keyS.containsAll(keyS1);
		mp.remove(6);										//elimino la chiave 6
		System.out.println(keyS.size()); 
		assertEquals(false,keyS.contains(6));	   //avendo eliminato k===6 e v corrispondente, il set keyS non contine la chiave 6 
		assertEquals(true,keyS.containsAll(keyS1));			//2 e 5 sono entrambe chiavi presenti in mp
	}
	
	@Test
	public void keySetEqualsTest() {
		MapAdapter mp = new MapAdapter();
		MapAdapter mp1 = new MapAdapter();
		MapAdapter mp2 = new MapAdapter();
		mp.put(1,1);
		mp.put(2, "otto");
		mp1.put(1, "tetto");
		mp1.put(2, "lago");
		mp2.put(3, "creato");
		mp2.put(1, "mare");
		mp2.put(2, 55);
		HSet keyS = mp.keySet();
		HSet keyS1 = mp1.keySet();
		HSet keyS2 = mp2.keySet();
		assertEquals(true,keyS.equals(keyS1));	  //verifico che equals mi restituisca che i due set di chiavi sono uguali
		assertEquals(false,keyS2.equals(keyS));
	}
	
	@Test
	public void keySetHashcodeTest() {
		MapAdapter mp = new MapAdapter();
		MapAdapter mp1 = new MapAdapter();
		mp.put("z",1);
		mp.put("a", "otto");
		mp1.put("z", "lago");
		mp1.put("a", "tetto");
		mp1.put("a", "letto"); 						//sostituzione del v per la chiave a. Di fatto non deve modificare il set di chiavi
		HSet keyS = mp.keySet();
		HSet keyS1 = mp1.keySet();
		assertEquals(true,keyS.hashCode()==keyS1.hashCode());	   
	}
	
	@Test
	public void keySetRemoveAllTest() {
		MapAdapter mp = new MapAdapter();
		MapAdapter mp1 = new MapAdapter();
		mp.put("z",1);
		mp.put("a", "otto");
		mp.put("b", 55);
		mp1.put("z", "lago");
		mp1.put("a", "tetto");
		
		HSet keyS = mp.keySet();
		HSet keyS1 = mp1.keySet();
		keyS.removeAll(keyS1);
		HIterator iter = keyS.iterator();
		Object x = null;
		while(iter.hasNext()) {
			x = iter.next();
		}
		assertEquals(true,mp.size()==1);			//elimino sia k==z che k==a con v associati. Rimane k==b con v associati
		assertEquals(55,mp.get(x)); 				//avendo eliminato k==a e k==z (con valori associati), rimane k==b con v==55
	}
	
	@Test
	public void keySetRetainAllTest() {
		MapAdapter mp = new MapAdapter();
		MapAdapter mp1 = new MapAdapter();
		mp.put("c",1);
		mp.put("a", "otto");
		mp.put("b", 55);
		mp1.put("z", "lago");
		mp1.put("a", "tetto");
		mp1.put("b", "tetto");
		
		HSet keyS = mp.keySet();
		HSet keyS1 = mp1.keySet();
		keyS.retainAll(keyS1);					//non dovrebbe fare niente dato che i due set di chiavi presentano le stesse chaivi
		HIterator iter = keyS.iterator();		
		assertEquals(2,mp.size());			
		//ora rimuovo da mp una chiave e riapplico retainAll(): 
		mp1.remove("a");
		keyS.retainAll(keyS1);						
		assertEquals(1,mp.size());					
	}
	
	@Test
	public void keySetIteratorTest() {
		MapAdapter mp = new MapAdapter();
		MapAdapter mp1 = new MapAdapter();
		mp.put("z",1);
		mp.put("a", "otto");
		mp.put("b", 55);
		HSet keyS = mp.keySet();
		HIterator iter = keyS.iterator();
		//iter.remove();    lancerebbe IllegalStateException() dato che non ho mai chiamato iter.next() prima
		while(iter.hasNext()) {						//nell'esempio l'iteratore itera b-->a-->z
			Object x = iter.next();
			if(x=="a"){
				if(iter.hasNext()) {
					iter.remove();					
				}
			}	
		}
		assertEquals(true,keyS.size()==2);
		assertEquals(false,keyS.contains("z"));
	}
	
	@Test
	public void keySetToArrayTest() {
		//metodo toArray()
		MapAdapter mp = new MapAdapter();
		MapAdapter mp1 = new MapAdapter();
		mp.put("z",1);
		mp.put("a", "otto");
		mp.put("b", 55);
		HSet keyS = mp.keySet();
		Object[] arr = keyS.toArray();
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
		assertEquals(3,arr.length);
		
		//metodo toArray(Object[] o)
		Object[] arrNew = new Object[10];
		arr = keyS.toArray(arrNew);
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]);			//stampa valori validi da indice 0 a indice 2 estremi inclusi 
		}
		assertEquals(10,arr.length);
	}
}
