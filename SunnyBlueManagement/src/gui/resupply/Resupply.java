package gui.resupply;

import javax.swing.JPanel;

import controller.ResupplyOrderController;
import gui.MainFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Resupply extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7068038639929039542L;
	private MainFrame mainFrame;
	private ResupplyOrderController resupplyOrderController;
	private JLayeredPane layeredPane;
	private JPanel ResupplyRestaurantPanel;
	private JPanel ResupplyKitchenPanel;

	/**
	 * Create the panel.
	 */
	public Resupply(final MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.resupplyOrderController = new ResupplyOrderController();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		layeredPane = new JLayeredPane();
		add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel ResupplyMenuPanel = new JPanel();
		layeredPane.add(ResupplyMenuPanel, "name_2798950394800");
		ResupplyMenuPanel.setLayout(new MigLayout("", "[89px,center][89px][][][][][][]", "[23px][][][][][][][][][]"));
		
		JLabel lblHeader = new JLabel("Resupply Menu");
		ResupplyMenuPanel.add(lblHeader, "cell 0 0,alignx center");
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		ResupplyMenuPanel.add(rigidArea, "cell 0 1,alignx center");
		
		JButton btnResupplyRestaurant = new JButton("Resupply restaurant");
		btnResupplyRestaurant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(ResupplyRestaurantPanel);
			}
		});
		ResupplyMenuPanel.add(btnResupplyRestaurant, "cell 0 2,alignx center,aligny top");
		
		JButton btnResupplyKitchen = new JButton("Resupply kitchen");
		ResupplyMenuPanel.add(btnResupplyKitchen, "cell 0 3,alignx center,aligny top");
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.backToMainMenu();
			}
		});
		ResupplyMenuPanel.add(btnBack, "cell 7 9");
		
		ResupplyRestaurantPanel = new JPanel();
		layeredPane.add(ResupplyRestaurantPanel, "name_3150264217800");
		
		ResupplyKitchenPanel = new JPanel();
		layeredPane.add(ResupplyKitchenPanel, "name_3157677484000");

	}
	
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

}
