package controller;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.interfaces.DaoSupplyOrderIF;

import java.time.LocalDateTime;  

import model.LineItem;
import model.SupplyOrder;

public class SupplyOrderController {
	
	DaoSupplyOrderIF daoSupplyOrder = DaoFactory.createDaoSupplyOrder();

	public void createResupplyOrder(LocalDateTime date, String urgency, ArrayList<LineItem> listOfItems) {
		SupplyOrder supplyOrder = new SupplyOrder(date, urgency, listOfItems);
		try {
			daoSupplyOrder.create(supplyOrder);
		} catch (Exception e) {
			// TODO RETURN SOME KIND OF ERROR TO THE USER
			e.printStackTrace();
		}
	}
}
