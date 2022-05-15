package model;

import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  

import java.util.ArrayList;

public class SupplyOrder {

	private int supplyOrderId;
	private LocalDateTime date;
	private UrgencyEnum urgencyEnum;
	private ArrayList<LineItem> listOfItems;
	
	public SupplyOrder(int supplyOrderId, LocalDateTime date, UrgencyEnum urgency, ArrayList<LineItem> listOfItems) {
		super();
		this.supplyOrderId = supplyOrderId;
		this.date = date;
		this.urgencyEnum = urgency;
		this.listOfItems = listOfItems;
	}

	/**
	 * in case you need to create a supplyOrder before it is inserted into the database (you don't know the id yet)
	 * @param date
	 * @param urgencyEnum
	 * @param listOfItems
	 */
	public SupplyOrder(LocalDateTime date, UrgencyEnum urgencyEnum, ArrayList<LineItem> listOfItems) {
		super();
		this.date = date;
		this.urgencyEnum = urgencyEnum;
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
	public UrgencyEnum getUrgencyEnum() {
		return urgencyEnum;
	}
	public void setUrgencyEnum(UrgencyEnum urgency) {
		this.urgencyEnum = urgency;
	}

	public ArrayList<LineItem> getListOfItems() {
		return this.listOfItems;
	}

	public void setListOfItems(ArrayList<LineItem> listOfItems) {
		this.setListOfItems(listOfItems);
	}
	
	public void addLineItem(LineItem lineItem) {
		boolean found = false;
		for (LineItem temp : this.getListOfItems()) {
			//TODO move this checking into a separate method
			if (temp.getItem().getName().equals(lineItem.getItem().getName())) {
				temp.setQuantity(temp.getQuantity() + lineItem.getQuantity());
				found = true;
			}
		}
		if (!found) {
			this.listOfItems.add(lineItem);
		}
	}
	
	public void removeLineItem(LineItem LineItem) {
		this.getListOfItems().remove(LineItem);
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
