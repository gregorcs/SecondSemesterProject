package gui;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import gui.reservation.Reserve;

import gui.resupply.SupplyGUI;

import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JPanel {

	private static final long serialVersionUID = -2547880461104225298L;

	private MainFrame mainFrame;
	private Reserve reservePanel;
	private SupplyGUI supplyPanel;
	private JLabel lblConOutput;


	/**
	 * Create the panel.
	 */
	public MainMenu(final MainFrame mainFrame) {
		setBounds(100, 100, 1920, 1080);
		setLayout(new MigLayout("align 50% 50%", "[][][center][]", "[][][][][][][][][]"));
		//setLayout(new MigLayout("align 50% 50%"));

		reservePanel = new Reserve(mainFrame);

		supplyPanel = new SupplyGUI(mainFrame);

		
		JLabel lblHeader = DefaultComponentFactory.getInstance().createLabel("Main menu");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblHeader, "cell 2 0");
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_2, "cell 2 1");
		
		//It needs to go to the EnterDetails Panel instead - halp
		JButton btnReserveTable = new JButton("Reserve Table");
		btnReserveTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.switchPanels(reservePanel);
			}
		});
		add(btnReserveTable, "cell 2 2");
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_1, "cell 2 3");
		
		JButton btnResupply = new JButton("Resupply ");
		btnResupply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.switchPanels(supplyPanel);
			}
		});
		add(btnResupply, "cell 2 4");
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea, "cell 2 5");
		
		JLabel lblConnection = new JLabel("Connection:");
		add(lblConnection, "cell 0 6");
		CheckConnectionWorker connection = new CheckConnectionWorker(this);
		connection.execute();
		
		lblConOutput = new JLabel("");
		add(lblConOutput, "cell 1 6");

	}
	
	public void updateConnectionOutput(String status) {
		lblConOutput.setText(status);
	}
	
	public void refresh() {
		this.revalidate();
		this.repaint();
	}
}
