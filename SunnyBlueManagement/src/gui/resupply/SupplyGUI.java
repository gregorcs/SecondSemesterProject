package gui.resupply;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.*;
import java.util.Collection;

import javax.swing.*;

import controller.ItemController;
import controller.SupplyOrderController;
import model.Item;
import model.LineItem;
import model.UrgencyEnum;
import gui.MainFrame;
import gui.item.ItemScrollPane;
import net.miginfocom.swing.MigLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;

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

	private ItemScrollPane itemScrollPane;
	private JTextField textFieldEnterQuantity;

	/**
	 * Create the panel.
	 */
	public SupplyGUI(final MainFrame mainFrame) {
		setBounds(100, 100, 1920, 1080);
		this.mainFrame = mainFrame;
		this.supplyOrderController = new SupplyOrderController();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		constructLayeredPane();
		constructSupplyRestaurantPanel();
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
		supplyRestaurantPanel.setLayout(new MigLayout("", "[grow][173.00px][113.00px,center][][][][][][][][][grow]", "[grow][][][14px][][][][41.00][40.00][39.00][36.00][68.00][][][][][][][grow]"));

		JLabel lblResupplyHeader = new JLabel("Resupply ");
		lblResupplyHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		supplyRestaurantPanel.add(lblResupplyHeader, "cell 1 1 10 1,alignx center,aligny top");
		
				JLabel lblEnterProduct = new JLabel("Enter product:");
				supplyRestaurantPanel.add(lblEnterProduct, "flowx,cell 1 5,alignx left,aligny center");
		
				JButton btnSelectItem = new JButton("Search");
				btnSelectItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						itemController = new ItemController();
						readItems();
					}
				});
				supplyRestaurantPanel.add(btnSelectItem, "flowx,cell 2 5,growx,aligny bottom");
		
		JButton btnCheckOrder = new JButton("Check order");
		supplyRestaurantPanel.add(btnCheckOrder, "cell 4 5,alignx center,aligny center");
		btnCheckOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mainFrame, createOrderSummary());
			}
		});

		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		supplyRestaurantPanel.add(rigidArea_1, "cell 1 6");

		itemScrollPane = new ItemScrollPane();
		supplyRestaurantPanel.add(itemScrollPane, "cell 1 7 10 9,grow");

		JButton btnGoBack = new JButton("Back");
		btnGoBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.backToMainMenu();
			}
		});

		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finalizeOrder();
			}
		});

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		supplyRestaurantPanel.add(rigidArea, "cell 6 16");

		JLabel lblEnterQuantity = new JLabel("Enter quantity:");
		supplyRestaurantPanel.add(lblEnterQuantity, "flowx,cell 1 17");

		textFieldEnterQuantity = new JTextField();
		supplyRestaurantPanel.add(textFieldEnterQuantity, "cell 1 17,growx");
		textFieldEnterQuantity.setColumns(10);

		JButton btnAddItem = new JButton("Add to order");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addLineItems();
			}
		});
		supplyRestaurantPanel.add(btnAddItem, "cell 2 17,growx,aligny bottom");
		
		JButton btnRemoveFromOrder = new JButton("Remove from order");
		supplyRestaurantPanel.add(btnRemoveFromOrder, "cell 4 17");
		btnRemoveFromOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeLineItems();
			}
		});
		
		supplyRestaurantPanel.add(btnProceed, "cell 9 17");

		supplyRestaurantPanel.add(btnGoBack, "cell 10 17,growx,aligny bottom");
		
				textFieldSearch = new JTextField();
				supplyRestaurantPanel.add(textFieldSearch, "cell 1 5,growx,aligny center");
				textFieldSearch.setColumns(10);
		
	}

	private void readItems() {
		Collection<Item> itemsFound;
		if (itemScrollPane.getDepartmentFromChoice().equals("any")) {
			itemsFound = itemController.readItemByName(getNameFromSearchTextField());
		} else {
			itemsFound = itemController.readItemByNameAndDepartment(getNameFromSearchTextField(),
					itemScrollPane.getDepartmentFromChoice());
		}
		itemScrollPane.updateListItem(itemsFound);
	}

	private int getQuantityFromTextField() throws Exception {
		return Integer.parseInt(textFieldEnterQuantity.getText());
	}

	private String getNameFromSearchTextField() {
		return textFieldSearch.getText();
	}

	private void addLineItems() {
		LineItem lineItem = null;
		// TODO Maybe move LineItem into a controller so GUI doesn't see the model
		// TODO Put into separate method
		try {
			int quantity = getQuantityFromTextField();
			if (quantity > 0) {
				lineItem = new LineItem(quantity, itemScrollPane.getSelectedItem());
				supplyOrderController.getSupplyOrder().addLineItem(lineItem);
			} else {
				throw new Exception();
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(mainFrame, "Unable to add item, please check quantity");
		}

		if (lineItem != null) {
			JOptionPane.showMessageDialog(mainFrame, "Item added successfully"
										 + System.lineSeparator() + "Name: " + lineItem.getItem().getName()
										 + System.lineSeparator() + "Quantity: " + lineItem.getQuantity());
		}
	}
	
	private void removeLineItems() {
		LineItem lineItem = null;
		try {
			int quantity = getQuantityFromTextField();
			if (quantity > 0) {
				int itemId = itemScrollPane.getSelectedItem().getItemId();
				lineItem = supplyOrderController.getSupplyOrder().getLineItemById(itemId);
				supplyOrderController.getSupplyOrder().removeLineItem(lineItem, quantity);
				
				if (lineItem.getQuantity() < quantity) {
					JOptionPane.showMessageDialog(mainFrame, "Unable to remove item, please check quantity");
				}
				
				if (lineItem != null) {
					JOptionPane.showMessageDialog(mainFrame, quantity + " item(s) removed successfully");
				}
			} else {
			throw new Exception();
			} 
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(mainFrame, "Unable to remove item, please check quantity");
		}
	}

	private void finalizeOrder() {
		String messageToShow = createOrderSummary();
		JOptionPane.showMessageDialog(mainFrame, messageToShow);
		UrgencyEnum selectedUrgency = createUrgencyDialog();
		supplyOrderController.getSupplyOrder().setUrgencyEnum(selectedUrgency);
		// TODO is this the best way?
		if (supplyOrderController.createSupplyOrder()) {
			supplyOrderController.emptySupplyOrder();
			JOptionPane.showMessageDialog(mainFrame, "Order was created");
		} else {
			supplyOrderController.emptySupplyOrder();
			JOptionPane.showMessageDialog(mainFrame, "Order creation failed");
		}
	}

	private UrgencyEnum createUrgencyDialog() {
		JList<UrgencyEnum> listOfUrgencyEnums = new JList<UrgencyEnum>(UrgencyEnum.values());
		JOptionPane.showMessageDialog(null, listOfUrgencyEnums, "Urgency of your order", JOptionPane.PLAIN_MESSAGE);
		return listOfUrgencyEnums.getSelectedValue();
	}

	private String createOrderSummary() {
		String messageToShow = "";
		if (!supplyOrderController.getSupplyOrder().getListOfItems().isEmpty()) {
			messageToShow += "Your order:";
			for (LineItem lineItem : supplyOrderController.getSupplyOrder().getListOfItems()) {
				messageToShow += System.lineSeparator() + "Name: " + lineItem.getItem().getName()
							  + System.lineSeparator() + "Quantity: " + lineItem.getQuantity()
							  + System.lineSeparator() + "ID: " + lineItem.getItem().getItemId()
							  + System.lineSeparator();
			}
		} else {
			messageToShow += "Your order is empty";
		}
		return messageToShow;
	}
	
	private void refresh() {
		this.revalidate();
		this.repaint();
	}
}