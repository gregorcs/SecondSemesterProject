package controller;

import java.util.Collection;

import dao.DaoFactory;
import dao.interfaces.DaoReservationIF;
import model.Decoration;
import model.LineItem;
import model.ReservationFolder.Reservation;
import model.ReservationFolder.Table;

public class ReservationController {
	
	private Reservation reservation;

	//creates reservation??? - should be in DAO
	public Collection<Table> enterDetails(int numOfPeople, String date, String reservationName, String specificRequests, long phoneNo, boolean isEvent) throws Exception {	
		reservation = new Reservation(date, numOfPeople, reservationName, specificRequests, phoneNo, isEvent);
		
		DaoReservationIF daoReservation = DaoFactory.createDaoReservation();
		Collection<Table> availableTables = daoReservation.readTablesByDate(date);
		
		return availableTables;
	}

	public Collection<Reservation> readReservationsByDate(String date) throws Exception{
		return DaoFactory.createDaoReservation().readReservationsByDate(date);
	}
	
	public boolean delete(Reservation reservation) {
		try {
			DaoFactory.createDaoReservation().delete(reservation);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//confirms the reservation
	public boolean confirmReservation() {
		try {
			DaoFactory.createDaoReservation().create(reservation);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void selectTable(Table table) {
		if(!reservation.getListOfTables().contains(table)) {
			reservation.addTable(table);
		}
	}
	
	public void removeTable(Table table) {
		if(reservation.getListOfTables().contains(table)) {
			reservation.removeTable(table);
		}
	}
	
	public Collection<Table> getSelectedTables() {
		return reservation.getListOfTables();
	}
	
	public void removeDecoration(LineItem<Decoration> decoration) {
		if(reservation.getListOfDecorations().contains(decoration)) {
			reservation.removeDecoration(decoration);
		}
	}
	
	public Collection<LineItem<Decoration>> getSelectedDecorations() {
		return reservation.getListOfDecorations();
	}
	
	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public String constructDetails(Reservation reservation) {
		String messageToShow = "Name: " + reservation.getReservationName()
						+ "\nDate: " + reservation.getDate()
						+ "\nNo. of people: " + reservation.getNumOfPeople()
						+ "\nPhone No.: " + reservation.getPhoneNo()
						+ "\n"
						+ "\nTables: ";
		for(Table table : reservation.getListOfTables()) {
			messageToShow += "\n"+table.getTableNo();
		}
		
		if(reservation.isEvent()) {
			messageToShow += "\n"
							+"\nDecorations:";
			for(LineItem<Decoration> decoration : reservation.getListOfDecorations()) {
				messageToShow += "\n" + decoration.getItem().getName() + "   Quantity: "+decoration.getQuantity();
			}
		
		}
		return messageToShow;
	}
}
