package model.ReservationFolder;

import java.util.ArrayList;
import java.util.Collection;

import model.Decoration;
import model.LineItem;

public class Reservation {
	
	private int reservationId;
	private int numOfPeople;
	private String date;
	private String reservationName;
	private String specificRequests;
	private long phoneNo;
	private Collection<Table> listOfTables = new ArrayList<>();
	private boolean isEvent;
	private Collection<LineItem<Decoration>> listOfDecorations = new ArrayList<>();
	
	//constructor
	public Reservation(int reservationID, int numOfPeople, String date,String reservationName, String specificRequests, long phoneNo) {
		this.setReservationID(reservationID);
		this.setNumOfPeople(numOfPeople);
		this.setDate(date);
		this.setReservationName(reservationName);
		this.setSpecificRequests(specificRequests);
		this.setPhoneNo(phoneNo);
	}
	
	public boolean isEvent() {
		return isEvent;
	}

	public void setEvent(boolean isEvent) {
		this.isEvent = isEvent;
	}

	public Reservation() {
		
	}
	/**
	 * if you need to create a reservation before it is inserted into the database
	 * @param numOfPeople
	 * @param date
	 * @param reservationName
	 * @param specificRequests
	 * @param phoneNo
	 */
	public Reservation(int numOfPeople, String date,String reservationName, String specificRequests, long phoneNo, boolean isEvent) {
		this.setNumOfPeople(numOfPeople);
		this.setDate(date);
		this.setReservationName(reservationName);
		this.setSpecificRequests(specificRequests);
		this.setPhoneNo(phoneNo);
		this.setEvent(isEvent);
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
	
	public void removeTable(Table table) {
		listOfTables.remove(table);
	}
	
	public Collection<Table> getListOfTables(){
		return listOfTables;
	}
	
	public void addDecoration(LineItem<Decoration> decoration) {
		listOfDecorations.add(decoration);
	}
	
	public void removeDecoration(LineItem<Decoration> decoration) {
		listOfDecorations.remove(decoration);
	}
	
	public Collection<LineItem<Decoration>> getListOfDecorations(){
		return listOfDecorations;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationID(int reservationId) {
		this.reservationId = reservationId;
	}

}

