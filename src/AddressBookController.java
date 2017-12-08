import java.io.IOException;


public class AddressBookController {
	AddressBookModel addressBookModel;
	private AddressBookView addressBookView;
	
	AddressBookController(){
	}

	public void setAddressBookView(AddressBookView view) {
		addressBookView = view;
	}
	
	public void createAddressBookModel() {
		addressBookModel = new AddressBookModel();
		addressBookModel.addAddressBookListener(addressBookView);
	}
	
	public void setAddressBookModel(AddressBookModel addressBookModel) {
		this.addressBookModel = addressBookModel;
	}
	
	public void addBuddy(String name, String address, String phoneNumber) {
		addressBookModel.addBuddy(name, address, phoneNumber);
	}
	
	public void removeBuddy(BuddyInfoModel buddy) {
		addressBookModel.removeBuddy(buddy);
	}
	
	public String getAddressBookToString() {
		return addressBookModel.toString();
	}
		
	public void updateBuddy(BuddyInfoModel buddy, String address, String phoneNumber) {
		addressBookModel.updateBuddy(buddy, address, phoneNumber);
	}
	
	public void export() throws IOException {
		addressBookModel.export();
	}
	
	public void importAddressBook(String fileName) throws IOException {
		addressBookModel.importAddressBook(fileName);
	}
}
