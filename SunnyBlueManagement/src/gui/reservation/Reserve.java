package gui.reservation;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.ReservationController;
import gui.MainFrame;
import gui.table.TableScrollPane;
import model.ReservationFolder.Table;
import net.miginfocom.swing.MigLayout;

public class Reserve extends JPanel {

	private static final long serialVersionUID = 69L;
	private MainFrame mainFrame;
	private ReservationController reservationController;
	private JLayeredPane layeredPane;
	private JPanel EnterDetailsPanel;
	private JPanel ChooseTablePanel;
	private JPanel SpecificRequirementsPanel;
	//Should Event panels be implemented here or in a separate class?
	private JTextField textField;
	private JTextField textName;
	private JTextField textNumOfPeople;
	private JTextField textDate;
	private JTextField textPhoneNum;
	private TableScrollPane paneTablesAvailable;
	private TableScrollPane paneTablesSelected;
	
	//Panel creation
	
	public Reserve (final MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.reservationController = new ReservationController();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		layeredPane = new JLayeredPane();
		add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		//TODO verify parameters for alignment and positioning
		EnterDetailsPanel = new JPanel();
		layeredPane.add(EnterDetailsPanel, "name_28904848226400");
		EnterDetailsPanel.setLayout(new MigLayout("", "[grow][89px][108.00px][70.00,grow][-109.00]", "[grow][23px][][][][][][][][][grow]"));
		
		JLabel lblHeader = new JLabel("Enter Details");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		EnterDetailsPanel.add(lblHeader, "cell 1 1,alignx center");
		
		Component rigidArea = Box.createRigidArea(new Dimension(20,20));
		EnterDetailsPanel.add(rigidArea, "cell 1 2,alignx center");
		
		paneTablesAvailable = new TableScrollPane();
		paneTablesSelected = new TableScrollPane();
		
		//TEXT FIELDS AND LABELS 
		
		JLabel lblReservationName = new JLabel("Reservation Name:");
		EnterDetailsPanel.add(lblReservationName, "cell 1 3,alignx trailing");
				
		textName = new JTextField();
		EnterDetailsPanel.add(textName, "cell 2 3,alignx center");
		textName.setColumns(10);
		
		
		
		JLabel lblNumOfPeople = new JLabel("No. of people:");
		EnterDetailsPanel.add(lblNumOfPeople, "cell 1 4,alignx trailing");
		
		textNumOfPeople = new JTextField();
		EnterDetailsPanel.add(textNumOfPeople, "cell 2 4,alignx center");
		textNumOfPeople.setColumns(10);
		
		
		
		JLabel lblDate = new JLabel("Date:");
		EnterDetailsPanel.add(lblDate, "cell 1 5,alignx trailing");
		
		textDate = new JTextField();
		EnterDetailsPanel.add(textDate, "cell 2 5,alignx center");
		textDate.setColumns(10);
		
		
		
		JLabel lblPhoneNo = new JLabel("Phone Number:");
		EnterDetailsPanel.add(lblPhoneNo, "cell 1 6,alignx trailing");
		
		textPhoneNum = new JTextField();
		EnterDetailsPanel.add(textPhoneNum, "cell 2 6,alignx center");
		textPhoneNum.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.backToMainMenu();
				
			}
		});
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textDate.getText().equals("") || textName.getText().equals("") || textNumOfPeople.getText().equals("") || textPhoneNum.getText().equals("")) {   /////CHANGE HERE IF YOU WANT TO CHECK FOR DIFFERENT THINGS LIKE "IS INT....
			        JOptionPane.showMessageDialog(null, "Please enter all details", "Missing details", JOptionPane.ERROR_MESSAGE);
				}
				else {
					//GET ALL TABLES WITH DETAILS
					int numOfPeople = Integer.parseInt(textNumOfPeople.getText());
					String date = textDate.getText();
					String reservationName = textName.getText();
					String specificRequests = ""; 				//ADD SPECIFIC REQUESTS HERE !!!! 
					int phoneNo = Integer.parseInt(textPhoneNum.getText());
					
					try {
						Collection<Table> tables = reservationController.enterDetails(numOfPeople, date, reservationName, specificRequests, phoneNo);
						switchReservePanels(ChooseTablePanel);
						updateScrollPane(paneTablesAvailable ,tables);
						

					} catch (Exception e1) {
						e1.printStackTrace();
					}
					//Update the panel
				}
			}
		});
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		EnterDetailsPanel.add(rigidArea_1, "cell 2 7");
		
		//
		
		EnterDetailsPanel.add(btnProceed, "cell 2 8,growx");
		
		EnterDetailsPanel.add(btnBack, "cell 2 9,growx");
		
		
		//Table Selection panel
		ChooseTablePanel = new JPanel();
		layeredPane.add(ChooseTablePanel, "name_54378967892300");
		ChooseTablePanel.setLayout(new MigLayout("", "[173.00px][165.00px,center][42.00][264.00,grow][][][]", "[14px][69.00][43.00][184.00,top][grow]"));
		
		JLabel lblChooseTableHeader = new JLabel("Choose a table");
		lblChooseTableHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		ChooseTablePanel.add(lblChooseTableHeader, "cell 0 0,alignx left,aligny top");
		
		//Maybe one more JLabel?
		
		JButton btnSelectTable = new JButton("Select");
		btnSelectTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Table table = paneTablesAvailable.getSelectedTable();
				if(table!=null) {
					reservationController.selectTable(table);
					updateScrollPane(paneTablesSelected, reservationController.getSelectedTables());
				}
			}
		});
		ChooseTablePanel.add(btnSelectTable, "cell 1 1,alignx left,aligny bottom");
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Table table = paneTablesSelected.getSelectedTable();
				if(table!=null) {
					reservationController.removeTable(table);
					updateScrollPane(paneTablesSelected, reservationController.getSelectedTables());
				}
			}
		});
		ChooseTablePanel.add(btnRemove, "cell 3 1");
		
		JLabel lblAvailable = new JLabel("Available");
		ChooseTablePanel.add(lblAvailable, "cell 0 2");
		
		JLabel lblSelected = new JLabel("Selected");
		ChooseTablePanel.add(lblSelected, "cell 3 2");
				
		ChooseTablePanel.add(paneTablesAvailable, "cell 0 3 2 1,grow");
		ChooseTablePanel.add(paneTablesSelected, "cell 3 3,grow");
		
		textField = new JTextField();
		ChooseTablePanel.add(textField, "cell 0 1,growx,aligny center");
		textField.setColumns(10);
		
		JButton btnBackFromChooseTable = new JButton("Back");
		btnBackFromChooseTable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				switchReservePanels(EnterDetailsPanel);
			}
		});
		
		ChooseTablePanel.add(btnBackFromChooseTable, "cell 4 4,aligny bottom");
		
		JButton btnConfirmReservation = new JButton("Confirm");
		btnConfirmReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reservationController.confirmReservation();
			}
		});
		ChooseTablePanel.add(btnConfirmReservation, "cell 6 4");
		//end of Table Selection
		
		//Specific Requirements Panel
		//TODO FINISH THIS UP
		SpecificRequirementsPanel = new JPanel();
		layeredPane.add(SpecificRequirementsPanel, "name_74326784378200");
	}
	
	public void switchReservePanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public void updateScrollPane(TableScrollPane pane, Collection<Table> tables) {
		pane.initializeList(tables);
	}
}