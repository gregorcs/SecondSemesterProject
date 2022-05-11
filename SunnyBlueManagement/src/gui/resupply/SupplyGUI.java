package gui.resupply;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ItemController;
import controller.SupplyOrderController;
import gui.MainFrame;
import gui.item.ItemScrollPane;
import model.Item;
import model.LineItem;
import net.miginfocom.swing.MigLayout;
import java.awt.Choice;

public class SupplyGUI extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = -7068038639929039542L;
	private MainFrame mainFrame;
	private SupplyOrderController supplyOrderController;
	private ItemController itemController;
	private JLayeredPane layeredPane;
	private JPanel supplyRestaurantPanel;
	private JPanel ResupplyKitchenPanel;
	private JPanel supplyMenuPanel;
	private JTextField textFieldSearch;

	private ItemScrollPane scrollPane;
	private JTextField textFieldEnterQuantity;

	/**
	 * Create the panel.
	 */
	public SupplyGUI(final MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.supplyOrderController = new SupplyOrderController();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		constructLayeredPane();
		constructSupplyMenuPanel();
		constructSupplyRestaurantPanel();
		constructResupplyKitchenPanel();

	}

	public void switchSupplyPanels(JPanel panel) {
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
	
	private void constructSupplyMenuPanel() {
		supplyMenuPanel = new JPanel();
		layeredPane.add(supplyMenuPanel, "name_2798950394800");
		supplyMenuPanel.setLayout(new MigLayout("", "[89px,center][89px][][][][][][]", "[23px][][][][][][][][][]"));

		JLabel lblHeader = new JLabel("Resupply Menu");
		supplyMenuPanel.add(lblHeader, "cell 0 0,alignx center");

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		supplyMenuPanel.add(rigidArea, "cell 0 1,alignx center");

		JButton btnResupplyRestaurant = new JButton("Resupply restaurant");
		btnResupplyRestaurant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchSupplyPanels(supplyRestaurantPanel);
			}
		});
		supplyMenuPanel.add(btnResupplyRestaurant, "cell 0 2,alignx center,aligny top");

		JButton btnResupplyKitchen = new JButton("Resupply kitchen");
		supplyMenuPanel.add(btnResupplyKitchen, "cell 0 3,alignx center,aligny top");

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.backToMainMenu();
			}
		});
		supplyMenuPanel.add(btnBack, "cell 7 9");
	}
	
	private void constructSupplyRestaurantPanel() {
		supplyRestaurantPanel = new JPanel();
		layeredPane.add(supplyRestaurantPanel, "name_3150264217800");
		supplyRestaurantPanel.setLayout(new MigLayout("", "[173.00px][113.00px,center][][][][][]", "[14px][][][][][]"));

		JLabel lblResupplyRestaurantHeader = new JLabel("Resupply Restaurant");
		lblResupplyRestaurantHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		supplyRestaurantPanel.add(lblResupplyRestaurantHeader, "cell 0 0,alignx left,aligny top");

		JLabel lblEnterProduct = new JLabel("Enter product:");
		supplyRestaurantPanel.add(lblEnterProduct, "flowx,cell 0 1,alignx left,aligny center");

		JButton btnSelectItem = new JButton("Search");
		btnSelectItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemController = new ItemController();
				readItemsByName();
			}
		});
		supplyRestaurantPanel.add(btnSelectItem, "flowx,cell 1 1,alignx left,aligny bottom");
		
		JLabel lblCategory = new JLabel("In department:");
		supplyRestaurantPanel.add(lblCategory, "cell 2 1");
		
		//TODO SHOW ENUMS IN THIS, MAKE A ANY CHOICE FOR ALL THE ENUMS TO BE SEARCHED
		Choice choiceDepartments = new Choice();
		supplyRestaurantPanel.add(choiceDepartments, "cell 3 1");

		scrollPane = new ItemScrollPane();
		supplyRestaurantPanel.add(scrollPane, "cell 0 2 6 1,grow");

		textFieldSearch = new JTextField();
		supplyRestaurantPanel.add(textFieldSearch, "cell 0 1,growx,aligny center");
		textFieldSearch.setColumns(10);

		JButton btnGoBack = new JButton("Back");
		btnGoBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchSupplyPanels(supplyMenuPanel);
			}
		});
		
		JButton btnAddItem = new JButton("Add");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addLineItems();
			}
		});
		supplyRestaurantPanel.add(btnAddItem, "cell 5 3,growx,aligny bottom");
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finalizeOrder();
			}
		});
		
				JLabel lblEnterQuantity = new JLabel("Enter quantity:");
				supplyRestaurantPanel.add(lblEnterQuantity, "flowx,cell 0 4");
		supplyRestaurantPanel.add(btnProceed, "cell 5 5");

		supplyRestaurantPanel.add(btnGoBack, "cell 6 5,growx,aligny bottom");
				
						textFieldEnterQuantity = new JTextField();
						supplyRestaurantPanel.add(textFieldEnterQuantity, "cell 0 4");
						textFieldEnterQuantity.setColumns(10);

	}

	private void readItemsByName() {
		Collection<Item> itemsFound = itemController.readByNameItem(getNameFromSearchTextField());
		scrollPane.updateList(itemsFound);
	}

	private void constructResupplyKitchenPanel() {
		ResupplyKitchenPanel = new JPanel();
		layeredPane.add(ResupplyKitchenPanel, "name_3157677484000");
	}
	
	private int getQuantityFromTextField() throws Exception {
		return Integer.parseInt(textFieldEnterQuantity.getText());
	}
	
	private String getNameFromSearchTextField() {
		return textFieldSearch.getText();
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
