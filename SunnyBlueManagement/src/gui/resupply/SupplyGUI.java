package gui.resupply;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Choice;

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
import model.Item;
import model.LineItem;
import model.UrgencyEnum;
import gui.MainFrame;
import gui.item.ItemScrollPane;
import net.miginfocom.swing.MigLayout;

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
	private JTextField textFieldSearch;

	private ItemScrollPane scrollPane;
	private Choice choiceDepartments;
	private JTextField textFieldEnterQuantity;

	/**
	 * Create the panel.
	 */
	public SupplyGUI(final MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.supplyOrderController = new SupplyOrderController();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		constructLayeredPane();
		constructSupplyRestaurantPanel();
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
	
	private void constructSupplyRestaurantPanel() {
		supplyOrderController = new SupplyOrderController();
		supplyRestaurantPanel = new JPanel();
		layeredPane.add(supplyRestaurantPanel, "name_3150264217800");
		supplyRestaurantPanel.setLayout(new MigLayout("", "[173.00px][113.00px,center][][][][][]", "[14px][][][][][]"));

		JLabel lblResupplyHeader = new JLabel("Resupply ");
		lblResupplyHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		supplyRestaurantPanel.add(lblResupplyHeader, "cell 0 0,alignx left,aligny top");

		JLabel lblEnterProduct = new JLabel("Enter product:");
		supplyRestaurantPanel.add(lblEnterProduct, "flowx,cell 0 1,alignx left,aligny center");
		
		JLabel lblCategory = new JLabel("Filter:");
		supplyRestaurantPanel.add(lblCategory, "flowx,cell 1 1");

		JButton btnSelectItem = new JButton("Search");
		btnSelectItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itemController = new ItemController();
				readItems();
			}
		});
		supplyRestaurantPanel.add(btnSelectItem, "flowx,cell 5 1,growx,aligny bottom");

		scrollPane = new ItemScrollPane();
		supplyRestaurantPanel.add(scrollPane, "cell 0 2 6 1,grow");

		textFieldSearch = new JTextField();
		supplyRestaurantPanel.add(textFieldSearch, "cell 0 1,growx,aligny center");
		textFieldSearch.setColumns(10);

		JButton btnGoBack = new JButton("Back");
		btnGoBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//switchSupplyPanels(supplyMenuPanel);
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
		
		//TODO SHOW ENUMS IN THIS, MAKE A ANY CHOICE FOR ALL THE ENUMS TO BE SEARCHED
		choiceDepartments = new Choice();
		supplyRestaurantPanel.add(choiceDepartments, "cell 1 1");
		constructChoiceDepartment();	//initialize the various strings inside it

	}

	private void readItems() {
		Collection<Item> itemsFound;
		if (getDepartmentFromChoice().equals("any")) {
			itemsFound = itemController.readItemByName(getNameFromSearchTextField());
		} else {
			itemsFound = itemController.readItemByNameAndDepartment(getNameFromSearchTextField(), getDepartmentFromChoice());
		}
		scrollPane.updateList(itemsFound);
	}
	
	private void constructChoiceDepartment() {
		choiceDepartments.add("any");
		itemController = new ItemController();
		for(String department: itemController.getAllDepartmentTypes()) {
			choiceDepartments.add(department);
		}
	}
	
	private int getQuantityFromTextField() throws Exception {
		return Integer.parseInt(textFieldEnterQuantity.getText());
	}
	
	private String getNameFromSearchTextField() {
		return textFieldSearch.getText();
	}
	
	private String getDepartmentFromChoice() {
		//getItem can only return type of string, so it will have to get converted back to an enum elsewhere
		return choiceDepartments.getItem(choiceDepartments.getSelectedIndex());
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
		String messageToShow = createOrderSummary();
		JOptionPane.showMessageDialog(mainFrame, messageToShow);
		//TODO FIX URGENCY
		supplyOrderController.getSupplyOrder().setUrgencyEnum(UrgencyEnum.LOW);
		//TODO is this the best way?
		if (supplyOrderController.createSupplyOrder()) {
			supplyOrderController.emptySupplyOrder();
			JOptionPane.showMessageDialog(mainFrame, "Order was created");
		} else {
			supplyOrderController.emptySupplyOrder();
			JOptionPane.showMessageDialog(mainFrame, "Order creation failed");
		}
	}
	
	private String createOrderSummary() {
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
