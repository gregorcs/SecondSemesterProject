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
					mainMenu.updateConnectionOutput(true);
					mainMenu.refresh();
				} else {
					mainMenu.updateConnectionOutput(false);
					DBConnection.resetConnection();
					mainMenu.refresh();
				}
			} catch (Exception e) {
				mainMenu.updateConnectionOutput(false);
				mainMenu.refresh();
				e.printStackTrace();
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
