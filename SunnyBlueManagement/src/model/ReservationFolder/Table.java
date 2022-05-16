package model.ReservationFolder;

public class Table {
	private int tableNo;
	private int noOfSeats;
	private boolean isOutside;
	
	public Table(int tableNo, int noOfSeats, boolean isOutside) {
		this.tableNo = tableNo;
		this.noOfSeats = noOfSeats;
		this.isOutside = isOutside;
	}

	public boolean isOutside() {
		return isOutside;
	}

	public void setOutside(boolean isOutside) {
		this.isOutside = isOutside;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}
	
	public int getTableNo() {
		return tableNo;
	}
}
