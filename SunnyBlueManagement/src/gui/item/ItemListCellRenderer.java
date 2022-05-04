package gui.item;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.Item;


public class ItemListCellRenderer implements ListCellRenderer<Item> {

	private DefaultListCellRenderer dlcr;
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Item> list, Item value, int index, boolean isSelected,
			boolean cellHasFocus) {
		dlcr = new DefaultListCellRenderer();
		String textToShow = ("Name: " + value.getName() + ",  ID: " + value.getItemId() + ",  Department: " + value.getDepartmentType());

		return dlcr.getListCellRendererComponent(list, textToShow, index, isSelected, cellHasFocus);
	}
}
