package gui.decoration;

import java.awt.Choice;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import controller.DecorationController;
import controller.ItemController;
import model.Decoration;
public class DecorationScrollPane extends JScrollPane{

	private static final long serialVersionUID = -4773670531023046534L;

	private Choice choiceDepartments;
	private Choice choiceLowOrHigh;
	private enum SortStockEnum {LOWEST, HIGHEST;};
	
	private JList<Decoration> decorationList;
	private DefaultListModel<Decoration> listRepresentationDecoration;
	
	private DecorationController decorationController;
	private ItemController itemController;
	private JMenuBar menuBar;
	
	public DecorationScrollPane() {
		decorationController = new DecorationController();
		itemController = new ItemController();
		decorationList = new JList<Decoration>();
		setViewportView(decorationList);
		decorationList.setFixedCellHeight(20);
		constructScrollPaneHeader();
		initializeList();
	}
	
	public void initializeList() {
		DecorationListCellRenderer cellRenderer = new DecorationListCellRenderer();
		decorationList.setCellRenderer(cellRenderer);
		decorationController = new DecorationController();
		updateList(decorationController.readAllDecorations());
	}
	
	public void updateList(Collection<Decoration> listToShow) {
		listRepresentationDecoration = new DefaultListModel<Decoration>();
		
		for(Decoration item : listToShow) {
			listRepresentationDecoration.addElement(item);
		}
		decorationList.setModel(listRepresentationDecoration);
	}
	
	public Decoration getSelectedDecoration() {return decorationList.getSelectedValue();};
	
	public String getDepartmentFromChoice() {
		return choiceDepartments.getItem(choiceDepartments.getSelectedIndex()).toString();
	}
	
	public String getStockSortFromChoice() {
		return choiceLowOrHigh.getItem(choiceLowOrHigh.getSelectedIndex()).toString();
	}
	
	private void constructChoiceDepartment() {
		JLabel lblSortByDepartment = new JLabel("By department:  ");
		menuBar.add(lblSortByDepartment);
		choiceDepartments = new Choice();
		menuBar.add(choiceDepartments);
		choiceDepartments.add("any");
		for (String department : itemController.getAllDepartmentTypes()) {
			choiceDepartments.add(department);
		}
	}
	
	private void constructChoiceLowOrHigh() {
		JLabel lblSortByStock = new JLabel("By stock:  ");
		menuBar.add(lblSortByStock);
		choiceLowOrHigh = new Choice();
		for (SortStockEnum temp : SortStockEnum.values()) {
			choiceLowOrHigh.add(temp.toString());
		}
		menuBar.add(choiceLowOrHigh);
	}
	
	private void constructScrollPaneHeader() {
		menuBar = new JMenuBar();
		setColumnHeaderView(menuBar);
		constructChoiceDepartment();
		constructChoiceLowOrHigh();
		JToggleButton monthlyView = new JToggleButton("Monthly view");
		menuBar.add(monthlyView);
	}
}