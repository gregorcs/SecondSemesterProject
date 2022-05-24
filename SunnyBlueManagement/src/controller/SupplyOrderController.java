package controller;

import dao.DaoFactory;
import dao.interfaces.DaoSupplyOrderIF;
import model.Item;
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
	
	public SupplyOrder readSupplyOrder(int id) {
		this.daoSupplyOrder = DaoFactory.createDaoSupplyOrder();
		try {
			return daoSupplyOrder.read(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
		String messageToShow = "";
		if (!supplyOrder.getListOfItems().isEmpty()) {
			messageToShow += "Your order:";
			for (LineItem<Item> lineItem : supplyOrder.getListOfItems()) {
				messageToShow += System.lineSeparator() + "Name: " + lineItem.getItem().getName()
						+ System.lineSeparator() + "Quantity: " + lineItem.getQuantity();
			}
		} else {
			messageToShow += "Your order is empty";
		}
		return messageToShow;
	}
}