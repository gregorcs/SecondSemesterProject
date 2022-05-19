package gui.decoration;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;

import controller.DecorationController;
import controller.ItemController;

/**
 * This object displays any type of collection as long as you provide it 
 * a Collection<any type> with a listCellRenderer<any type> to go with it
 * @author Gregor
 *
 * @param <T> type the class should work with
 */
public class GenericScrollPane<T> extends JScrollPane{

	private static final long serialVersionUID = -4773670531023046534L;

	private Choice choiceDepartments;
	private Choice choiceLowOrHigh;
	private enum SortStockEnum {LOWEST, HIGHEST;};
	
	private JList<T> jlist;
	private DefaultListModel<T> listRepresentation;
	private ListCellRenderer<? super T> cellRenderer;
	
	private DecorationController decorationController;
	private ItemController itemController;
	private JMenuBar menuBar;
	
	/**
	 * constructor
	 * @param decorationsToShow collection of any type
	 * @param cellRenderToUse ListCellRenderer that can process the passed in decorationsToShow
	 */
	public GenericScrollPane(Collection<T> decorationsToShow, ListCellRenderer<? super T> cellRenderToUse) {
		decorationController = new DecorationController();
		itemController = new ItemController();
		jlist = new JList<T>();
		setViewportView(jlist);
		jlist.setFixedCellHeight(20);
		constructScrollPaneHeader();
		initializeList(decorationsToShow, cellRenderToUse);
	}
	
	public void initializeList(Collection<T> decorationsToShow, ListCellRenderer<? super T> cellRenderToUse) {
		this.cellRenderer = cellRenderToUse;
		jlist.setCellRenderer(cellRenderer);
		decorationController = new DecorationController();
		updateList(decorationsToShow);
	}
	
	public void updateList(Collection<T> listToShow) {
		listRepresentation = new DefaultListModel<T>();
		
		for(T item : listToShow) {
			listRepresentation.addElement(item);
		}
		jlist.setModel(listRepresentation);
	}

	public T getSelectedDecoration() {return jlist.getSelectedValue();};
	
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
		monthlyView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//switchToMonthlyView();
			}
		});
		menuBar.add(monthlyView);
	}
}
