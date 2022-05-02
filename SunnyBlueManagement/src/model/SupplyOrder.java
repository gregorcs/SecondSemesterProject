package model;

import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SupplyOrder {

	private LocalDateTime date;
	private String urgency;
	private ArrayList<Item> listOfItems;
	
	public SupplyOrder(LocalDateTime date, String urgency, ArrayList<Item> listOfItems) {
		super();
		this.date = date;
		this.urgency = urgency;
		this.listOfItems = listOfItems;
	}

	public SupplyOrder() {
		// TODO Auto-generated constructor stub
	}

	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
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
	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");    
	   return dtf.format(this.getDate());
	}
}
