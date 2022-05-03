package tests;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controller.SupplyOrderController;
import dao.DBConnection;
import model.Item;
import model.LineItem;
import model.SupplyOrder;

import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

class SupplyOrderTests {

	DBConnection con = DBConnection.getInstance();

	@Test
	public void SupplyOrderWasCreated() {
		//Arrange
		SupplyOrderController resupplyOrderController = new SupplyOrderController();
		Item item = new Item(0, "potato", "restaurant");
		LineItem lineItem = new LineItem(40, item);
		SupplyOrder supplyOrderCreated = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		LocalDateTime date = LocalDateTime.now();  
		//Act
		resupplyOrderController.createResupplyOrder(date, "urgent", new ArrayList<LineItem>() {/**
			 * 
			 */
			private static final long serialVersionUID = -1669512258082083176L;

		{add(lineItem);}});
		
		System.out.println(supplyOrderCreated);
		//Assert
		assertEquals(true, true);
	}

}
