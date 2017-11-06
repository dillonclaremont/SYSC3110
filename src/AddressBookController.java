
public class AddressBookController {
	AddressBookModel addressBookModel;
	AddressBookView addressBookView;
	
	AddressBookController(){
	}
	
	public void setAddressBookView(AddressBookView view) {
		addressBookView = view;
	}
	
	public void createAddressBookModel() {
		addressBookModel = new AddressBookModel();
	}
	
	public BuddyInfoModel addBuddy(String name, String address, String phoneNumber) {
		return addressBookModel.addBuddy(name, address, phoneNumber);
	}
	
	public BuddyInfoModel removeBuddy(BuddyInfoModel buddy) {
		return addressBookModel.removeBuddy(buddy);
	}
	
	public String getAddressBookToString() {
		return addressBookModel.toString();
	}
		
	public void updateBuddy(BuddyInfoModel buddy, String address, String phoneNumber) {
		buddy.setAddress(address);
		buddy.setPhoneNumber(phoneNumber);
	}
}
