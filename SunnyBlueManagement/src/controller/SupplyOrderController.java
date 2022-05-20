package controller;


import dao.DaoFactory;
import dao.interfaces.DaoSupplyOrderIF;

import model.SupplyOrder;

public class SupplyOrderController {
	
	private DaoSupplyOrderIF daoSupplyOrder;
	private SupplyOrder supplyOrder;
	
	public SupplyOrderController() {
		super();
		this.daoSupplyOrder = DaoFactory.createDaoSupplyOrder();
		this.supplyOrder = new SupplyOrder();
	}

	public boolean createSupplyOrder() {
		this.daoSupplyOrder = DaoFactory.createDaoSupplyOrder();
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
	
	public String createOrderSummary() {
		return supplyOrder.createOrderSummary();
	}
}
