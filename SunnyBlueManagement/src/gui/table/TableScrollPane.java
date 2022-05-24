package gui.table;

import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import controller.TableController;
import model.ReservationFolder.Table;

public class TableScrollPane extends JScrollPane {
	
	private static final long serialVersionUID = -6447934041446584216L;
	
	private JList<Table> tableList;
	private DefaultListModel<Table> listRepresentationTable;
	
	private TableController tableController;
	
	public TableScrollPane() {
		tableList = new JList<Table>();
		setViewportView(tableList);
		tableList.setFixedCellHeight(20);
		
		tableController = new TableController();
		initializeList(tableController.readAllTables());
	}
	
	public void initializeList(Collection<Table> listToShow) {
		TableListCellRenderer cellRenderer = new TableListCellRenderer();
		tableList.setCellRenderer(cellRenderer);
		tableController = new TableController();
		updateListTable(tableController.readAllTables());
	}
	
	public void updateListTable(Collection<Table> listToShow) {
		listRepresentationTable = new DefaultListModel<Table>();
		
		for (Table table : listToShow) {
			listRepresentationTable.addElement(table);
		}
		tableList.setModel(listRepresentationTable);
	}
		
	public Table getSelectedTable() {return tableList.getSelectedValue();};
}