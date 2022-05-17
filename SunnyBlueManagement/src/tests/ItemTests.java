package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import controller.ItemController;
import dao.DBConnection;
import model.Decoration;
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
		itemController.createItem(nameOfItem, "kitchen");
		itemController = new ItemController(); 		//gotta refresh the connection here
		itemsFound = itemController.readItemByNameAndDepartment(nameOfItem, "ANY");
		
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
	
	public void decorationWorks() {
		Item item = new Decoration("confetti", "restaurant", 5);
	}
}
