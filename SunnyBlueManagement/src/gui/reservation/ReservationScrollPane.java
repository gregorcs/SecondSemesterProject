package gui.reservation;

import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import model.ReservationFolder.Reservation;

public class ReservationScrollPane extends JScrollPane{
private static final long serialVersionUID = -6447934041446584216L;
	
	private JList<Reservation> reservationList;
	private DefaultListModel<Reservation> listRepresentation;
	
	public ReservationScrollPane() {
		reservationList = new JList<Reservation>();
		setViewportView(reservationList);
		//setting style
		reservationList.setFixedCellHeight(20);
		//TODO initialize list here
	}
	
	public void initializeList(Collection<Reservation> arrayToShow) {
		ReservationListCellRenderer cellRenderer = new ReservationListCellRenderer();
		reservationList.setCellRenderer(cellRenderer);
		updateList(arrayToShow);
	}
	
	public void updateList(Collection<Reservation> arrayToShow) {
		listRepresentation = new DefaultListModel<Reservation>();
		
		for(Reservation reservation : arrayToShow) {
			listRepresentation.addElement(reservation);
		}
		reservationList.setModel(listRepresentation);
	}
		
	public Reservation getSelectedTable() {
		return reservationList.getSelectedValue();
	}
}
