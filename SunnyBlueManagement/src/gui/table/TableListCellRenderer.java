package gui.table;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.ReservationFolder.Table;

public class TableListCellRenderer implements ListCellRenderer<Table>{

	private DefaultListCellRenderer	dlcr;
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Table> list, Table value, int index,
			boolean isSelected, boolean cellHasFocus) {
		dlcr = new DefaultListCellRenderer();
		String textToShow = "No." + value.getTableNo() + ", Seats: " + value.getNoOfSeats() + ", Is Outside: " + value.getIsOutside();
		
		return dlcr.getListCellRendererComponent(list, textToShow, index, isSelected, cellHasFocus);
	}

}
