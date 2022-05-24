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

	public Table() {
	}

	public int getTableNo() {
		return tableNo;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public boolean getIsOutside() {
		return isOutside;
	}

	public void setIsOutside(boolean isOutside) {
		this.isOutside = isOutside;
	}
}