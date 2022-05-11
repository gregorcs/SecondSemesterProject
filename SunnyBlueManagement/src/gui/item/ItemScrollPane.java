package gui.item;

import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import controller.ItemController;
import model.Item;

public class ItemScrollPane extends JScrollPane {

	private static final long serialVersionUID = -4773670531023046534L;

	private JList<Item> itemList;
	private DefaultListModel<Item> listRepresentation;
	
	private ItemController itemController;
	
	public ItemScrollPane() {
		itemController = new ItemController();
		itemList = new JList<Item>();
		setViewportView(itemList);
		itemList.setFixedCellHeight(20);
		initializeList();
	}
	
	public void initializeList() {
		ItemListCellRenderer cellRenderer = new ItemListCellRenderer();
		itemList.setCellRenderer(cellRenderer);
		updateList(itemController.readAllItems());
	}
	
	public void updateList(Collection<Item> listToShow) {
		listRepresentation = new DefaultListModel<Item>();
		Collection<Item> itemsFound = listToShow;
		
		for(Item item : itemsFound) {
			listRepresentation.addElement(item);
		}
		itemList.setModel(listRepresentation);
	}
	
	public Item getSelectedItem() {return itemList.getSelectedValue();};
	
}
