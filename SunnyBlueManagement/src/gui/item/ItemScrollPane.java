package gui.item;

import java.awt.Choice;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;

import controller.ItemController;
import gui.ScrollPaneIF;
import model.Item;

public class ItemScrollPane extends JScrollPane implements ScrollPaneIF<Item>{

	private static final long serialVersionUID = -4773670531023046534L;

	private Choice choiceDepartments;
	
	private JList<Item> itemList;
	private DefaultListModel<Item> listRepresentationItem;
	
	
	private ItemController itemController;
	
	public ItemScrollPane() {
		itemController = new ItemController();
		itemList = new JList<Item>();
		setViewportView(itemList);
		itemList.setFixedCellHeight(20);
		

		JMenuBar menuBar = new JMenuBar();
		setColumnHeaderView(menuBar);
		JLabel lblSortBy = new JLabel("Sort by: ");
		menuBar.add(lblSortBy);
		choiceDepartments = new Choice();
		menuBar.add(choiceDepartments);
		constructChoiceDepartment();
		
		initializeList();
	}
	
	@Override
	public void initializeList() {
		ItemListCellRenderer cellRenderer = new ItemListCellRenderer();
		itemList.setCellRenderer(cellRenderer);
		itemController = new ItemController();
		updateListItem(itemController.readAllItems());
	}
	
	@Override
	public void updateListItem(Collection<Item> listToShow) {
		listRepresentationItem = new DefaultListModel<Item>();
		Collection<Item> itemsFound = listToShow;
		
		for(Item item : itemsFound) {
			listRepresentationItem.addElement(item);
		}
		itemList.setModel(listRepresentationItem);
	}
	
	@Override
	public Item getSelectedItem() {return itemList.getSelectedValue();};
	
	private void constructChoiceDepartment() {
		choiceDepartments.add("any");
		for (String department : itemController.getAllDepartmentTypes()) {
			choiceDepartments.add(department);
		}
	}
	
	@Override
	public String getDepartmentFromChoice() {
		return choiceDepartments.getItem(choiceDepartments.getSelectedIndex()).toString();
	}
}
