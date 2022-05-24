package gui.table;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.TableController;
import gui.MainFrame;
import model.ReservationFolder.Table;
import net.miginfocom.swing.MigLayout;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class TableGUI extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLayeredPane layeredPane;
	private JTextField textFieldSearch;
	private JPanel tablePanel;
	
	private TableScrollPane tableScrollPane;
	
	private TableController tableController;
	private JRadioButton rdbtnIsInside;
	private JRadioButton rdbtnIsOutside;
	private JRadioButton rdbtnNoPreference;
	private JButton btnDeleteTable;
	private JButton btnBackToMainMenu;
	private JButton btnNewButton;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel;
	private JLabel lblTableNo;
	private JLabel lblNoOfPeople;
	private JLabel lblOutside;
	private JCheckBox chckbxOutside;
	
	public TableGUI(final MainFrame mainFrame) {
		setBounds(100, 100, 1920, 1080);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		constructLayeredPane();
	}
	
	private void constructLayeredPane() {
		layeredPane = new JLayeredPane();
		add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		constructTablePanel();
	}
	
	private void constructTablePanel() {
		tablePanel = new JPanel();
		layeredPane.add(tablePanel, "name_3150264217800");
		tablePanel.setLayout(new MigLayout("", "[grow][173.00px][pref!,grow][][113.00px,center][][][][][][][77.00][163.00][200.00,grow]", "[grow][][][14px][][][][41.00][40.00][39.00][36.00][36.00][][][][][][][][grow]"));

		JLabel lblTableManagementHeader = new JLabel("Table Management");
		lblTableManagementHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		tablePanel.add(lblTableManagementHeader, "cell 1 1 12 1,alignx center,aligny top");
		
		btnBackToMainMenu = new JButton("Back");
		tablePanel.add(btnBackToMainMenu, "cell 6 4,alignx center,aligny baseline");
		
		tableScrollPane = new TableScrollPane();
		tablePanel.add(tableScrollPane, "cell 1 7 10 9,grow");
				
				rdbtnIsInside = new JRadioButton("Indoors Table");
				tablePanel.add(rdbtnIsInside, "cell 2 4");
				
				rdbtnIsOutside = new JRadioButton("Outdoors Table");
				tablePanel.add(rdbtnIsOutside, "cell 3 4,aligny top");
				
				rdbtnNoPreference = new JRadioButton("No Preference");
				rdbtnNoPreference.setSelected(true);
				tablePanel.add(rdbtnNoPreference, "cell 4 4");
				
				ButtonGroup tableLocation = new ButtonGroup();
				tableLocation.add(rdbtnIsInside);
				tableLocation.add(rdbtnIsOutside);
				tableLocation.add(rdbtnNoPreference);
		
				JLabel lblEnterTableNo = new JLabel("Enter table number:");
				tablePanel.add(lblEnterTableNo, "flowx,cell 1 5,alignx trailing,aligny center");
						
				textFieldSearch = new JTextField();
				tablePanel.add(textFieldSearch, "cell 2 5 2 1,growx");
				textFieldSearch.setColumns(5);
								
				JButton btnSelectTable = new JButton("Search");
				btnSelectTable.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					readTables();
				}
				});
				tablePanel.add(btnSelectTable, "flowx,cell 4 5,growx,aligny bottom");
				
				btnDeleteTable = new JButton("Remove");
				tablePanel.add(btnDeleteTable, "cell 0 8,alignx right,aligny baseline");
				btnDeleteTable.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						deleteTable();
					}
				});
				
				lblNewLabel = new JLabel("Table Creation");
				tablePanel.add(lblNewLabel, "cell 12 8,alignx center");
				
				lblTableNo = new JLabel("Table Number");
				tablePanel.add(lblTableNo, "cell 11 9,alignx center");
				
				textField_1 = new JTextField();
				tablePanel.add(textField_1, "cell 12 9,growx");
				textField_1.setColumns(5);
				
				lblNoOfPeople = new JLabel("Number of Persons");
				tablePanel.add(lblNoOfPeople, "cell 11 10,alignx center");
				
				textField_2 = new JTextField();
				tablePanel.add(textField_2, "cell 12 10,growx");
				textField_2.setColumns(10);
				
				lblOutside = new JLabel("Location");
				tablePanel.add(lblOutside, "cell 11 11,alignx center");
				
				chckbxOutside = new JCheckBox("Outside");
				tablePanel.add(chckbxOutside, "cell 12 11");
				
				btnNewButton = new JButton("Create");
				tablePanel.add(btnNewButton, "cell 12 13,alignx center");
	}
	
	private void readTables() {
		Collection<Table> tablesFound = tableController.readAllTables();
		
		if (!textFieldSearch.getText().equals("")) {
			tablesFound.add(tableController.readTable(getTableNoFromTextField()));
		}
		
		if (rdbtnIsOutside.isSelected()) {
			tablesFound = tableController.readTableByIsOutside(true);
		} else if (rdbtnIsInside.isSelected()) {
			tablesFound = tableController.readTableByIsOutside(false);
		}
		tableScrollPane.updateListTable(tablesFound);
	}
	
	private void deleteTable() {
		Table table = tableScrollPane.getSelectedTable();
		tableController.deleteTable(table);
	}
	
	private int getTableNoFromTextField() {
		return Integer.parseInt(textFieldSearch.getText());
	}
}