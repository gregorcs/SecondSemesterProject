package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.junit.jupiter.api.Test;

import controller.ReservationController;
import dao.DBConnection;
import model.ReservationFolder.Reservation;
import model.ReservationFolder.Table;

class ReservationsTests {

	DBConnection con = DBConnection.getInstance();
	
	@Test
	void reservationIsCreated() {
		//Arrange
		ReservationController reservationController = new ReservationController();
		Collection<Table> tables = null;
		Collection<Reservation> reservationsFound = null;
		String date = "26/10/" + generateRandomYear();
		String name = "Test";
		Reservation reservationFound = null;
		boolean found = false;
		//Act
		try {
			tables = reservationController.enterDetails(5, date, name, "None", 328298234, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reservationController.getReservation().addTable(((ArrayList<Table>) tables).get(0));
		reservationController.confirmReservation();

		try {
			reservationsFound = reservationController.readReservationsByDate(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Reservation temp : reservationsFound) {
			if (temp.getReservationName().equals(name)) {
				found = true;
			}
		}
		
		//Assert
		assertTrue(found);
	}

	private int generateRandomYear() {
		Random r = new Random();
		int low = 2023;
		int high = 3000;
		return r.nextInt(high-low) + low;
	}
}
