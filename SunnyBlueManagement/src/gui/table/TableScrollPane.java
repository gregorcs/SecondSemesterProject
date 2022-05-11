package gui.table;

import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import model.ReservationFolder.Table;

public class TableScrollPane extends JScrollPane{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6447934041446584216L;
	
	private JList<Table> tableList;
	private DefaultListModel<Table> listRepresentation;
	
	public TableScrollPane() {
		tableList = new JList<Table>();
		setViewportView(tableList);
		//setting style
		tableList.setFixedCellHeight(20);
		//TODO initialize list here
	}
	
	public void initializeList(Collection<Table> arrayToShow) {
		TableListCellRenderer cellRenderer = new TableListCellRenderer();
		tableList.setCellRenderer(cellRenderer);
		updateList(arrayToShow);
	}
	
	public void updateList(Collection<Table> arrayToShow) {
		listRepresentation = new DefaultListModel<Table>();
		
		for(Table table : arrayToShow) {
			listRepresentation.addElement(table);
		}
		tableList.setModel(listRepresentation);
	}
		
	public Table getSelectedProduct() {
		return tableList.getSelectedValue();
	}
}
