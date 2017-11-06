import javax.swing.*;
import java.util.*;

public class AddressBookModel {
	ArrayList<BuddyInfoModel> buddies;
	
	public AddressBookModel() {
		buddies = new ArrayList<BuddyInfoModel>();
	}
	
	public void addBuddy(BuddyInfoModel buddy) {
		if (buddy!=null) 
			buddies.add(buddy);
	}
	
	public void addBuddy(String name) {
		buddies.add(new BuddyInfoModel(name));
	}
	
	public BuddyInfoModel addBuddy(String name, String address, String phoneNumber) {
		BuddyInfoModel buddy = new BuddyInfoModel(name);
		buddy.setAddress(address);
		buddy.setPhoneNumber(phoneNumber);
		buddies.add(buddy);
		return buddy;
	}
	
	public BuddyInfoModel removeBuddy(BuddyInfoModel buddy) {
		if (buddy!=null) {
			buddies.remove(buddy);
			return buddy;
		}
		return null;
	}
	
	public String toString() {
		String temp = null;
		for (BuddyInfoModel buddy : buddies) {
			if (temp == null)
				temp = buddy + "";
			else
				temp += buddy + "";
		}
		return temp;
	}
	
	/*public static void main(String args[]) {
		AddressBookModel addressBook = new AddressBookModel();
		BuddyInfoModel homer = new BuddyInfoModel("Homer");
		
	}*/
}
