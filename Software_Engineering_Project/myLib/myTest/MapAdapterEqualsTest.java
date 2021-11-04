package myTest;
import myAdapter.*;
import myAdapter.MapAdapter;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapAdapterEqualsTest {

	@Test
	public void MapAdapterEqualstest() {
		MapAdapter mp = new MapAdapter(); 
		MapAdapter mp2 = new MapAdapter(); 
		mp.put("ciao", "otto");
		mp.put("ci", "finale");
		mp.put("ca", "finao");
		mp.put("co", "tacos");
		mp2.put("co", "tacos");
		mp2.put("ca", "finao");
		mp2.put("ci", "finale");
		mp2.put("ciao", "otto");
		assertEquals(true,mp.equals(mp2));
		mp2.put("ciao", "x");
		assertEquals(false,mp.equals(mp2));
		assertEquals(false,mp.equals(null));
	}
}
