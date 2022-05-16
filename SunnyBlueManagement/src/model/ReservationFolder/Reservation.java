package model.ReservationFolder;

import java.util.ArrayList;

public class Reservation {
	
	private int reservationId;
	private int numOfPeople;
	private String date;
	private String reservationName;
	private String specificRequests;
	// ^^^^ these should be either a boolean isEvent or we can have event tags for different events
	private long phoneNo;
	private ArrayList<Table> listOfTables = new ArrayList<>();

	
	//constructor
	public Reservation(int reservationID, int numOfPeople, String date,String reservationName, String specificRequests, long phoneNo) {
		this.setNumOfPeople(numOfPeople);
		this.setDate(date);
		this.setReservationName(reservationName);
		this.setSpecificRequests(specificRequests);
		this.setPhoneNo(phoneNo);
	}
	
	/**
	 * if you need to create a reservation before it is inserted into the database
	 * @param numOfPeople
	 * @param date
	 * @param reservationName
	 * @param specificRequests
	 * @param phoneNo
	 */
	public Reservation(int numOfPeople, String date,String reservationName, String specificRequests, long phoneNo) {
		this.setNumOfPeople(numOfPeople);
		this.setDate(date);
		this.setReservationName(reservationName);
		this.setSpecificRequests(specificRequests);
		this.setPhoneNo(phoneNo);
	}

	public int getnumOfPeople() {
		return numOfPeople;
	}

	public void setNumOfPeople(int numOfPeople) {
		this.numOfPeople = numOfPeople;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getSpecificRequests() {
		return specificRequests;
	}

	public void setSpecificRequests(String specificRequests) {
		this.specificRequests = specificRequests;
	}

	public long getphoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public void addTable(Table table) {
		listOfTables.add(table);
	}
	
	public ArrayList<Table> getListOfTables(){
		return listOfTables;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationID(int reservationId) {
		this.reservationId = reservationId;
	}

}

