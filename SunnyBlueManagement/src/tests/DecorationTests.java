package tests;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;

import controller.DecorationController;
import dao.DBConnection;
import model.Decoration;

class DecorationTests {

	DBConnection con = DBConnection.getInstance();
	
	@Test
	void decoraitonIsCreated() {
		//Arrange
		DecorationController deocrationController = new DecorationController();
		Collection<Decoration> decorationsFound;
		String name = "decorationTest";
		String department = "restaurantDecorationTest";
		int stockQuantity = 15;
		Decoration decorationMatch = null;
		//Act
		deocrationController.createDecoration(name, department, stockQuantity);
		decorationsFound = deocrationController.readAllDecorations();
		for (Decoration decoration : decorationsFound) {
			if (decoration.getName().equals(name)) {
				decorationMatch = decoration;
			}
		}
		//Assert
		assertEquals(name, decorationMatch.getName());
	}
}
