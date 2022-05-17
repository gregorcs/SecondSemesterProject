package gui.decoration;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.Decoration;


public class DecorationListCellRenderer implements ListCellRenderer<Decoration> {

	private DefaultListCellRenderer dlcr;
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Decoration> list, Decoration value, int index, boolean isSelected,
			boolean cellHasFocus) {
		dlcr = new DefaultListCellRenderer();
		String textToShow = ("Name: " + value.getName() + ",  ID: " + value.getItemId() + ",  Department: " + value.getDepartmentType() + ",  In stock: " + value.getQuantityInStock());
		return dlcr.getListCellRendererComponent(list, textToShow, index, isSelected, cellHasFocus);
	}
}
