package gui.decoration;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.DecorationStatistics;

public class DecorationStatisticsListCellRenderer implements ListCellRenderer<DecorationStatistics>{

	private DefaultListCellRenderer dlcr;
	
	@Override
	public Component getListCellRendererComponent(JList<? extends DecorationStatistics> list, DecorationStatistics value, int index, boolean isSelected,
			boolean cellHasFocus) {
		dlcr = new DefaultListCellRenderer();
		String textToShow = ("Month: " + value.getMonth() + ",  Decoration needed: " + value.getAveragePerMonth());
		return dlcr.getListCellRendererComponent(list, textToShow, index, isSelected, cellHasFocus);
	}
}
