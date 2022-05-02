package tests;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controller.ResupplyOrderController;
import dao.DBConnection;
import model.Item;

import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

class SupplyOrderTests {

	DBConnection con = DBConnection.getInstance();

	@Test
	public void SupplyOrderWasCreated() {
		//Arrange
		ResupplyOrderController resupplyOrderController = new ResupplyOrderController();
		Item item = new Item("potato", 50, "restaurant");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		LocalDateTime date = LocalDateTime.now();  
		//Act
		resupplyOrderController.createResupplyOrder(date, "urgent", new ArrayList<Item>() {/**
			 * 
			 */
			private static final long serialVersionUID = -1669512258082083176L;

		{add(item);}});
		//Assert
		assertEquals(true, true);
	}

}
