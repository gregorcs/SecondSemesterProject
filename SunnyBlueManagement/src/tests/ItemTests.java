package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import controller.ItemController;
import dao.DBConnection;
import model.DepartmentEnum;
import model.Item;

class ItemTests {
	
	DBConnection con = DBConnection.getInstance();

	@Test
	public void itemIsRead() {
		//Arrange
		ItemController itemController = new ItemController();
		String itemName = "ham";
		//Act
		Item itemFound = itemController.readItem(3);
		//Assert
		assertEquals(itemName, itemFound.getName());
	}
	
	
	public void itemIsDeleted() {
		//Arrange
		ItemController itemController = new ItemController();
		//Act
		itemController.deleteItem(null);
		//Assert
		//TODO FINISH IT
	}
	
	@Test
	public void DepartmentEnumWorks() {
		//Arrange
		//Act
		DepartmentEnum enumFoundKitchen = DepartmentEnum.fromString("kitChen");
		DepartmentEnum enumFoundRestaurant = DepartmentEnum.fromString("ReStaUrant");
		//Assert
		assertEquals(DepartmentEnum.KITCHEN, enumFoundKitchen);
		assertEquals(DepartmentEnum.RESTAURANT, enumFoundRestaurant);
	}

}
