package gui.resupply;

import javax.swing.JPanel;

import controller.DecorationController;
import gui.MainFrame;
import gui.decoration.DecorationScrollPane;
import model.Decoration;
import net.miginfocom.swing.MigLayout;

import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.awt.event.ActionEvent;

public class DecorationGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4802415561708298203L;
	private MainFrame mainFrame;
	private DecorationScrollPane list;
	private DecorationController decorationController;
	
	public DecorationGUI(final MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setBounds(100, 100, 1920, 1080);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		decorationController = new DecorationController();
		
		JLayeredPane layeredPane = new JLayeredPane();
		add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("", "[grow][][][56.00][87.00,grow][][][][grow]", "[grow][][][grow][][][][][][grow]"));
		layeredPane.add(panel, "name_97268705302100");
		
		JLabel lblHeader = new JLabel("Decoration management");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblHeader, "cell 1 1 6 1,alignx center,aligny center");
		
		list = new DecorationScrollPane();
		panel.add(list, "cell 1 3 7 5,grow");
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decorationController = new DecorationController();
				Collection<Decoration> decorationsFound;
				decorationsFound = decorationController.readAllSwitch(list.getDepartmentFromChoice(), list.getStockSortFromChoice());
				list.updateList(decorationsFound);
			}
		});
		panel.add(btnSearch, "cell 1 8");
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.backToMainMenu();
			}
		});
		panel.add(btnBack, "cell 8 9,alignx right,aligny bottom");
		
	}
	
	public void refresh() {
		this.revalidate();
		this.repaint();
	}

}
