import java.util.Scanner;

public class BuddyInfoModel {
	private String name;
	private String address;
	private String phoneNumber;
	private int age;
	
	public BuddyInfoModel(String name){
		setName(name);
	}
	
	public BuddyInfoModel(BuddyInfoModel buddy) {
		setName(buddy.getName());
		setAddress(buddy.getAddress());
		setPhoneNumber(buddy.getPhoneNumber());
		setAge(buddy.getAge());
	}
	
	public static BuddyInfoModel importBuddy(String buddy){
		Scanner s = new Scanner(buddy).useDelimiter("\\$");
		BuddyInfoModel b = new BuddyInfoModel(s.next());
		b.setPhoneNumber(s.next());
		b.setAddress(s.next());
		return b;
	}
	
	public String getGreeting() {
		return ("Hi my name is " + name + ".");
	}
	
	public boolean isOver18() {
		return (age > 18);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
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
		return name + "$" + phoneNumber + "$" + address; 
	}
	


}
