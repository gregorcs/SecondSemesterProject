package model;

import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SupplyOrder {

	private int supplyOrderId;
	private LocalDateTime date;
	private String urgency;
	private ArrayList<LineItem> listOfItems;
	
	public SupplyOrder(int supplyOrderId, LocalDateTime date, String urgency, ArrayList<LineItem> listOfItems) {
		super();
		this.supplyOrderId = supplyOrderId;
		this.date = date;
		this.urgency = urgency;
		this.listOfItems = listOfItems;
	}

	/**
	 * in case you need to create a supplyOrder before it is inserted into the database (you don't know the id yet)
	 * @param date
	 * @param urgency
	 * @param listOfItems
	 */
	public SupplyOrder(LocalDateTime date, String urgency, ArrayList<LineItem> listOfItems) {
		super();
		this.date = date;
		this.urgency = urgency;
		this.listOfItems = listOfItems;
	}
	
	public SupplyOrder() {
		this.listOfItems = new ArrayList<LineItem>();
		this.date = LocalDateTime.now();
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

	public ArrayList<LineItem> getListOfItems() {
		return listOfItems;
	}

	public void setListOfItems(ArrayList<LineItem> listOfItems) {
		this.listOfItems = listOfItems;
	}
	
	public void addLineItem(LineItem lineItem) {
		this.listOfItems.add(lineItem);
	}
	
	public void removeLineItem(LineItem LineItem) {
		this.listOfItems.remove(LineItem);
	}
	
	public String getDateString() {
	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");    
	   return dtf.format(this.getDate());
	}

	public int getSupplyOrderId() {
		return supplyOrderId;
	}

	public void setSupplyOrderId(int supplyOrderId) {
		this.supplyOrderId = supplyOrderId;
	}
}
