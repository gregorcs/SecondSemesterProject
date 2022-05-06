package controller;

import model.ReservationFolder.Reservation;

public class ReservationController {
	
	Reservation reservation;
	
	//creates reservation??? - should be in DAO
	public void enterDetails(int numOfPeople, String date, String reservationName, String specificRequests, long phoneNo) {	
		reservation = new Reservation(numOfPeople, date, reservationName, specificRequests, phoneNo);
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
