package myTest;
import myAdapter.*;
import myAdapter.MapAdapter;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapAdapterPutTest {

	@Test
	public void MapAdapterPutElementTest() {
		MapAdapter mp = new MapAdapter(); 
		mp.put(1,1);
		boolean result = mp.containsKey(1);
		assertEquals(true,result);
	}
	
	@Test
	public void MapAdapterPutRemoveTest() {
		MapAdapter mp = new MapAdapter(); 
		mp.put(1,1);
		mp.put("ciao", "otto");
		mp.remove(1);
		mp.remove("ciao");
		boolean result = mp.isEmpty();
		assertEquals(true,result);
	}
	@Test
	public void MapAdapterPutSubstituteTest() {
		MapAdapter mp = new MapAdapter(); 
		mp.put(1,1);
		mp.put("ciao", "otto");
		mp.put("ciao", "finale");
		mp.put("ciao", "finao");
		mp.put("ciao", "tacos");
		assertEquals("tacos", mp.get("ciao"));
		assertEquals(2,mp.size());
	}
	
	

}
