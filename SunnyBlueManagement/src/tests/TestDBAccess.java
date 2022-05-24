package tests;

import static org.junit.Assert.*;

import org.junit.*;
import dao.DBConnection;

public class TestDBAccess {

	DBConnection con = DBConnection.getInstance();
	
	@Test
	public void wasConnected() {
		assertNotNull("Connected - connection cannot be null", con);
		
		DBConnection.closeConnection();
		boolean wasNullified = DBConnection.instanceIsNull();
		assertTrue("Disconnected - instance set to null", wasNullified);
		
		con = DBConnection.getInstance();
		assertNotNull("Connected - connection cannot be null", con);		
	}
}