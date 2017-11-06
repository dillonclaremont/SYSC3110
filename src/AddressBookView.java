import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
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
import javax.swing.JTextArea;

public class AddressBookView extends JFrame implements ActionListener {
		private AddressBookController addressBookController;
		private List <JMenuItem> menuItems = new ArrayList<JMenuItem>();
		private JList<BuddyInfoModel> jList;
		private DefaultListModel<BuddyInfoModel> listModel = new DefaultListModel<BuddyInfoModel>();
		
	AddressBookView(){
		JMenuBar menuBar = new JMenuBar();
		JMenu menu_addressBook = new JMenu("Address Book");
		JMenu menu_buddyInfo = new JMenu("Buddy Info");
		
		jList = new JList<BuddyInfoModel>(listModel);
		jList.setVisible(false);
		
		this.setSize(400, 800);
		this.setJMenuBar(menuBar);
		this.getContentPane().add(jList);
		
		//Create Menu Items and their respective actions
		JMenuItem create = new JMenuItem("Create");
		create.addActionListener(e -> {  addressBookController.createAddressBookModel();
										for (JMenuItem menuItem : menuItems) {
											menuItem.setEnabled(true);
										}});
		
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(e -> {BufferedWriter out = null;
									try { out = new BufferedWriter(new FileWriter("myfile.txt"));
									} catch (IOException e2) {}
									try { out.write(addressBookController.getAddressBookToString());
									} catch (IOException e1) {}
									try { out.close();
									} catch (IOException e1) {
									}});
		save.setEnabled(false);
		menuItems.add(save);
		
		JMenuItem display = new JMenuItem("Display");
		display.addActionListener(e -> jList.setVisible(true));
		display.setEnabled(false);
		menuItems.add(display);
		
		JMenuItem add = new JMenuItem("Add");
		add.addActionListener(e -> { String name = JOptionPane.showInputDialog("Name");
									String phoneNumber = JOptionPane.showInputDialog("Phone Number");
									String address = JOptionPane.showInputDialog("Address");
									listModel.addElement(addressBookController.addBuddy(name, address, phoneNumber));
									});
		add.setEnabled(false);
		menuItems.add(add);
		
		JMenuItem remove = new JMenuItem("Remove");
		remove.addActionListener(e -> listModel.removeElement(addressBookController.removeBuddy(jList.getSelectedValue())));
		remove.setEnabled(false);
		menuItems.add(remove);
		
		JMenuItem edit = new JMenuItem("Edit");
		edit.addActionListener(e -> { String phoneNumber = JOptionPane.showInputDialog("New Phone Number");
									  String address = JOptionPane.showInputDialog("New Address");
									  addressBookController.updateBuddy(jList.getSelectedValue(), address, phoneNumber);
									});
		edit.setEnabled(false);
		menuItems.add(edit);
		
		
		//Add all menuItems to it's menu
		menu_addressBook.add(create);
		menu_addressBook.add(save);
		menu_addressBook.add(display);
		menu_buddyInfo.add(add);
		menu_buddyInfo.add(remove);
		menu_buddyInfo.add(edit);
		
		//Add all menus to it's menubar
		menuBar.add(menu_addressBook);
		menuBar.add(menu_buddyInfo);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setVisible(true);
	}
	
	public void setAddressBookController(AddressBookController controller) {
		addressBookController = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
	}

}
