package gui.reservation;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.DecorationController;
import controller.ReservationController;
import gui.GenericScrollPane;
import gui.MainFrame;
import gui.decoration.DecorationListCellRenderer;
import gui.decoration.LineItemDecorationListCellRenderer;
import gui.table.TableScrollPane;
import model.Decoration;
import model.LineItem;
import model.ReservationFolder.Table;
import net.miginfocom.swing.MigLayout;
import javax.swing.JRadioButton;

public class ReserveGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2671428415317456781L;
	private MainFrame mainFrame;
	private ReservationController reservationController;
	private DecorationController decorationController;
	private JLayeredPane layeredPane;
	
	private JPanel EnterDetailsPanel;
	private JPanel ChooseTablePanel;
	private JPanel SpecificRequirementsPanel;
	
	private JTextField textName;
	private JTextField textNumOfPeople;
	private JTextField textDate;
	private JTextField textPhoneNum;
	
	private TableScrollPane paneTablesAvailable;
	private TableScrollPane paneTablesSelected;
	private GenericScrollPane<Decoration> paneDecorationsAvailable;
	private GenericScrollPane<LineItem<Decoration>> paneDecorationsSelected;
	
	private JButton btnRemoveDecoration;
	private JButton btnSelectDecoration;
	
	private JRadioButton rdbtnIsEvent;
	private JLabel lblAvailableDecorations;
	private JLabel lblSelectedDecorations;
	
	
	//Panel creation
	
	private void constructLayeredPane() {
		layeredPane = new JLayeredPane();
		add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
	}
	
	private void createEnterDetailsLabels() {
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
	}

	private void createEnterDetailsButtons() {
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.backToMainMenu();
			}
		});
		
		rdbtnIsEvent = new JRadioButton("Event");
		EnterDetailsPanel.add(rdbtnIsEvent, "cell 1 8");
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createReservation();
			}
		});
		EnterDetailsPanel.add(btnProceed, "cell 2 8,growx");
		EnterDetailsPanel.add(btnBack, "cell 2 9,growx");
	}
	
	private void constructEnterDetails() {
		//TODO verify parameters for alignment and positioning
		EnterDetailsPanel = new JPanel();
		layeredPane.add(EnterDetailsPanel, "name_28904848226400");
		EnterDetailsPanel.setLayout(new MigLayout("", "[grow][89px][108.00px][70.00,grow][-109.00]", "[grow][23px][][][][][][][][][grow]"));
		
		JLabel lblHeader = new JLabel("Enter Details");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		EnterDetailsPanel.add(lblHeader, "cell 1 1 2 1,alignx center");
		
		Component rigidArea = Box.createRigidArea(new Dimension(20,20));
		EnterDetailsPanel.add(rigidArea, "cell 1 2,alignx center");
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		EnterDetailsPanel.add(rigidArea_1, "cell 2 7");
		
		paneTablesAvailable = new TableScrollPane();
		paneTablesSelected = new TableScrollPane();
    
		decorationController = new DecorationController();
		paneDecorationsAvailable = new GenericScrollPane<Decoration>(decorationController.readAllDecorations(), new DecorationListCellRenderer());
		paneDecorationsSelected = new GenericScrollPane<LineItem<Decoration>>(new ArrayList<LineItem<Decoration>>(), new LineItemDecorationListCellRenderer());
		
		createEnterDetailsLabels();
		createEnterDetailsButtons();
	}
	
	private void createReservationCreationLabels() {
		JLabel lblAvailableTables = new JLabel("Available tables");
		lblAvailableTables.setFont(new Font("Tahoma", Font.BOLD, 12));
		ChooseTablePanel.add(lblAvailableTables, "cell 1 3,alignx center");
		
		JLabel lblSelectedTables = new JLabel("Selected tables");
		lblSelectedTables.setFont(new Font("Tahoma", Font.BOLD, 12));
		ChooseTablePanel.add(lblSelectedTables, "cell 4 3,alignx center");
		
		lblAvailableDecorations = new JLabel("Available decorations");
		lblAvailableDecorations.setFont(new Font("Tahoma", Font.BOLD, 12));
		ChooseTablePanel.add(lblAvailableDecorations, "cell 1 7,alignx center");
		
		lblSelectedDecorations = new JLabel("Selected decorations");
		lblSelectedDecorations.setFont(new Font("Tahoma", Font.BOLD, 12));
		ChooseTablePanel.add(lblSelectedDecorations, "cell 4 7,alignx center");
		ChooseTablePanel.add(paneDecorationsSelected, "cell 4 8,grow");
		ChooseTablePanel.add(paneDecorationsAvailable, "cell 1 8,grow");
	}
	
	private void createReservationCreationButtons() {
		
		JLabel lblChooseTableHeader = new JLabel("Create reservation");
		lblChooseTableHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		ChooseTablePanel.add(lblChooseTableHeader, "cell 1 1 4 1,alignx center,aligny top");
		
		JButton btnConfirmReservation = new JButton("Confirm");
		btnConfirmReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmReservation();
			}
		});
		
		
		JButton btnSelectTable = new JButton("Select table");
		btnSelectTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectTable();
			}
		});
		ChooseTablePanel.add(btnSelectTable, "cell 1 5,alignx left,aligny bottom");
		
		JButton btnRemoveTable = new JButton("Remove table");
		btnRemoveTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeTable();
			}
		});
		ChooseTablePanel.add(btnRemoveTable, "cell 4 5");
		
		//DECORATION BUTTONS
		btnSelectDecoration = new JButton("Select decoration");
		btnSelectDecoration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectDecoration();
			}
		});
		ChooseTablePanel.add(btnSelectDecoration, "cell 1 9");
		
		btnRemoveDecoration = new JButton("Remove decoration");
		btnRemoveDecoration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeDecoration();
			}
		});
		ChooseTablePanel.add(btnRemoveDecoration, "cell 4 9");
		ChooseTablePanel.add(btnConfirmReservation, "flowx,cell 5 10,alignx right,aligny bottom");
		
		JButton btnSpecificReq = new JButton("Add specific request");
		btnSpecificReq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addSpecificRequest();
			}
		});
		ChooseTablePanel.add(btnSpecificReq, "cell 4 10");
		ChooseTablePanel.add(btnConfirmReservation, "flowx,cell 5 11,alignx right,aligny bottom");
		
		JButton btnBackFromChooseTable = new JButton("Back");
		btnBackFromChooseTable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				switchReservePanels(EnterDetailsPanel);
			}
		});
		
		ChooseTablePanel.add(btnBackFromChooseTable, "cell 5 10,alignx right,aligny bottom");
	}
	
	private void constructReservationCreation() {
		//Table Selection panel
		ChooseTablePanel = new JPanel();
		ChooseTablePanel.setBounds(100, 100, 1920, 1080);
		layeredPane.add(ChooseTablePanel, "name_54378967892300");
		ChooseTablePanel.setLayout(new MigLayout("", "[grow][grow][center][][grow][grow]", "[grow][14px][32.00][][184.00,grow,top][][][][grow][][grow]"));

		createReservationCreationLabels();
				
		ChooseTablePanel.add(paneTablesAvailable, "cell 1 4,grow");
		ChooseTablePanel.add(paneTablesSelected, "cell 4 4,grow");
		
		//Maybe one more JLabel?
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		ChooseTablePanel.add(rigidArea, "cell 1 6");
		
		createReservationCreationButtons();
	}
	
	public ReserveGUI (final MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.setBounds(100, 100, 1920, 1080);
		this.reservationController = new ReservationController();
		this.decorationController = new DecorationController();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		constructLayeredPane();
		constructEnterDetails();
		constructReservationCreation();
		
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
	
	/**
	 * updates the parameter JPanel with the parameter Collection
	 * @param pane
	 * @param tables
	 */
	public void updateScrollPane(TableScrollPane pane, Collection<Table> tables) {
		pane.initializeList(tables);
	}
	
	/**
	 * refreshes the passed in scrollPane, accepts DecorationScrollPane<any type>
	 * updates it with a Collection<any type>
	 * @param <T>
	 * @param pane
	 * @param decorations
	 */
	public <T> void updateScrollPane(GenericScrollPane<T> pane, Collection<T> decorations) {//THESE 2 ARE DIFFERENT - INITIALIZE / UPDATELIST
		pane.updateList(decorations);
	}
	
	private int askForDecorationAmount() {
        String input = JOptionPane.showInputDialog("Enter amount of decoration");
        //todo edge case handling here
        return Integer.parseInt(input);
	}
		
	private void cleanReservationPanel() {
		textName.setText("");
        textNumOfPeople.setText("");
        textPhoneNum.setText("");
        textDate.setText("");
        switchReservePanels(EnterDetailsPanel);
        updateScrollPane(paneTablesSelected, reservationController.getSelectedTables());
        updateScrollPane(paneDecorationsSelected, reservationController.getSelectedDecorations());
        //decorationController = new DecorationController();
        //updateScrollPane(paneDecorationsAvailable, decorationController.readAllDecorations());
	}
	
	public boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
	
	private void createReservation() {
		if(textDate.getText().equals("") || textName.getText().equals("") || textNumOfPeople.getText().equals("") || textPhoneNum.getText().equals("")) {   /////CHANGE HERE IF YOU WANT TO CHECK FOR DIFFERENT THINGS LIKE "IS INT....
	        JOptionPane.showMessageDialog(null, "Please enter all details", "Missing details", JOptionPane.ERROR_MESSAGE);
	        return;
		}
		if(!isValid(textDate.getText())) {
	        JOptionPane.showMessageDialog(null, "Please use following date format: 'dd/mm/yyyy' !", "Incorrect date format", JOptionPane.ERROR_MESSAGE);
	        return;
		}
		
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date1 = sdf.parse(textDate.getText());
			Date date2 = new Date();
			
			if(!date1.after(date2)) {
		        JOptionPane.showMessageDialog(null, "You can only make a reservation for a future date!", "Incorrect date format", JOptionPane.ERROR_MESSAGE);
		        return;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		if (!Pattern.matches("[0-9]+", textPhoneNum.getText())) {
			JOptionPane.showMessageDialog(null, "Phone number cannot contain a letter!", "Incorrect phoneNo format", JOptionPane.ERROR_MESSAGE);
	        return;
		}
		
		if(!Pattern.matches("[0-9]+", textNumOfPeople.getText())) {
			JOptionPane.showMessageDialog(null, "Number of people cannot be a letter!", "Incorrect numOfPeople format", JOptionPane.ERROR_MESSAGE);
	        return;
		}
		
		/*if (textPhoneNum.getText().length()>10) {
			JOptionPane.showMessageDialog(null, "Phone number shouldn't be longer than 10 digits!", "Incorrect phoneNo format", JOptionPane.ERROR_MESSAGE);
	        return;
		}*/
			//GET ALL TABLES WITH DETAILS
		int numOfPeople = Integer.parseInt(textNumOfPeople.getText());
		String date = textDate.getText();
		String reservationName = textName.getText();
		String specificRequests = ""; 				//ADD SPECIFIC REQUESTS HERE !!!! 
		boolean isEvent = rdbtnIsEvent.isSelected();
		int phoneNo = Integer.parseInt(textPhoneNum.getText());
		
		try {
			Collection<Table> tables = reservationController.enterDetails(numOfPeople, date, reservationName, specificRequests, phoneNo, isEvent);
			switchReservePanels(ChooseTablePanel);
			updateScrollPane(paneTablesAvailable ,tables);		
			updateScrollPane(paneDecorationsAvailable ,decorationController.readAllDecorations());						
			updateScrollPane(paneTablesSelected, reservationController.getSelectedTables());
			updateScrollPane(paneDecorationsSelected, reservationController.getSelectedDecorations());
			
			if(rdbtnIsEvent.isSelected()) {
				toggleDecorations(true);
			}
				else{
				toggleDecorations(false);
			}
			
		} catch (Exception e1) {
				e1.printStackTrace();
		}
			//Update the panel
	}
	
	private void confirmReservation() {
		if(reservationController.getSelectedTables().size()==0) {
	        JOptionPane.showMessageDialog(null, "You didn't select any tables!", "Table selection", JOptionPane.ERROR_MESSAGE);
	        return;
		}
		if(reservationController.confirmReservation()) {
	        JOptionPane.showMessageDialog(null, "Reservation confirmed!");
	        cleanReservationPanel();
	        mainFrame.backToMainMenu();
		}
		else {
	        JOptionPane.showMessageDialog(null, "Reservation failed");
		}
	}
	
	private void selectTable() {
		Table table = paneTablesAvailable.getSelectedTable();
		if(table!=null) {
			reservationController.selectTable(table);
			updateScrollPane(paneTablesSelected, reservationController.getSelectedTables());
		}
	}
	
	private void removeTable() {
		Table table = paneTablesSelected.getSelectedTable();
		if(table!=null) {
			reservationController.removeTable(table);
			updateScrollPane(paneTablesSelected, reservationController.getSelectedTables());
		}
	}
	
	private void selectDecoration() {
		Decoration decoration = paneDecorationsAvailable.getSelectedObj();
		if(decoration!=null) {
			int quantity = askForDecorationAmount();
			reservationController.getReservation().addDecoration(new LineItem<Decoration>(quantity, decoration));;
			updateScrollPane(paneDecorationsSelected, reservationController.getSelectedDecorations());
		}
	}
	
	private void removeDecoration() {
		Decoration decoration = paneDecorationsAvailable.getSelectedObj();
		if(decoration!=null) {
			reservationController.removeDecoration(null);
			updateScrollPane(paneDecorationsSelected, reservationController.getSelectedDecorations());
		}
	}
	
	private void toggleDecorations(boolean b) {
		paneDecorationsAvailable.setEnabled(b);
		paneDecorationsAvailable.setVisible(b);
		
		paneDecorationsSelected.setEnabled(b);
		paneDecorationsSelected.setVisible(b);
		
		lblAvailableDecorations.setEnabled(b);
		lblAvailableDecorations.setVisible(b);
		
		lblSelectedDecorations.setEnabled(b);
		lblSelectedDecorations.setVisible(b);
		
		btnRemoveDecoration.setEnabled(b);
		btnRemoveDecoration.setVisible(b);
		
		btnSelectDecoration.setEnabled(b);
		btnSelectDecoration.setVisible(b);
	}
	
	private void addSpecificRequest() {
		String specReq = JOptionPane.showInputDialog("Add specific requests");
		reservationController.addSpecificRequest(specReq);
	}
	
}
