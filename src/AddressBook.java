import java.util.*;

public class AddressBook {
	ArrayList<BuddyInfo> buddies;
	
	public AddressBook() {
		buddies = new ArrayList<BuddyInfo>();
	}
	
	public void addBuddy(BuddyInfo buddy) {
		if (buddy!=null) 
			buddies.add(buddy);
	}
	
	public void removeBuddy(BuddyInfo buddy) {
		if (buddy!=null)
			buddies.remove(buddy);
	}
	
	public static void main(String args[]) {
		AddressBook addressBook = new AddressBook();
		BuddyInfo homer = new BuddyInfo("Homer");
		
		addressBook.addBuddy(homer);
		addressBook.removeBuddy(homer);
		
		System.out.println("Address Book");
	}
}
