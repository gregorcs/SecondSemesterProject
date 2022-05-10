package gui;

import dao.DBConnection;

import javax.swing.*;

//TODO put it in gui
public class CheckConnectionWorker extends SwingWorker<String, String>{
	
	private MainMenu mainMenu;
	
	public CheckConnectionWorker(MainMenu mainMenu) {
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
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//TODO RETURN A MESSAGE TO THE USER
				e.printStackTrace();
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
