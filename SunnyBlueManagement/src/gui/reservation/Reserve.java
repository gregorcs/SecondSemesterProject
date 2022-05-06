package gui.reservation;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.ReservationController;
import gui.MainFrame;
import net.miginfocom.swing.MigLayout;

public class Reserve extends JPanel {

	private static final long serialVersionUID = 69L;
	private MainFrame mainFrame;
	private ReservationController reservationController;
	private JLayeredPane layeredPane;
	private JPanel EnterDetailsPanel;
	private JPanel ChooseTablePanel;
	private JPanel SpecificRequirementsPanel;
	//Should Event panels be implemented here or in a separate class?
	private JTextField textField;
	
	//Panel creation
	
	public Reserve (final MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.reservationController = new ReservationController();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		layeredPane = new JLayeredPane();
		add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		//TODO verify parameters for alignment and positioning
		EnterDetailsPanel = new JPanel();
		layeredPane.add(EnterDetailsPanel, "name_28904848226400");
		EnterDetailsPanel.setLayout(new MigLayout("", "[89px, center][89px][][][][][][]", "[23px][][][][][][][][][]"));
		
		JLabel lblHeader = new JLabel("Enter Details");
		EnterDetailsPanel.add(lblHeader, "cell 0 0,alignx center");
		
		Component rigidArea = Box.createRigidArea(new Dimension(20,20));
		EnterDetailsPanel.add(rigidArea, "cell 0 1,alignx center");
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchReservePanels(ChooseTablePanel);
			}
		});
		EnterDetailsPanel.add(btnProceed, "cell 0 2, alignx center, aligny top");
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.backToMainMenu();
				
			}
		});
		
		EnterDetailsPanel.add(btnBack, "cell 7 9");
		
		//Table Selection panel
		ChooseTablePanel = new JPanel();
		layeredPane.add(ChooseTablePanel, "name_54378967892300");
		ChooseTablePanel.setLayout(new MigLayout("", "[173.00px][113.00px,center][][][][]", "[14px][][]"));
		
		JLabel lblChooseTableHeader = new JLabel("Choose a table");
		lblChooseTableHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		ChooseTablePanel.add(lblChooseTableHeader, "cell 0 0,alignx left,aligny top");
		
		//Maybe one more JLabel?
		
		JButton btnSelectTable = new JButton("Select");
		btnSelectTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ChooseTablePanel.add(btnSelectTable, "cell 1 1,alignx left,aligny bottom");
		
		JScrollPane scrollPane = new JScrollPane();
		ChooseTablePanel.add(scrollPane, "cell 0 2 5 1, grow");
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		textField = new JTextField();
		ChooseTablePanel.add(textField, "cell 0 1,growx,aligny center");
		textField.setColumns(10);
		
		JButton btnBackFromChooseTable = new JButton("Back");
		btnBackFromChooseTable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				switchReservePanels(EnterDetailsPanel);
			}
		});
		ChooseTablePanel.add(btnBackFromChooseTable, "cell 5 2,aligny bottom");
		//end of Table Selection
		
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
	
}
