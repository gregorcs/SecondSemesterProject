package tests;

import org.junit.jupiter.api.Test;

import dao.DBConnection;

class SupplyOrderTests {

	DBConnection con = DBConnection.getInstance();

	@Test
	public void SupplyOrderWasCreated() {
		/*
		 * no time to implement
		//Arrange
		int createdId = 0;
		int foundId = 0;
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
			supplyOrderController.readSupplyOrder(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//TODO FINISH IT
		assertEquals(true, false);
		*/
	}
}
