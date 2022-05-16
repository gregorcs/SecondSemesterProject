package controller;

import java.util.ArrayList;
import java.util.Collection;

import dao.DaoFactory;
import dao.implementation.DaoReservationImplementation;
import model.Item;
import model.ReservationFolder.Reservation;
import model.ReservationFolder.Table;

public class ReservationController {
	
	Reservation reservation;
	Collection<Table> selectedTables;
	
	//creates reservation??? - should be in DAO
	public Collection<Table> enterDetails(int numOfPeople, String date, String reservationName, String specificRequests, long phoneNo) throws Exception {	
		reservation = new Reservation(numOfPeople, date, reservationName, specificRequests, phoneNo);
		selectedTables = new ArrayList<>();
		
		DaoReservationImplementation daoReservation = (DaoReservationImplementation) DaoFactory.createDaoReservation(); //CHANGE THIS FROM A CAST!
		Collection<Table> availableTables = daoReservation.readTablesByDate(date);
		
		return availableTables;
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
	public void confirmReservation() {
		try {
			for(Table table : selectedTables) {
				reservation.addTable(table);
			}
			
			DaoFactory.createDaoReservation().create(reservation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectTable(Table table) {
		if(!selectedTables.contains(table)) {
			selectedTables.add(table);
		}
	}
	
	public void removeTable(Table table) {
		if(selectedTables.contains(table)) {
			selectedTables.remove(table);
		}
	}
	
	public Collection<Table> getSelectedTables() {
		return selectedTables;
	}
}
