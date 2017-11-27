import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AddressBookModel {
	private ArrayList<BuddyInfoModel> buddies;
	private List<AddressBookListener> addressBookListeners;
	
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

	public void export() {
		BufferedWriter out = null;
		try { 
			out = new BufferedWriter(new FileWriter("myfile.txt"));
		} catch (IOException e2) {}
		try { 
			out.write(this.toString());
		} catch (IOException e1) {}
		try { 
			out.close();
		} catch (IOException e1) {
		}
	}
}
