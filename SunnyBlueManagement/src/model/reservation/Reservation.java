package model.reservation;

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

	 /**
	 * if you need to create a reservation before it is inserted into the database
	 * @param date
	 * @param numOfPeople
	 * @param reservationName
	 * @param specificRequests
	 * @param phoneNo
	 * @param isEvent
	 */
	public Reservation(String date, int numOfPeople, String reservationName, String specificRequests, long phoneNo, boolean isEvent) {
		constructCommon(date, numOfPeople, reservationName, specificRequests, phoneNo, isEvent);
	}

	public Reservation(int reservationID, String date, int numOfPeople, String reservationName, String specificRequests, long phoneNo, boolean isEvent) {
		this.setReservationID(reservationID);
		constructCommon(date, numOfPeople, reservationName, specificRequests, phoneNo, isEvent);
	}

	private void constructCommon(String date, int numOfPeople, String reservationName, String specificRequests, long phoneNo, boolean isEvent) {
		setDate(date);
		setNumOfPeople(numOfPeople);
		setReservationName(reservationName);
		setSpecificRequests(specificRequests);
		setPhoneNo(phoneNo);
		setEvent(isEvent);
	}

	public String constructDetails() {
		String messageToShow = "Name: " + getReservationName()
						+ "\nDate: " + getDate()
						+ "\nNo. of people: " + getNumOfPeople()
						+ "\nPhone No.: " + getPhoneNo()
						+ "\n"
						+ "\nTables: ";
		for(Table table : getListOfTables()) {
			messageToShow += "\n"+table.getTableNo();
		}

		if(isEvent()) {
			messageToShow += "\n"
							+"\nDecorations:";
			for(LineItem<Decoration> decoration : getListOfDecorations()) {
				messageToShow += "\n" + decoration.getItem().getName() + "   Quantity: "+decoration.getQuantity();
			}

		}
		return messageToShow;
	}

	public boolean isEvent() {
		return isEvent;
	}

	public void setEvent(boolean isEvent) {
		this.isEvent = isEvent;
	}

	public int getNumOfPeople() {
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

	public long getPhoneNo() {
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
		//TODO error handling
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
