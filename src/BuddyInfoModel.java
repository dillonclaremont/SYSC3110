import java.io.Serializable;
import java.util.Scanner;

public class BuddyInfoModel implements Serializable {
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
		s.close();
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
	
/*METHODS ADDED FOR LAB 8
/* Part 2*/	
	/**
	 * Export Buddy to XML Format.
	 * @param inheritedWhiteSpace	- level of whitespace to add based on parent
	 * @return
	 */
	public String toXML(String inheritedWhiteSpace) {
		String xml;
		xml = inheritedWhiteSpace + "<Buddy name=\"" + name + "\">";
		//xml += "\n" + inheritedWhiteSpace + "\t<Name>" + name + "</Name>";
		xml += "\n" + inheritedWhiteSpace + "\t<Address>" + address + "</Address>";
		xml += "\n" + inheritedWhiteSpace + "\t<PhoneNumber>" + phoneNumber + "</PhoneNumber>";
		xml += "\n" + inheritedWhiteSpace + "\t<Age>" + age + "</Age>";
		xml += "\n" + inheritedWhiteSpace + "</Buddy>";
		return xml;
	}
}
