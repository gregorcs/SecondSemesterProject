package tests;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;

import controller.ItemController;
import dao.DBConnection;
import model.Item;

class ItemTests {
	
	DBConnection con = DBConnection.getInstance();

	@Test
	public void itemIsRead() {
		//faulty logic
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
		Collection<Item> itemsFound;
		String name = "carrot";
		String department = "restaurant";
		Item itemMatch = new Item();
		//Act
		itemController.createItem(name, department);
		itemsFound = itemController.readAllItems();
		for (Item item : itemsFound) {
			if (item.getName().equals(name)) {
				itemMatch = item;
			}
		}
		//Assert
		assertEquals(name, itemMatch.getName());
	}
}
