package model.ReservationFolder;

public class Reservation {
	
	private int amountOfPeople;
	private String date;
	private String reservationName;
	private String specificRequests;
	// ^^^^ these should be either a boolean isEvent or we can have event tags for different events
	private long phoneNumber;

	public Reservation(int amountOfPeople, String Date,String reservationName, String specificRequests, long phoneNumber) {
		this.setAmountOfPeople(amountOfPeople);
		this.setDate(date);
		this.setReservationName(reservationName);
		this.setSpecificRequests(specificRequests);
		this.setPhoneNumber(phoneNumber);
	}

	public int getAmountOfPeople() {
		return amountOfPeople;
	}

	public void setAmountOfPeople(int amountOfPeople) {
		this.amountOfPeople = amountOfPeople;
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

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}

