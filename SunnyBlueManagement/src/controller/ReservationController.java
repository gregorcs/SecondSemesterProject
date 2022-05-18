package controller;

import java.util.ArrayList;
import java.util.Collection;

import dao.DaoFactory;
import dao.implementation.DaoReservationImplementation;
import dao.interfaces.DaoReservationIF;
import model.Decoration;
import model.Item;
import model.ReservationFolder.Reservation;
import model.ReservationFolder.Table;

public class ReservationController {
	
	Reservation reservation;
	
	//creates reservation??? - should be in DAO
	public Collection<Table> enterDetails(int numOfPeople, String date, String reservationName, String specificRequests, long phoneNo, boolean isEvent) throws Exception {	
		reservation = new Reservation(numOfPeople, date, reservationName, specificRequests, phoneNo, isEvent);
		
		DaoReservationIF daoReservation = DaoFactory.createDaoReservation();
		Collection<Table> availableTables = daoReservation.readTablesByDate(date);
		
		return availableTables;
	}

	public Collection<Reservation> readReservationsByDate(String date) throws Exception{
		return DaoFactory.createDaoReservation().readReservationsByDate(date);
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
			reservation = new Reservation();
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public void selectDecoration(Decoration decoration) {
		if(!reservation.getListOfDecorations().contains(decoration)) {
			reservation.addDecoration(decoration);
		}
	}
	
	public void removeDecoration(Decoration decoration) {
		if(reservation.getListOfDecorations().contains(decoration)) {
			reservation.removeDecoration(decoration);
		}
	}
	
	public Collection<Decoration> getSelectedDecorations() {
		return reservation.getListOfDecorations();
	}
}
