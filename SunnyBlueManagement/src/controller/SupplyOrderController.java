package controller;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.interfaces.DaoSupplyOrderIF;

import java.time.LocalDateTime;

import model.Item;
import model.LineItem;
import model.SupplyOrder;
import model.UrgencyEnum;

public class SupplyOrderController {
	
	private DaoSupplyOrderIF daoSupplyOrder;
	private SupplyOrder supplyOrder;
	
	public SupplyOrderController() {
		super();
		this.daoSupplyOrder = DaoFactory.createDaoSupplyOrder();
		this.supplyOrder = new SupplyOrder();
	}

	public void createSupplyOrder(LocalDateTime date, UrgencyEnum urgencyEnum, ArrayList<LineItem<Item>> listOfItems) {
		SupplyOrder supplyOrderToCreate = new SupplyOrder(date, urgencyEnum, listOfItems);

		try {
			if (listOfItems.isEmpty()) {
				throw new Exception("List is empty");
			} else {
				daoSupplyOrder.create(supplyOrderToCreate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean createSupplyOrder() {
		try {
			if (supplyOrder.getListOfItems().isEmpty()) {
				return false;
			} else {
				daoSupplyOrder.create(supplyOrder);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
