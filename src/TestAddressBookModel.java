
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

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
	
/*Test ADDED FOR LAB 8*/
/* Part 1*/	
	/**
	 * Export Address Book. Import that same address book, and test to ensure they are equal
	 * @throws IOException
	 */
	@Test
	public void testExportAddressBook() throws IOException {
		AddressBookModel addressBook2 = new AddressBookModel();
		
		addressBook.addBuddy(buddy);
	    BuddyInfoModel buddy2 = new BuddyInfoModel("Bart");
	    buddy2.setAddress("Springfield");
	    buddy2.setPhoneNumber("819-123-4567");
	    buddy2.setAge(44);
	    addressBook.addBuddy(buddy2);
	    
	    addressBook.export();
	    addressBook2 .importAddressBook("myfile.txt"); 
	    assertEquals(addressBook.toString(), addressBook2.toString()); 
	}
	
	/**
	 * Export Address Book using Serializer. Import same address book, test to ensure they are equal
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@Test
	public void testExportAddressBookSer() throws IOException, ClassNotFoundException {
		AddressBookModel addressBook2 = new AddressBookModel();
		
		addressBook.addBuddy(buddy);
	    BuddyInfoModel buddy2 = new BuddyInfoModel("Bart");
	    buddy2.setAddress("Springfield");
	    buddy2.setPhoneNumber("819-123-4567");
	    buddy2.setAge(44);
	    addressBook.addBuddy(buddy2);
	    
	    addressBook.exportSerialized();
	    addressBook2 = AddressBookModel.importSerialized(); 
	    assertEquals(addressBook.toString(), addressBook2.toString()); 
	}
	
/* Part 2*/	
	/**
	 * Export Address Book to XML. Import address book from XML, test to ensure they are equal
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	@Test
	public void testExportXMLString() throws IOException, ClassNotFoundException, ParserConfigurationException, SAXException {
		AddressBookModel addressBook2 = new AddressBookModel();
		
		addressBook.addBuddy(buddy);
	    BuddyInfoModel buddy2 = new BuddyInfoModel("Bart");
	    buddy2.setAddress("Springfield");
	    buddy2.setPhoneNumber("819-123-4567");
	    buddy2.setAge(44);
	    addressBook.addBuddy(buddy2);
	    
	    addressBook.exportToXmlFile("exportedXML.xml");
	    
	    File f = new File("exportedXML.xml");
	    addressBook2 = new AddressBookModel();
	    addressBook2.importFromXmlFile(f);
	    assertEquals(addressBook.toXML(), addressBook2.toXML()); 
	}
}