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
import model.DepartmentEnum;
import model.Item;
import model.LineItem;
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
	}
	
	private void constructSupplyRestaurantPanel() {
		supplyRestaurantPanel = new JPanel();
		layeredPane.add(supplyRestaurantPanel, "name_3150264217800");
		supplyRestaurantPanel.setLayout(new MigLayout("", "[173.00px][113.00px,center][][][][][]", "[14px][][][][][]"));

		JLabel lblResupplyHeader = new JLabel("Resupply ");
		lblResupplyHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		supplyRestaurantPanel.add(lblResupplyHeader, "cell 0 0,alignx left,aligny top");

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
		
		JLabel lblCategory = new JLabel("Filter:");
		supplyRestaurantPanel.add(lblCategory, "cell 2 1");
		
		//TODO SHOW ENUMS IN THIS, MAKE A ANY CHOICE FOR ALL THE ENUMS TO BE SEARCHED
		choiceDepartments = new Choice();
		supplyRestaurantPanel.add(choiceDepartments, "cell 3 1");
		constructChoiceDepartment();	//initialize the various strings inside it

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

	}

	private void readItemsByName() {
		Collection<Item> itemsFound = itemController.readByNameItem(getNameFromSearchTextField());
		scrollPane.updateList(itemsFound);
	}
	
	private void constructChoiceDepartment() {
		for (DepartmentEnum tempEnum : DepartmentEnum.values()) {
			choiceDepartments.add(tempEnum.toString());
		}
	}

	private void constructResupplyKitchenPanel() {
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
