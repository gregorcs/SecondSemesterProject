package tests;


import org.junit.jupiter.api.Test;

import controller.SupplyOrderController;
import dao.DBConnection;
import model.Item;
import model.LineItem;
import model.UrgencyEnum;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

class SupplyOrderTests {

	DBConnection con = DBConnection.getInstance();

	@Test
	public void SupplyOrderWasCreated() {
		
		//Arrange
		SupplyOrderController supplyOrderController = new SupplyOrderController();
		Item item = new Item("potato", "restaurant");
		Item item2 = new Item("decoration", "kitchen");
		LineItem<Item> lineItem = new LineItem<Item>(40, item);
		LineItem<Item> lineItem2 = new LineItem<Item>(20, item2);
		LocalDateTime date = LocalDateTime.now();  
		//Act
		supplyOrderController.getSupplyOrder().addLineItem(lineItem);
		supplyOrderController.getSupplyOrder().addLineItem(lineItem2);
		supplyOrderController.getSupplyOrder().setUrgencyEnum(UrgencyEnum.LOW);
		try {
			supplyOrderController.createSupplyOrder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//TODO FINISH IT
		assertEquals(true, false);
	}
}
