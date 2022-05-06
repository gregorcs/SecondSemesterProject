package gui;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import gui.reservation.Reserve;
import gui.resupply.Resupply;

import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JPanel implements Runnable {

	private static final long serialVersionUID = -2547880461104225298L;
	private MainFrame mainFrame;
	private Resupply resupplyPanel;
	private Reserve reservePanel;

	/**
	 * Create the panel.
	 */
	public MainMenu(final MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setBounds(100, 100, 1920, 1080);
		setLayout(new MigLayout("", "[][][center][]", "[][][][][][][][][]"));
		//setLayout(new MigLayout("align 50% 50%"));
		resupplyPanel = new Resupply(mainFrame);
		reservePanel = new Reserve(mainFrame);
		
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
				mainFrame.switchPanels(resupplyPanel);
			}
		});
		add(btnResupply, "cell 2 4");
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea, "cell 2 5");
		
		JLabel lblConnection = new JLabel("Connection");
		add(lblConnection, "cell 0 6");
		
		JLabel lblConnectionOutput = new JLabel("");
		add(lblConnectionOutput, "cell 1 8");

	}

	@Override
	public void run() {
		
		
	}
	
	
}
