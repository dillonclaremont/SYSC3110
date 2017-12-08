import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AddressBookModel extends DefaultHandler implements Serializable{
	private static ArrayList<BuddyInfoModel> buddies;
	private transient List<AddressBookListener> addressBookListeners;
	
	public AddressBookModel() {
		buddies = new ArrayList<BuddyInfoModel>();
		addressBookListeners = new ArrayList<AddressBookListener>();
	}
	
	public void importAddressBook(String addressBookFile) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(addressBookFile));
		String buddyString = in.readLine();
		
		while (buddyString != null){		
			this.addBuddy(BuddyInfoModel.importBuddy(buddyString));
			buddyString = in.readLine();
		}
		in.close();
	}
	
	public void addAddressBookListener(AddressBookListener a) {
		addressBookListeners.add(a);
	}
	
	public void addBuddy(BuddyInfoModel buddy) {
		if (buddy!=null) {
			buddies.add(buddy);
			AddressBookEvent e = new AddressBookEvent(this, buddy);
			for (AddressBookListener a : addressBookListeners) {
				a.buddyAdded(e);
			}
		}
	}
	
	public void addBuddy(String name) {
		BuddyInfoModel buddy = new BuddyInfoModel(name);
		buddies.add(buddy);
		AddressBookEvent e = new AddressBookEvent(this, buddy);
		for (AddressBookListener a : addressBookListeners) {
			a.buddyAdded(e);
		}
	}
	
	public BuddyInfoModel addBuddy(String name, String address, String phoneNumber) {
		BuddyInfoModel buddy = new BuddyInfoModel(name);
		buddy.setAddress(address);
		buddy.setPhoneNumber(phoneNumber);
		buddies.add(buddy);
		
		AddressBookEvent e = new AddressBookEvent(this, buddy);
		for (AddressBookListener a : addressBookListeners) {
			a.buddyAdded(e);
		}
		
		return buddy;
	}
	
	public BuddyInfoModel removeBuddy(BuddyInfoModel buddy) {
		if (buddy!=null) {
			buddies.remove(buddy);
			AddressBookEvent e = new AddressBookEvent(this, buddy);
			for (AddressBookListener a : addressBookListeners) {
				a.buddyRemoved(e);
			}
			return buddy;
		}
		return null;
	}
	
	public void updateBuddy(BuddyInfoModel buddy, String address, String phoneNumber) {
		buddy.setAddress(address);
		buddy.setPhoneNumber(phoneNumber);
	}
	
	public String toString() {
		String temp = null;
		for (BuddyInfoModel buddy : buddies) {
			if (temp == null)
				temp = buddy.toString() + "\n";
			else
				temp += buddy.toString() + "\n";
		}
		return temp;
	}
	
	public int size() {
		return buddies.size();
	}
	
	public void clear() {
		buddies = new ArrayList<BuddyInfoModel>();
	}

	public void export() throws IOException {
		BufferedWriter out = null;
		out = new BufferedWriter(new FileWriter("myfile.txt"));
		out.write(this.toString());
		out.close();
	}
	
/*METHODS ADDED FOR LAB 8*/
/* Part 1*/	
	public void exportSerialized() throws IOException {
		String filename = "addressbook.ser";
		FileOutputStream fout = new FileOutputStream(filename);
		ObjectOutputStream out = new ObjectOutputStream(fout);
		
		out.writeObject(this);
		out.close();
	}
	
	public static AddressBookModel importSerialized() throws IOException, ClassNotFoundException {
		String filename = "addressbook.ser";
		FileInputStream fin = new FileInputStream(filename);
		ObjectInputStream in = new ObjectInputStream(fin);
		AddressBookModel temp =  (AddressBookModel) in.readObject();
		in.close();
		return temp;
	}
	
	/**
	 * Lab 8 - Part 2
	 * Create XML String from Address Book and it's Buddies.
	 * @return
	 */
	public String toXML() {
		String xml;
		xml = "<AddressBook>";
		for (BuddyInfoModel buddy : buddies) {
			xml += "\n" + buddy.toXML("\t");
		}
		xml += "\n</AddressBook>";
		return xml;
	}
	
	public void exportToXmlFile(String filename) throws IOException {
		BufferedWriter out = null;
		out = new BufferedWriter(new FileWriter(filename));
		out.write(this.toXML());
		out.close();
	}

	/**
	 * Lab 8 - Part 2
	 * Method to import AddressBook details from XML file.
	 * 
	 * @param f
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void importFromXmlFile(File f) throws ParserConfigurationException, SAXException, IOException {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser s = spf.newSAXParser();
			
			DefaultHandler dh = new DefaultHandler() {
				BuddyInfoModel buddyBuilder = null;				//Used to build buddy from xml
				boolean bAge = false;							//Used to coordinate values from XML tags
				boolean bAddress = false;						//	''
				boolean bPhoneNumber = false;					//	''
				
				public void startElement(String u, String ln, String qName, Attributes a) {
					if (qName.equals("AddressBook")) {
					} else if (qName.equals("Buddy")) {
						if (buddyBuilder == null) {
							buddyBuilder = new BuddyInfoModel(a.getValue("name"));
						}
					} else if (qName.equals("Age")) {
						bAge = true;
					} else if (qName.equals("Address")) {
						bAddress = true;
					} else if (qName.equals("PhoneNumber")) {
						bPhoneNumber = true;
					}
				}
				
				public void endElement(String uri, String localName, String qName) {
					if (qName.equals("Buddy")) {
						buddies.add(buddyBuilder);
						buddyBuilder = null;
					}
				}
				
				public void characters(char[] ch, int start, int length) {
					if (bAge) {
						buddyBuilder.setAge(Integer.parseInt(new String(ch, start, length)));
						bAge = false;
					} else if (bAddress) {
						buddyBuilder.setAddress(new String(ch, start, length));
						bAddress = false;
					} else if (bPhoneNumber) {
						buddyBuilder.setPhoneNumber(new String(ch, start, length));
						bPhoneNumber = false;
					}
				}
			};
			s.parse(f, dh);
	}
}
