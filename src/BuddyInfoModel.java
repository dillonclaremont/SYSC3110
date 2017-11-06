
public class BuddyInfoModel {
	private String name;
	private String address;
	private String phoneNumber;
	
	public BuddyInfoModel(String name){
		setName(name);
	}
	
	public static void main(String[] args) {
		BuddyInfoModel buddy = new BuddyInfoModel("Homer");
		System.out.println("Hello, I am experimentaly returning: Simpson @ " + buddy.getName());

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String toString() {
		return "name: " + name + " \n" + "phone number: " + phoneNumber + " \n" + "address: " + address + " \n"; 
	}

}
