package gui.reservation;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.ReservationFolder.Reservation;
import model.ReservationFolder.Table;

public class ReservationListCellRenderer implements ListCellRenderer<Reservation> {

	private DefaultListCellRenderer	dlcr;
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Reservation> list, Reservation value, int index,
			boolean isSelected, boolean cellHasFocus) {
		dlcr = new DefaultListCellRenderer();
		String tables = "";
		for(Table table : value.getListOfTables()) {
			tables+= table.getTableNo() + "   ";
		}
		String textToShow = "Name: " + value.getReservationName() + "| Date: " + value.getDate() + "| Phone no: " + value.getphoneNo() + "| Tables: " + tables;
		
		return dlcr.getListCellRendererComponent(list, textToShow, index, isSelected, cellHasFocus);
	}

}