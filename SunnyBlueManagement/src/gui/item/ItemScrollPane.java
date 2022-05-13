package gui.item;

import java.awt.Choice;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;

import controller.DecorationController;
import controller.ItemController;
import model.Decoration;
import model.Item;

public class ItemScrollPane extends JScrollPane {

	private static final long serialVersionUID = -4773670531023046534L;

	private Choice choiceDepartments;
	
	private JList<Item> itemList;
	private DefaultListModel<Item> listRepresentationItem;
	private JList<Decoration> decorationList;
	private DefaultListModel<Decoration> listRepresentationDecoration;
	
	
	private ItemController itemController;
	private DecorationController decorationController;
	
	public ItemScrollPane() {
		itemController = new ItemController();
		itemList = new JList<Item>();
		setViewportView(itemList);
		itemList.setFixedCellHeight(20);
		

		JMenuBar menuBar = new JMenuBar();
		setColumnHeaderView(menuBar);
		
		choiceDepartments = new Choice();
		menuBar.add(choiceDepartments);
		//constructChoiceDepartment();
		JLabel lblSortBy = new JLabel("Sort by:");
		menuBar.add(lblSortBy);
		
		initializeList();
	}
	
	public void initializeList() {
		ItemListCellRenderer cellRenderer = new ItemListCellRenderer();
		itemList.setCellRenderer(cellRenderer);
		updateListItem(itemController.readAllItems());
	}
	
	public void updateListItem(Collection<Item> listToShow) {
		listRepresentationItem = new DefaultListModel<Item>();
		Collection<Item> itemsFound = listToShow;
		
		for(Item item : itemsFound) {
			listRepresentationItem.addElement(item);
		}
		itemList.setModel(listRepresentationItem);
	}
	
	public void updateListDecoration(Collection<Decoration> listToShow) {
		listRepresentationItem = new DefaultListModel<Item>();
		Collection<Decoration> itemsFound = listToShow;
		
		for(Decoration decoration : itemsFound) {
			listRepresentationItem.addElement(decoration);
		}
		decorationList.setModel(listRepresentationDecoration);
	}
	
	public Item getSelectedItem() {return itemList.getSelectedValue();};
	/*
	private void constructChoiceDepartment() {
		choiceDepartments.add("any");
		itemController = new ItemController();
		for (String department : itemController.getAllDepartmentTypes()) {
			choiceDepartments.add(department);
		}
	}
	*/
	public String getDepartmentFromChoice() {
		return choiceDepartments.getItem(choiceDepartments.getSelectedIndex()).toString();
	}
}
