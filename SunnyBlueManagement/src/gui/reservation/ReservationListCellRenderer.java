package gui.reservation;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.reservation.Reservation;

public class ReservationListCellRenderer implements ListCellRenderer<Reservation> {

	private DefaultListCellRenderer	dlcr;
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Reservation> list, Reservation value, int index,
			boolean isSelected, boolean cellHasFocus) {
		dlcr = new DefaultListCellRenderer();
		String textToShow = "Name: " + value.getReservationName() + "| Date: " + value.getDate() + "| Phone no: " + value.getPhoneNo() + "| Is event: " + value.isEvent() + "| Amount of tables: " + value.getListOfTables().size();
		
		return dlcr.getListCellRendererComponent(list, textToShow, index, isSelected, cellHasFocus);
	}

}
