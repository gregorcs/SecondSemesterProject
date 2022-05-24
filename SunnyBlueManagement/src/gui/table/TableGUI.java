package gui.table;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.*;
import controller.TableController;
import gui.GenericScrollPane;
import gui.MainFrame;
import model.reservation.Table;
import net.miginfocom.swing.MigLayout;

public class TableGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private TableController tableController;
	
	private JLayeredPane layeredPane;
	
	private JTextField textFieldSearch;
	private JTextField textFieldTableNo;
	private JTextField textFieldNoOfPeople;
	
	private JCheckBox chckbxOutside;
	
	private MainFrame mainFrame;
	private JPanel tablePanel;
	
	private GenericScrollPane<Table> tableScrollPane;
	
	/*
	 * GUI Creation
	 */
	
	public TableGUI(final MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.tableController = new TableController();
		setBounds(100, 100, 1920, 1080);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		constructLayeredPane();
		constructTablePanel();
	}
	
	private void constructLayeredPane() {
		layeredPane = new JLayeredPane();
		add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
	}
	
	private void constructTablePanel() {
		tablePanel = new JPanel();
		layeredPane.add(tablePanel, "name_3150264217800");
		tablePanel.setLayout(new MigLayout("", "[grow][173.00px][pref!,grow][95.00][113.00px,center][][][][][][][77.00][163.00][200.00,grow]", "[grow][][][14px][][][][41.00][40.00][39.00][36.00][36.00][][][][][][][][grow]"));

		tableScrollPane = new GenericScrollPane<Table>(new ArrayList<Table>(), new TableListCellRenderer());
		tablePanel.add(tableScrollPane, "cell 1 7 10 9,grow");
		
		chckbxOutside = new JCheckBox("Outside");
		tablePanel.add(chckbxOutside, "cell 12 11");
		
		readTables();
		createTableGUIButtons();
		createTableGUITextFields();
		createTableGUILabels();
	}
	
	private void createTableGUIButtons() {
		JButton btnCreateTable = new JButton("Create");
		btnCreateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createTable();
			}
		});
		tablePanel.add(btnCreateTable, "cell 12 13,alignx center");
	
		JButton btnSearchTable = new JButton("Search");
		btnSearchTable.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			searchTable();
		}
		});
		tablePanel.add(btnSearchTable, "flowx,cell 4 5,growx,aligny bottom");
		
		JButton btnDeleteTable = new JButton("Remove");
		tablePanel.add(btnDeleteTable, "cell 0 8,alignx right,aligny baseline");
		btnDeleteTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteTable();
			}
		});
	}
	
	private void createTableGUITextFields() {
		textFieldSearch = new JTextField();
		tablePanel.add(textFieldSearch, "cell 2 5 2 1,growx");
		textFieldSearch.setColumns(5);
		
		textFieldTableNo = new JTextField();
		tablePanel.add(textFieldTableNo, "cell 12 9,growx");
		textFieldTableNo.setColumns(5);
		
		textFieldNoOfPeople = new JTextField();
		tablePanel.add(textFieldNoOfPeople, "cell 12 10,growx");
		textFieldNoOfPeople.setColumns(10);
	}
	
	private void createTableGUILabels() {
		JLabel lblTableManagementHeader = new JLabel("Table Management");
		lblTableManagementHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		tablePanel.add(lblTableManagementHeader, "cell 1 1 12 1,alignx center,aligny top");
		
		JLabel lblEnterTableNo = new JLabel("Enter number of seats:");
		tablePanel.add(lblEnterTableNo, "flowx,cell 1 5,alignx trailing,aligny center");
						
		JLabel lblCreateTable = new JLabel("Table Creation");
		lblTableManagementHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		tablePanel.add(lblCreateTable, "cell 12 8,alignx center");
		
		JLabel lblTableNo = new JLabel("Table Number");
		tablePanel.add(lblTableNo, "cell 11 9,alignx center");
		
		JLabel lblNoOfPeople = new JLabel("Number of Persons");
		tablePanel.add(lblNoOfPeople, "cell 11 10,alignx center");
		
		JLabel lblOutside = new JLabel("Location");
		tablePanel.add(lblOutside, "cell 11 11,alignx center");
		
		JButton btnGoBack = new JButton("Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.backToMainMenu();
			}
		});
		tablePanel.add(btnGoBack, "cell 13 19,alignx right,aligny bottom");
	}
		
	/*
	 * Button Functionality
	 */
	
	private void createTable() {
		int tableNo = getTableNoFromTextField();
		int noOfPeople = getNoOfPeopleFromTextField();
		boolean isOutside = getChckbxOutside();
		
		boolean found = false;
		
		for (Table table : tableController.readAllTables()) {
			if (table.getTableNo() == tableNo) {
				found = true;
			}
		}
		
		if (found == false) {
			tableController.createTable(tableNo, noOfPeople, isOutside);
			readTables();
		} else {
			JOptionPane.showMessageDialog(mainFrame, "A table with this number already exists.");
		}
	}
	
	private void readTables() {
		Collection<Table> tablesFound = tableController.readAllTables();
		tableScrollPane.updateList(tablesFound);
	}
	
	private void deleteTable() {
		Table table = tableScrollPane.getSelectedObj();
		tableController.deleteTable(table);
		readTables();
	}
	
	private void searchTable() {
		int noOfSeats = getNoOfSeatsFromTextFieldSearch();
		Collection<Table> tablesFound = tableController.readTableByNoOfSeats(noOfSeats);
		tableScrollPane.updateList(tablesFound);
	}
	
	/*
	 *  Getters
	 */
	
	private int getNoOfSeatsFromTextFieldSearch() {
		return Integer.parseInt(textFieldSearch.getText());
	}
	
	private int getTableNoFromTextField() {
		return Integer.parseInt(textFieldTableNo.getText());
	}

	private int getNoOfPeopleFromTextField() {
		return Integer.parseInt(textFieldNoOfPeople.getText());
	}

	private boolean getChckbxOutside() {
		return chckbxOutside.isSelected();
	}
}