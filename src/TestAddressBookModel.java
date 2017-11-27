
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestAddressBookModel {
	private AddressBookModel addressBook = null;
	private BuddyInfoModel buddy = null;
	
	@Before
	public void setUp() {
		addressBook = new AddressBookModel();
	    buddy = new BuddyInfoModel("Homer");
	    buddy.setAddress("Springfield");
	    buddy.setPhoneNumber("613-123-4567");
	    buddy.setAge(19);
	}
	  
	@Test
	public void testEmptyAddressBook() {
		assertEquals("", 0, addressBook.size());
	}
	
	@Test
	public void testAddressBookWithOneBuddy() {
		addressBook.addBuddy(buddy);
		assertEquals("", 1, addressBook.size());
	}
	
	@Test
	public void testAddressBookAfterRemoveLastBuddy() {
		addressBook.removeBuddy(buddy);
		assertEquals("", 0, addressBook.size());
	}
	
	@Test
	public void testAddressBookAfterClear() {
		addressBook.addBuddy(buddy);
		addressBook.clear();
		assertEquals("", 0, addressBook.size());
	}
	
	@Test
	public void testUpdateBuddy() {
		addressBook.addBuddy(buddy);
		addressBook.updateBuddy(buddy, "Shelbyville", "819-123-4567");
		assertEquals("", "Shelbyville", buddy.getAddress());
		assertEquals("", "819-123-4567", buddy.getPhoneNumber());
	}
}