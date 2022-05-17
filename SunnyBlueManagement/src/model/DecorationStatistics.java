package model;

public class DecorationStatistics {

	private String month;
	private int averagePerMonth;
	
	public DecorationStatistics(int numOfMonth, int averagePerMonth) {
		super();
		this.month = numOfMonthToString(numOfMonth);
		this.averagePerMonth = averagePerMonth;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getAveragePerMonth() {
		return averagePerMonth;
	}

	public void setAveragePerMonth(int averagePerMonth) {
		this.averagePerMonth = averagePerMonth;
	}
	
	private String numOfMonthToString(int numOfMonth) {
		switch(numOfMonth) {
		case 1:
			return "January";
		case 2:
			return "Febraury";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";
		default:
			return "Invalid month number";
		}
	}
	
}
