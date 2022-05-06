package controller;

import dao.DBConnection;
import gui.MainMenu;

import java.sql.SQLException;

import javax.swing.*;

public class CheckConnectionController extends SwingWorker<String, String>{
	
	private MainMenu mainMenu;
	
	public CheckConnectionController(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}

	@Override
	protected String doInBackground() {
		DBConnection.getInstance().getDBcon();

		while(true) {
			try {
				if (DBConnection.getInstance().isConnectionValid()) {
					mainMenu.updateConnectionOutput("valid");
					mainMenu.refresh();
				} else {
					mainMenu.updateConnectionOutput("failed");
					mainMenu.refresh();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
