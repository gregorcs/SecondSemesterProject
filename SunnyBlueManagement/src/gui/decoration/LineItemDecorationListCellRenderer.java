package gui.decoration;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.Decoration;
import model.LineItem;

public class LineItemDecorationListCellRenderer implements ListCellRenderer<LineItem<Decoration>> {

	private DefaultListCellRenderer dlcr;
	
	@Override
	public Component getListCellRendererComponent(JList<? extends LineItem<Decoration>> list,
			LineItem<Decoration> value, int index, boolean isSelected, boolean cellHasFocus) {
		dlcr = new DefaultListCellRenderer();
		String textToShow = ("Name: " + value.getItem().getName() + ",   " + "Quantity: " + value.getQuantity());
		return dlcr.getListCellRendererComponent(list, textToShow, index, isSelected, cellHasFocus);
	}

}
