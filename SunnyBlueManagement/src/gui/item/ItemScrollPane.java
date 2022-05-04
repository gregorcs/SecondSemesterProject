package gui.item;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import model.Item;

public class ItemScrollPane extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4773670531023046534L;

	private JList<Item> itemList;
	private DefaultListModel<Item> listRepresentation;
	
	public ItemScrollPane() {
		itemList = new JList<Item>();
		setViewportView(itemList);
		//setting style
		itemList.setFixedCellHeight(20);
		
		//TODO initialize list here
	}
	
	public void initializeList() {
		
	}
	
	public void updateList() {
		listRepresentation = new DefaultListModel<Item>();
	}
	
	public Item getSelectedItem() {return itemList.getSelectedValue();};
	
	private void findAllItems() {
		//TODO needs to get it from dao item
	}
}
