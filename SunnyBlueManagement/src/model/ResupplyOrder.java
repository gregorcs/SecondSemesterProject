package model;

import java.util.ArrayList;
import java.util.Date;

public class ResupplyOrder {

	private Date date;
	private String urgency;
	private ArrayList<Item> listOfItems;
	
	public ResupplyOrder(Date date, String urgency, ArrayList<Item> listOfItems) {
		super();
		this.date = date;
		this.urgency = urgency;
		this.listOfItems = listOfItems;
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
}
