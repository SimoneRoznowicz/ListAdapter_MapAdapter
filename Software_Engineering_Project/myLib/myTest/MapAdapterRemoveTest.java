package myTest;
import myAdapter.*;
import myAdapter.MapAdapter;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapAdapterRemoveTest {

	@Test
	public void MapAdapterRemoveElementTest() {
		MapAdapter mp = new MapAdapter(); 
		mp.put(1,1);
		mp.remove(0);
		mp.remove("ciao");
		assertEquals(1,mp.size());
	}

}
