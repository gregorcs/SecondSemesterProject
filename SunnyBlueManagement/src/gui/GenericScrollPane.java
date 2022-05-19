package gui;

import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

/**
 * This object displays any type of collection as long as you provide it a
 * Collection<any type> with a listCellRenderer<any type> to go with it
 * 
 *
 * @param <T> type the class should work with
 */
public class GenericScrollPane<T> extends JScrollPane{

	private static final long serialVersionUID = -4773670531023046534L;

	private JList<T> jlist;
	private DefaultListModel<T> listRepresentation;
	private ListCellRenderer<? super T> cellRenderer;
		
	/**
	 * constructor
	 * @param listToShow collection of any type
	 * @param cellRenderToUse ListCellRenderer that can process the passed in decorationsToShow
	 */
	public GenericScrollPane(Collection<T> listToShow, ListCellRenderer<? super T> cellRenderToUse) {
		jlist = new JList<T>();
		setViewportView(jlist);
		jlist.setFixedCellHeight(20);
		initializeList(listToShow, cellRenderToUse);
	}
	
	public void initializeList(Collection<T> listToShow, ListCellRenderer<? super T> cellRenderToUse) {
		this.cellRenderer = cellRenderToUse;
		jlist.setCellRenderer(cellRenderer);
		updateList(listToShow);
	}
	
	public void updateList(Collection<T> listToShow) {
		listRepresentation = new DefaultListModel<T>();
		
		for(T item : listToShow) {
			listRepresentation.addElement(item);
		}
		jlist.setModel(listRepresentation);
	}
	
	public void updateList(Collection<T> listToShow, ListCellRenderer<? super T> cellRendererToUse) {
		this.cellRenderer = cellRendererToUse;
		jlist.setCellRenderer(cellRenderer);
		updateList(listToShow);
	}

	public T getSelectedObj() {return jlist.getSelectedValue();};
}
