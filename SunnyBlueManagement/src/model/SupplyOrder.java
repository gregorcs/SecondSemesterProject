package model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SupplyOrder {

	private Date date;
	private String urgency;
	private ArrayList<Item> listOfItems;
	
	public SupplyOrder(Date date, String urgency, ArrayList<Item> listOfItems) {
		super();
		this.date = date;
		this.urgency = urgency;
		this.listOfItems = listOfItems;
	}

	public SupplyOrder() {
		// TODO Auto-generated constructor stub
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public ArrayList<Item> getListOfItems() {
		return listOfItems;
	}

	public void setListOfItems(ArrayList<Item> listOfItems) {
		this.listOfItems = listOfItems;
	}
	
	public String getDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
        return dateFormat.format(getDate());  
	}
}
