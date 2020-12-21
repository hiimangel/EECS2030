package myUtil_Set;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	  TestMyHashSet.class ,
	  TestMyLinkedHashSet.class ,
	  TestMyTreeSet.class
	}) 
public class TestsAll {
}