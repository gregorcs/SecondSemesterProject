package controller;

import java.util.Collection;

import dao.DaoFactory;
import dao.interfaces.DaoIF;
import model.Item;
import model.ReservationFolder.Reservation;
import model.ReservationFolder.Table;

public class ReservationController {
	
	Reservation reservation;
	
	//creates reservation??? - should be in DAO
	public Collection<Table> enterDetails(int numOfPeople, String date, String reservationName, String specificRequests, long phoneNo) throws Exception {	
		reservation = new Reservation(numOfPeople, date, reservationName, specificRequests, phoneNo);
				
		DaoIF daoReservation = DaoFactory.createDaoReservation();
		Collection<Table> availableTables = daoReservation.readAll();
		
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
		
	}
}
