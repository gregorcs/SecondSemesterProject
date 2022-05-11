package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

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
	
	@Test
	public void itemIsCreated() {
		//Arrange
		ItemController itemController = new ItemController();
		Collection<Item> itemsFound = new ArrayList<Item>();
		String nameOfItem = "flower";
		Item itemMatch = new Item();
		//Act
		itemController.createItem(nameOfItem, DepartmentEnum.KITCHEN);
		itemController = new ItemController(); 		//gotta refresh the connection here
		itemsFound = itemController.readByNameItem(nameOfItem);
		
		for (Item temp : itemsFound) {
			if (temp.getName().equals(nameOfItem)) {
				itemMatch = temp;
			}
		}
		//Assert
		assertEquals(nameOfItem, itemMatch.getName());
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
