package myTest;
import myAdapter.*;
import myAdapter.MapAdapter;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapAdapterHashCodeTest {

	@Test
	public void MapAdapterHashCodeTest() {
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
		assertEquals(mp2.hashCode(),mp.hashCode());
		mp2.put("ciao", "x");
		assertEquals(false,mp.hashCode() == mp2.hashCode());
		assertEquals(false,mp.hashCode() == mp2.hashCode());
	}

}
