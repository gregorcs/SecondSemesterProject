package tests;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controller.SupplyOrderController;
import dao.DBConnection;
import model.Item;
import model.LineItem;
import model.UrgencyEnum;


import java.time.LocalDateTime;

class SupplyOrderTests {

	DBConnection con = DBConnection.getInstance();

	@Test
	public void SupplyOrderWasCreated() {
		//Arrange
		SupplyOrderController resupplyOrderController = new SupplyOrderController();
		Item item = new Item(1, "potato", "restaurant");
		Item item2 = new Item(2, "decoration", "kitchen");
		LineItem<Item> lineItem = new LineItem<Item>(40, item);
		LineItem<Item> lineItem2 = new LineItem<Item>(20, item2);
		LocalDateTime date = LocalDateTime.now();  
		//Act
		try {
			/*
			resupplyOrderController.createSupplyOrder(date, UrgencyEnum.HIGH, new ArrayList<LineItem<Item>>() {
				private static final long serialVersionUID = -1669512258082083176L;
			{add(lineItem);
			 add(lineItem2);}});
*/		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//TODO FINISH IT
	}
}
