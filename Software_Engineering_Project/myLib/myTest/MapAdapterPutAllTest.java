package myTest;
import myAdapter.*;
import myAdapter.MapAdapter;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapAdapterPutAllTest {

	@Test
	public void MapAdapterPutAllGetTest() {
		MapAdapter mp = new MapAdapter(); 
		MapAdapter mp1 = new MapAdapter(); 
		mp1.put(1,'a');
		mp1.put(2,"grande");
		mp.put(2,"ciccio");
		mp.putAll(mp1);
		assertEquals("grande",mp.get(2)); 
		assertEquals(2,mp.size());
	}
}
