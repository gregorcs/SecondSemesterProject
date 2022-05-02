package controller;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.interfaces.DaoSupplyOrderIF;

import java.sql.Date;

import model.Item;
import model.SupplyOrder;

public class ResupplyOrderController {
	
	DaoSupplyOrderIF daoSupplyOrder = DaoFactory.createDaoSupplyOrder();

	public void createResupplyOrder(Date date, String urgency, ArrayList<Item> listOfItems) {
		SupplyOrder resupplyOrder = new SupplyOrder(date, urgency, listOfItems);
		try {
			daoSupplyOrder.create(resupplyOrder);
		} catch (Exception e) {
			// TODO RETURN SOME KIND OF ERROR TO THE USER
			e.printStackTrace();
		}
	}
}
