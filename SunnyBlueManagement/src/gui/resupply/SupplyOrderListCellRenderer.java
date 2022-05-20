package gui.resupply;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.Item;
import model.LineItem;
import model.SupplyOrder;

public class SupplyOrderListCellRenderer implements ListCellRenderer<SupplyOrder> {

	private DefaultListCellRenderer	dlcr;
	
	@Override
	public Component getListCellRendererComponent(JList<? extends SupplyOrder> list, SupplyOrder value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		dlcr = new DefaultListCellRenderer();

		String textToShow = "ID: " + value.getSupplyOrderId() + ",   Date: " + value.getDateString() + ",    Items list: ";
		for (LineItem<Item> item : value.getListOfItems()) {
			textToShow += " n: " + item.getItem().getName() + ", qty:" + item.getQuantity();
		}
		
		return dlcr.getListCellRendererComponent(list, textToShow, index, isSelected, cellHasFocus);
	}

}
