
public class Main {
	public static void main (String[] args) {
		AddressBookView addressBookView = new AddressBookView();
		AddressBookController addressBookController = new AddressBookController();
		
		addressBookView.setAddressBookController(addressBookController);
		addressBookController.setAddressBookView(addressBookView);
	}
	
	
}
