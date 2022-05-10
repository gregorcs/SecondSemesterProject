package gui.resupply;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.SupplyOrderController;
import gui.MainFrame;
import gui.item.ItemScrollPane;
import model.LineItem;
import net.miginfocom.swing.MigLayout;

public class ResupplyGUI extends JPanel {

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

	private ItemScrollPane scrollPane;
	private JTextField textFieldEnterQuantity;

	/**
	 * Create the panel.
	 */
	public ResupplyGUI(final MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.supplyOrderController = new SupplyOrderController();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		constructLayeredPane();
		constructResupplyMenuPanel();
		constructResupplyRestaurantPanel();
		constructResupplyKitchenPanel();

	}

	public void switchResupplyPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	private void constructLayeredPane() {
		layeredPane = new JLayeredPane();
		add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
	}
	
	private void constructResupplyMenuPanel() {
		ResupplyMenuPanel = new JPanel();
		layeredPane.add(ResupplyMenuPanel, "name_2798950394800");
		ResupplyMenuPanel.setLayout(new MigLayout("align 50% 50%", "[89px,center][89px][][][][][][]", "[23px][][][][][][][][][]"));

		JLabel lblHeader = new JLabel("Resupply Menu");
		ResupplyMenuPanel.add(lblHeader, "cell 0 0,alignx center");

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		ResupplyMenuPanel.add(rigidArea, "cell 0 1,alignx center");

		JButton btnResupplyRestaurant = new JButton("Resupply restaurant");
		btnResupplyRestaurant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchResupplyPanels(ResupplyRestaurantPanel);
			}
		});
		ResupplyMenuPanel.add(btnResupplyRestaurant, "cell 0 2,alignx center,aligny top");

		JButton btnResupplyKitchen = new JButton("Resupply kitchen");
		ResupplyMenuPanel.add(btnResupplyKitchen, "cell 0 3,alignx center,aligny top");

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.backToMainMenu();
			}
		});
		ResupplyMenuPanel.add(btnBack, "cell 7 9");
	}
	
	private void constructResupplyRestaurantPanel() {
		ResupplyRestaurantPanel = new JPanel();
		layeredPane.add(ResupplyRestaurantPanel, "name_3150264217800");
		ResupplyRestaurantPanel.setLayout(new MigLayout("align 50% 50%", "[173.00px][113.00px,center][][][][]", "[14px][][][][][]"));

		JLabel lblResupplyRestaurantHeader = new JLabel("Resupply Restaurant");
		lblResupplyRestaurantHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		ResupplyRestaurantPanel.add(lblResupplyRestaurantHeader, "cell 0 0,alignx left,aligny top");

		JLabel lblEnterProduct = new JLabel("Enter product:");
		ResupplyRestaurantPanel.add(lblEnterProduct, "flowx,cell 0 1,alignx left,aligny center");

		JButton btnSelectItem = new JButton("Select");
		btnSelectItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		ResupplyRestaurantPanel.add(btnSelectItem, "cell 1 1,alignx left,aligny bottom");

		scrollPane = new ItemScrollPane();
		ResupplyRestaurantPanel.add(scrollPane, "cell 0 2 5 1,grow");

		textField = new JTextField();
		ResupplyRestaurantPanel.add(textField, "cell 0 1,growx,aligny center");
		textField.setColumns(10);

		JButton btnGoBack = new JButton("Back");
		btnGoBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchResupplyPanels(ResupplyMenuPanel);
			}
		});

		JLabel lblEnterQuantity = new JLabel("Enter quantity:");
		ResupplyRestaurantPanel.add(lblEnterQuantity, "flowx,cell 0 3");
		
		JButton btnAddItem = new JButton("Add");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addLineItems();
			}
		});
		ResupplyRestaurantPanel.add(btnAddItem, "cell 4 3,growx,aligny bottom");
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finalizeOrder();
			}
		});
		ResupplyRestaurantPanel.add(btnProceed, "cell 4 5");

		ResupplyRestaurantPanel.add(btnGoBack, "cell 5 5,growx,aligny bottom");

		textFieldEnterQuantity = new JTextField();
		ResupplyRestaurantPanel.add(textFieldEnterQuantity, "cell 0 3");
		textFieldEnterQuantity.setColumns(10);

	}

	private void constructResupplyKitchenPanel() {
		ResupplyKitchenPanel = new JPanel();
		layeredPane.add(ResupplyKitchenPanel, "name_3157677484000");
	}
	
	private int getQuantityFromTextField() throws Exception {
		return Integer.parseInt(textFieldEnterQuantity.getText());
	}
	
	private void addLineItems() {
		LineItem lineItem = null;
		// TODO Ask for quantity and make a lineItem
		// TODO Maybe move LineItem into a controller so GUI doesn't see the model
		// TODO Put into separate method
		try {
			int quantity = getQuantityFromTextField();
			if (quantity > 0) {
				lineItem = new LineItem(quantity, scrollPane.getSelectedItem());
				supplyOrderController.getSupplyOrder().addLineItem(lineItem);
			} else {throw new Exception();}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(mainFrame, "Item wasn't added, check your quantity");
		}
	
		if (lineItem != null) {
			JOptionPane.showMessageDialog(mainFrame, "Added item successfully" 
													+ System.lineSeparator() 
													+ "Name: " + lineItem.getItem().getName()
													+ System.lineSeparator()
													+ "Quantity: " + lineItem.getQuantity());
		}
	}
	
	private void finalizeOrder() {	
		String messageToShow = createOrderReceipt();
		JOptionPane.showMessageDialog(mainFrame, messageToShow);
		boolean orderSuccess = true;
		supplyOrderController.getSupplyOrder().setUrgency("low");
		try {
			supplyOrderController.createSupplyOrder();
		} catch (Exception e) {
			orderSuccess = false;
			JOptionPane.showMessageDialog(mainFrame, "Order creation failed");
			e.printStackTrace();
		}
		if (orderSuccess) {
			supplyOrderController.emptySupplyOrder();
			JOptionPane.showMessageDialog(mainFrame, "Order was created");
		}
	}
	
	private String createOrderReceipt() {
		String messageToShow = "";
		if (!supplyOrderController.getSupplyOrder().getListOfItems().isEmpty()) {
			messageToShow += "Your order:";
			for (LineItem lineItem : supplyOrderController.getSupplyOrder().getListOfItems()) {
				messageToShow += System.lineSeparator() 
				+ "Name: " + lineItem.getItem().getName()
				+ System.lineSeparator()
				+ "Quantity: " + lineItem.getQuantity();
			}
		} else {
			messageToShow += "Your order is empty";
		}
		return messageToShow;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
