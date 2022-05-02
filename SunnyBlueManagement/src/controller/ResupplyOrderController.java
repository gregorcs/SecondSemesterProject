package controller;

import java.util.ArrayList;
import java.util.Date;

import model.Item;
import model.ResupplyOrder;

public class ResupplyOrderController {

	public void createResupplyOrder(Date date, String urgency, ArrayList<Item> listOfItems) {
		ResupplyOrder resupplyOrder = new ResupplyOrder(date, urgency, listOfItems);
	}
}
