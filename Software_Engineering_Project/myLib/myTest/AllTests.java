package myTest;
import myAdapter.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ListAdapterAddAllTest.class, ListAdapterAddTest.class, ListAdapterContainsAllTest.class,
		ListAdapterEqualsTest.class, ListAdapterHashCodeTest.class, ListAdapterIteratorTest.class,
		ListAdapterRemoveAllTest.class, ListAdapterRemoveTest.class, ListAdapterSetTest.class,
		ListAdapterSublistTest.class, ListAdapterToArrayTest.class, ListAdapterListIteratorTest.class,
		MapAdapterEntrySetTest.class, MapAdapterEqualsTest.class, MapAdapterHashCodeTest.class,
		MapAdapterKeySetTest.class, MapAdapterPutAllTest.class, MapAdapterPutTest.class, MapAdapterRemoveTest.class,
		MapAdapterValuesTest.class })
public class AllTests {

}
