
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestBuddyInfoModel {
	private BuddyInfoModel buddy = null;
	
	@Before
	public void setUp() {
	    buddy = new BuddyInfoModel("Homer");
	    buddy.setAddress("Springfield");
	    buddy.setPhoneNumber("613-123-4567");
	    buddy.setAge(19);
	}
	  
	@Test
	public void testCloneContructor() {
		BuddyInfoModel cloneBuddy = new BuddyInfoModel(buddy);
		assertEquals("", buddy.getName(), cloneBuddy.getName());
		assertEquals("", buddy.getAddress(), cloneBuddy.getAddress());
		assertEquals("", buddy.getPhoneNumber(), cloneBuddy.getPhoneNumber());
		assertEquals("", buddy.getAge(), cloneBuddy.getAge());
		assertEquals("", buddy.getGreeting(), cloneBuddy.getGreeting());
	}
	
	@Test
	public void testGreeting() {
		assertEquals("Expected greeting:Hi my name is Homer.", "Hi my name is Homer.", buddy.getGreeting());
	}
	
	@Test
	public void testOver18() {
		buddy.setAge(19);
		assertEquals("Buddy is over 18.", true, buddy.isOver18());
	}
	
	@Test
	public void testUnder18() {
		buddy.setAge(17);
		assertEquals("Buddy is under 18.", false, buddy.isOver18());
	}
	
	@Test
	public void testToString() {
		assertEquals("", "Homer$613-123-4567$Springfield", buddy.toString());
	}
}
