package gui;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import controller.CheckConnectionController;
import gui.resupply.ResupplyGUI;

import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JPanel {

	private static final long serialVersionUID = -2547880461104225298L;
	private MainFrame mainFrame;
	private ResupplyGUI resupplyPanel;
	private JLabel lblConOutput;

	/**
	 * Create the panel.
	 */
	public MainMenu(final MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setBounds(100, 100, 1920, 1080);
		setLayout(new MigLayout("", "[][][center][]", "[][][][][][][][][]"));
		//setLayout(new MigLayout("align 50% 50%"));
		resupplyPanel = new ResupplyGUI(mainFrame);
		
		JLabel lblHeader = DefaultComponentFactory.getInstance().createLabel("Main menu");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblHeader, "cell 2 0");
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_2, "cell 2 1");
		
		JButton btnReserveTable = new JButton("Reserve Table");
		add(btnReserveTable, "cell 2 2");
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_1, "cell 2 3");
		
		JButton btnResupply = new JButton("Resupply ");
		btnResupply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.switchPanels(resupplyPanel);
			}
		});
		add(btnResupply, "cell 2 4");
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea, "cell 2 5");
		
		JLabel lblConnection = new JLabel("Connection:");
		add(lblConnection, "cell 0 6");
		CheckConnectionController connection = new CheckConnectionController(this);
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
