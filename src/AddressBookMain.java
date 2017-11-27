
public class AddressBookMain {
	public static void main (String[] args) {
		AddressBookController addressBookController = new AddressBookController();
		AddressBookView addressBookView = new AddressBookView(addressBookController);

		addressBookController.setAddressBookView(addressBookView);
	}
}
