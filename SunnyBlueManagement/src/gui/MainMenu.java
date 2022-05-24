package gui;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import gui.resupply.DecorationGUI;

import gui.reservation.Reserve;

import gui.resupply.SupplyGUI;
import gui.table.TableGUI;

import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JPanel {

	private static final long serialVersionUID = -2547880461104225298L;

	private MainFrame mainFrame;
	private Reserve reservePanel;
	private SupplyGUI supplyPanel;
	private DecorationGUI decorationPanel;
	private TableGUI tablePanel;
	private JLabel lblConOutput;


	/**
	 * Create the panel.
	 */
	public MainMenu(final MainFrame mainFrame) {
		setBounds(100, 100, 1920, 1080);
		setLayout(new MigLayout("", "[grow][][][center][][][grow]", "[grow][][][][][][][][][][][grow]"));
		//setLayout(new MigLayout("align 50% 50%"));

		reservePanel = new Reserve(mainFrame);

		supplyPanel = new SupplyGUI(mainFrame);

		decorationPanel = new DecorationGUI();
		
		tablePanel = new TableGUI(mainFrame);
		
		JLabel lblHeader = DefaultComponentFactory.getInstance().createLabel("Main menu");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblHeader, "cell 3 1");
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_2, "flowx,cell 3 2");
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_1, "cell 3 5");
		
		JButton btnResupply = new JButton("Resupply ");
		btnResupply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.switchPanels(supplyPanel);
			}
		});
		add(btnResupply, "cell 3 4");
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea, "cell 3 7");
		
		JButton btnManageDecorations = new JButton("Manage Decorations");
		btnManageDecorations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.switchPanels(decorationPanel);
			}
		});
		add(btnManageDecorations, "cell 3 6");
		
		JButton btnManageTables = new JButton("Manage Tables");
		btnManageTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.switchPanels(tablePanel);
			}
		});
		add(btnManageTables, "cell 3 8");
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_3, "cell 3 9");
		
		JLabel lblConnection = new JLabel("Connection:");
		add(lblConnection, "cell 1 10");
		CheckConnectionWorker connection = new CheckConnectionWorker(this);
		connection.execute();
		
		lblConOutput = new JLabel("");
		add(lblConOutput, "cell 2 10");
		
		//It needs to go to the EnterDetails Panel instead - halp
		JButton btnReserveTable = new JButton("Reserve Table");
		
				add(btnReserveTable, "cell 3 3");
				btnReserveTable.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mainFrame.switchPanels(reservePanel);
					}
				});
				add(btnReserveTable, "cell 2 2");

	}
	
	public void updateConnectionOutput(boolean isConnected) {
		if (isConnected) {
			lblConOutput.setText("valid");
			lblConOutput.setForeground(Color.green);
		} else {
			lblConOutput.setText("failed");
			lblConOutput.setForeground(Color.red);
		}
		
	}
	
	public void refresh() {
		this.revalidate();
		this.repaint();
	}
}
