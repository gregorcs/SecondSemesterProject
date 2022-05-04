package tests;



import org.junit.jupiter.api.Test;

import controller.ItemController;
import dao.DBConnection;

class ItemTests {
	
	DBConnection con = DBConnection.getInstance();

	@Test
	public void AllItemsReturned() {
		//Arrange
		ItemController itemController = new ItemController();
		//Act
		itemController.readAll();
		//Assert
		
	}

}
