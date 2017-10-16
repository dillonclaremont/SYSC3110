
public class BuddyInfo {
	private String name;
	private String address;
	private String phoneNumber;
	
	public BuddyInfo(String name){
		setName(name);
	}
	public static void main(String[] args) {
		BuddyInfo buddy = new BuddyInfo("Homer");
		System.out.println("Hello, I am finally returning: " + buddy.getName());

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


}
