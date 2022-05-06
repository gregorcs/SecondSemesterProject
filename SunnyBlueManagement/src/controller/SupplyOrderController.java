package controller;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.interfaces.DaoSupplyOrderIF;

import java.time.LocalDateTime;  

import model.LineItem;
import model.SupplyOrder;

public class SupplyOrderController {
	
	private DaoSupplyOrderIF daoSupplyOrder;
	private SupplyOrder supplyOrder;
	
	public SupplyOrderController() {
		super();
		this.daoSupplyOrder = DaoFactory.createDaoSupplyOrder();
		this.supplyOrder = new SupplyOrder();
	}

	public void createSupplyOrder(LocalDateTime date, String urgency, ArrayList<LineItem> listOfItems) throws Exception {
		SupplyOrder supplyOrder = new SupplyOrder(date, urgency, listOfItems);
		if (listOfItems.isEmpty()) {
			throw new Exception();
		} else {daoSupplyOrder.create(supplyOrder);}
	}
	
	public void createSupplyOrder() throws Exception {
		if (supplyOrder.getListOfItems().isEmpty()) {
			throw new Exception();
		} else {daoSupplyOrder.create(supplyOrder);}
	}

	public SupplyOrder getSupplyOrder() {
		return supplyOrder;
	}

	public void setSupplyOrder(SupplyOrder supplyOrder) {
		this.supplyOrder = supplyOrder;
	}
	
	public void emptySupplyOrder() {
		supplyOrder = new SupplyOrder();
	}
}
