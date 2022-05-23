package model;

import java.time.LocalDate;  
import java.time.format.DateTimeFormatter;  

import java.util.ArrayList;

public class SupplyOrder {

	private int supplyOrderId;
	private LocalDate date;
	private UrgencyEnum urgencyEnum;
	private ArrayList<LineItem<Item>> listOfItems;
	
	public SupplyOrder(int supplyOrderId, LocalDate date, UrgencyEnum urgency, ArrayList<LineItem<Item>> listOfItems) {
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
	public SupplyOrder(LocalDate date, UrgencyEnum urgencyEnum, ArrayList<LineItem<Item>> listOfItems) {
		super();
		this.date = date;
		this.urgencyEnum = urgencyEnum;
		this.listOfItems = listOfItems;
	}
	
	public SupplyOrder() {
		this.listOfItems = new ArrayList<LineItem<Item>>();
		this.date = LocalDate.now();
	}

	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public UrgencyEnum getUrgencyEnum() {
		return urgencyEnum;
	}
	public void setUrgencyEnum(UrgencyEnum urgency) {
		this.urgencyEnum = urgency;
	}

	public ArrayList<LineItem<Item>> getListOfItems() {
		return this.listOfItems;
	}

	public void setListOfItems(ArrayList<LineItem<Item>> listOfItems) {
		this.setListOfItems(listOfItems);
	}
	
	public void addLineItem(LineItem<Item> lineItem) {
		boolean found = false;
		for (LineItem<Item> temp : this.getListOfItems()) {
			if (isItemInList(temp, lineItem)) {
				//if the lineItem is already in the list, add the quantity to that lineItem
				temp.setQuantity(temp.getQuantity() + lineItem.getQuantity());
				found = true;
			}
		}
		if (!found) {
			this.listOfItems.add(lineItem);
		}
	}
	
	private boolean isItemInList(LineItem<Item> existing, LineItem<Item> toBeAdded) {
		if(existing.getItem().getName().equals(toBeAdded.getItem().getName())) {
			return true;
		}
		return false;
	}
	
	public void removeLineItem(LineItem<Item> LineItem) {
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
	
	public String createOrderSummary() {
		//put into controller
		String messageToShow = "";
		if (!getListOfItems().isEmpty()) {
			messageToShow += "Your order:";
			for (LineItem<Item> lineItem : getListOfItems()) {
				messageToShow += System.lineSeparator() + "Name: " + lineItem.getItem().getName()
						+ System.lineSeparator() + "Quantity: " + lineItem.getQuantity();
			}
		} else {
			messageToShow += "Your order is empty";
		}
		return messageToShow;
	}
}
