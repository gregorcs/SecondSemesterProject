package controller;

import java.util.Collection;

import dao.DaoFactory;
import dao.interfaces.DaoReservationIF;
import model.Decoration;
import model.Item;
import model.LineItem;
import model.ReservationFolder.Reservation;
import model.ReservationFolder.Table;

public class ReservationController {
	
	Reservation reservation;
	
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
	
	//select table(s) for the reservation
	public void addTable(int tableNo) {
		//reservation.addTable(tableController.getTable(tableNo));
	}
	
	//choose decoration if the reservation is an event
	public void addDecoration(Item decoration) {
		
	}
	
	//to be implemented
	public String toString(){
		return null;
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
	
	//TODO RENAME TO ADD? ADD EDGE CASE HANDLING FOR CHECKING DUPLICATE
	public void selectDecoration(Decoration decoration, int quantity) {
		if(!reservation.getListOfDecorations().contains(new LineItem<Decoration>(quantity, decoration))) {
			reservation.addDecoration(new LineItem<Decoration>(quantity, decoration));
		}
	}
	
	public void removeDecoration(LineItem<Decoration> decoration) {
		if(reservation.getListOfDecorations().contains(decoration)) {
			reservation.removeDecoration(decoration);
		}
	}
	
	public Collection<LineItem<Decoration>> getSelectedDecorations() {
		return reservation.getListOfDecorations();
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
