
import java.util.EventObject;

@SuppressWarnings("serial")
public class AddressBookEvent extends EventObject {
	private BuddyInfoModel buddy;
	
	public AddressBookEvent(Object source, BuddyInfoModel buddy) {
		super(source);
		this.buddy = buddy;
	}
	
	public BuddyInfoModel getBuddy() {
		return buddy;
	}
}
