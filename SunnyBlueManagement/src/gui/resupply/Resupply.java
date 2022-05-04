package gui.resupply;

import javax.swing.JPanel;

import controller.SupplyOrderController;
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
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Font;

public class Resupply extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7068038639929039542L;
	private MainFrame mainFrame;
	private SupplyOrderController supplyOrderController;
	private JLayeredPane layeredPane;
	private JPanel ResupplyRestaurantPanel;
	private JPanel ResupplyKitchenPanel;
	private JPanel ResupplyMenuPanel;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public Resupply(final MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.supplyOrderController = new SupplyOrderController();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		layeredPane = new JLayeredPane();
		add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		ResupplyMenuPanel = new JPanel();
		layeredPane.add(ResupplyMenuPanel, "name_2798950394800");
		ResupplyMenuPanel.setLayout(new MigLayout("", "[89px,center][89px][][][][][][]", "[23px][][][][][][][][][]"));
		
		JLabel lblHeader = new JLabel("Resupply Menu");
		ResupplyMenuPanel.add(lblHeader, "cell 0 0,alignx center");
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		ResupplyMenuPanel.add(rigidArea, "cell 0 1,alignx center");
		
		JButton btnResupplyRestaurant = new JButton("Resupply restaurant");
		btnResupplyRestaurant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchResupplyPanels(ResupplyRestaurantPanel);
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
		ResupplyRestaurantPanel.setLayout(new MigLayout("", "[173.00px][113.00px,center][][][][]", "[14px][][]"));
		
		JLabel lblResupplyRestaurantHeader = new JLabel("Resupply Restaurant");
		lblResupplyRestaurantHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		ResupplyRestaurantPanel.add(lblResupplyRestaurantHeader, "cell 0 0,alignx left,aligny top");
		
		JLabel lblNewLabel_1 = new JLabel("Enter product:");
		ResupplyRestaurantPanel.add(lblNewLabel_1, "flowx,cell 0 1,alignx left,aligny center");
		
		JButton btnSelectItem = new JButton("Select");
		btnSelectItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		ResupplyRestaurantPanel.add(btnSelectItem, "cell 1 1,alignx left,aligny bottom");
		
		JScrollPane scrollPane = new JScrollPane();
		ResupplyRestaurantPanel.add(scrollPane, "cell 0 2 5 1,grow");
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		textField = new JTextField();
		ResupplyRestaurantPanel.add(textField, "cell 0 1,growx,aligny center");
		textField.setColumns(10);
		
		JButton btnBackFromRestaurant = new JButton("Back");
		btnBackFromRestaurant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchResupplyPanels(ResupplyMenuPanel);
			}
		});
		ResupplyRestaurantPanel.add(btnBackFromRestaurant, "cell 5 2,aligny bottom");
		
		ResupplyKitchenPanel = new JPanel();
		layeredPane.add(ResupplyKitchenPanel, "name_3157677484000");

	}
	
	public void switchResupplyPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

}
