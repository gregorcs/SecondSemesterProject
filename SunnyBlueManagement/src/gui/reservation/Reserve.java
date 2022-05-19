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
import java.time.LocalDateTime;
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

import controller.ReservationController;
import gui.MainFrame;
import gui.decoration.DecorationScrollPane;
import gui.table.TableScrollPane;
import model.Decoration;
import model.ReservationFolder.Table;
import net.miginfocom.swing.MigLayout;
import javax.swing.JRadioButton;

public class Reserve extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2671428415317456781L;
	private MainFrame mainFrame;
	private ReservationController reservationController;
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
	private DecorationScrollPane paneDecorationsAvailable;
	private DecorationScrollPane paneDecorationsSelected;
	
	private JButton btnRemoveDecoration;
	private JButton btnSelectDecoration;
	
	private JRadioButton rdbtnIsEvent;
	
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
		EnterDetailsPanel.add(lblHeader, "cell 1 1,alignx center");
		
		Component rigidArea = Box.createRigidArea(new Dimension(20,20));
		EnterDetailsPanel.add(rigidArea, "cell 1 2,alignx center");
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		EnterDetailsPanel.add(rigidArea_1, "cell 2 7");
		
		paneTablesAvailable = new TableScrollPane();
		paneTablesSelected = new TableScrollPane();
		paneDecorationsAvailable = new DecorationScrollPane();
		paneDecorationsSelected = new DecorationScrollPane();
		
		createEnterDetailsLabels();
		createEnterDetailsButtons();
	}
	
	private void createReservationCreationLabels() {
		JLabel lblAvailable = new JLabel("Available");
		ChooseTablePanel.add(lblAvailable, "cell 0 2");
		
		JLabel lblSelected = new JLabel("Selected");
		ChooseTablePanel.add(lblSelected, "cell 3 2");
	}
	
	private void createReservationCreationButtons() {
		
		JLabel lblChooseTableHeader = new JLabel("Choose a table");
		lblChooseTableHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		ChooseTablePanel.add(lblChooseTableHeader, "cell 0 0,alignx left,aligny top");
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeTable();
			}
		});
		
		//Maybe one more JLabel?
		
		JButton btnSelectTable = new JButton("Select");
		btnSelectTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectTable();
			}
		});
		ChooseTablePanel.add(btnSelectTable, "cell 0 1,alignx left,aligny bottom");
		ChooseTablePanel.add(btnRemove, "cell 3 1");
		
		//DECORATIONBUTTONS
		btnSelectDecoration = new JButton("Select");
		btnSelectDecoration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectDecoration();
			}
		});
		ChooseTablePanel.add(btnSelectDecoration, "cell 0 4");
		
		btnRemoveDecoration = new JButton("Remove");
		btnRemoveDecoration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeDecoration();
			}
		});
		ChooseTablePanel.add(btnRemoveDecoration, "cell 3 4");
		
		
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
				confirmReservation();
			}
		});
		ChooseTablePanel.add(btnConfirmReservation, "cell 6 4");
	}
	
	private void constructReservationCreation() {
		//Table Selection panel
		ChooseTablePanel = new JPanel();
		layeredPane.add(ChooseTablePanel, "name_54378967892300");
		ChooseTablePanel.setLayout(new MigLayout("", "[173.00px,grow][165.00px,center][42.00][264.00,grow][][][]", "[14px][32.00][43.00][184.00,top][][grow]"));

		createReservationCreationLabels();
				
		ChooseTablePanel.add(paneTablesAvailable, "cell 0 3 2 1,grow");
		ChooseTablePanel.add(paneTablesSelected, "cell 3 3,grow");
		ChooseTablePanel.add(paneDecorationsSelected, "cell 3 5,grow");
		ChooseTablePanel.add(paneDecorationsAvailable, "cell 0 5 2 1,grow");
		
		createReservationCreationButtons();
	}
	
	public Reserve (final MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.reservationController = new ReservationController();
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
	
	public void updateScrollPane(TableScrollPane pane, Collection<Table> tables) {				//THESE 2 ARE DIFFERENT - INITIALIZE / UPDATELIST
		pane.initializeList(tables);
	}
	
	public void updateScrollPane(DecorationScrollPane pane, Collection<Decoration> decorations) {//THESE 2 ARE DIFFERENT - INITIALIZE / UPDATELIST
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
		Decoration decoration = paneDecorationsAvailable.getSelectedDecoration();
		if(decoration!=null) {
			reservationController.selectDecoration(decoration);
			askForDecorationAmount();
			updateScrollPane(paneDecorationsSelected, reservationController.getSelectedDecorations());
		}
	}
	
	private void removeDecoration() {
		Decoration decoration = paneDecorationsAvailable.getSelectedDecoration();
		if(decoration!=null) {
			reservationController.removeDecoration(decoration);
			updateScrollPane(paneDecorationsSelected, reservationController.getSelectedDecorations());
		}
	}
	
	private void toggleDecorations(boolean b) {
		paneDecorationsAvailable.setEnabled(b);
		paneDecorationsAvailable.setVisible(b);
		paneDecorationsSelected.setEnabled(b);
		paneDecorationsSelected.setVisible(b);
		btnRemoveDecoration.setEnabled(b);
		btnRemoveDecoration.setVisible(b);
		btnSelectDecoration.setEnabled(b);
		btnSelectDecoration.setVisible(b);
	}
	
}
