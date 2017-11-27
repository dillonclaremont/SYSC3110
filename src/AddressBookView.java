import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class AddressBookView extends JFrame implements ActionListener, AddressBookListener{
		AddressBookController addressBookController;
		private List <JMenuItem> menuItems = new ArrayList<JMenuItem>();
		private JList<BuddyInfoModel> jList;
		private DefaultListModel<BuddyInfoModel> listModel;
		
	AddressBookView(AddressBookController controller){
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		//Set this View's controller
		addressBookController = controller;
		
		//Set List Model for this Views JList object, initially hidden, add to content pane
		listModel = new DefaultListModel<BuddyInfoModel>();
		jList = new JList<BuddyInfoModel>(listModel);
		jList.setVisible(false);
		this.getContentPane().add(jList);
		
		//Default size
		this.setSize(400, 800);
		
		//Create and add menus to  menuBar
		menuBar.add(createAddressBookMenu());
		menuBar.add(createBuddyMenu());
		
		//Default behavior on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setVisible(true);
	}
	
	private JMenu createAddressBookMenu() {
		JMenu menu_addressBook = new JMenu("Address Book");
		
		//Create Menu Items and their respective actions
		JMenuItem create = new JMenuItem("Create");
		create.addActionListener(e -> {  
			addressBookController.createAddressBookModel();
			for (JMenuItem menuItem : menuItems) {
				menuItem.setEnabled(true);
			}
		});
				
		JMenuItem importAddressBook = new JMenuItem("Import");
		importAddressBook.addActionListener(e -> {  
			try {
				addressBookController.importAddressBook("myfile.txt");
			} catch (IOException e1) {}
		});
		importAddressBook.setEnabled(false);
		menuItems.add(importAddressBook);
		
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(e -> {
			addressBookController.export();
		});
		save.setEnabled(false);
		menuItems.add(save);
				
		JMenuItem display = new JMenuItem("Display");
		display.addActionListener(e -> jList.setVisible(true));
		display.setEnabled(false);
		menuItems.add(display);
				
		//Add all menuItems to it's menu
		menu_addressBook.add(create);
		menu_addressBook.add(importAddressBook);
		menu_addressBook.add(save);
		menu_addressBook.add(display);
		
		return menu_addressBook;
	}

	private JMenu createBuddyMenu() {
		JMenu menu_buddyInfo = new JMenu("Buddy Info");
		
		//Create Menu items
		JMenuItem add = new JMenuItem("Add");
		add.addActionListener(e -> { String name = JOptionPane.showInputDialog("Name");
									String phoneNumber = JOptionPane.showInputDialog("Phone Number");
									String address = JOptionPane.showInputDialog("Address");
									addressBookController.addBuddy(name, address, phoneNumber);
									});
		add.setEnabled(false);
		menuItems.add(add);
		
		JMenuItem remove = new JMenuItem("Remove");
		remove.addActionListener(e -> addressBookController.removeBuddy(jList.getSelectedValue()));
		remove.setEnabled(false);
		menuItems.add(remove);
		
		JMenuItem edit = new JMenuItem("Edit");
		edit.addActionListener(e -> { String phoneNumber = JOptionPane.showInputDialog("New Phone Number");
									  String address = JOptionPane.showInputDialog("New Address");
									  addressBookController.updateBuddy(jList.getSelectedValue(), address, phoneNumber);
									});
		edit.setEnabled(false);
		menuItems.add(edit);

		//Add menu items to menu
		menu_buddyInfo.add(add);
		menu_buddyInfo.add(remove);
		menu_buddyInfo.add(edit);
		
		return menu_buddyInfo;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
	}

	@Override
	public void buddyAdded(AddressBookEvent e) {
		listModel.addElement(e.getBuddy());
	}

	@Override
	public void buddyRemoved(AddressBookEvent e) {
		listModel.removeElement(e.getBuddy());
	}

}
