package gui.supply;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.*;

import controller.ItemController;
import controller.SupplyOrderController;

import model.Item;
import model.LineItem;
import model.ParametersSortEnum;
import model.SupplyOrder;
import model.UrgencyEnum;

import gui.GenericScrollPane;
import gui.MainFrame;
import gui.item.ItemListCellRenderer;

import net.miginfocom.swing.MigLayout;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Choice;

public class SupplyGUI extends JPanel {

	private static final long serialVersionUID = -7068038639929039542L;
	
	private MainFrame mainFrame;
	
	private SupplyOrderController supplyOrderController;
	private ItemController itemController;
	
	private JLayeredPane layeredPane;
	private JPanel supplyRestaurantPanel;
	
	private JTextField textFieldSearch;
	private JTextField textFieldEnterQuantity;

	private Choice choiceLowOrHigh;

	private GenericScrollPane<Item> itemScrollPane;
	private GenericScrollPane<SupplyOrder> supplyOrderScrollPane;

	/**
	 * Create the panel.
	 */
	public SupplyGUI(final MainFrame mainFrame) {
		setBounds(100, 100, 1920, 1080);
		this.mainFrame = mainFrame;
		this.supplyOrderController = new SupplyOrderController();
		this.itemController = new ItemController();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		constructLayeredPane();
		constructSupplyRestaurantPanel();
		constructChoiceLowOrHigh();
	}

	private void constructLayeredPane() {
		layeredPane = new JLayeredPane();
		add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
	}
	
	private void createSupplyGUIButtons() {
		JButton btnSelectItem = new JButton("Search");
		btnSelectItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				readItems();
			}
		});
		supplyRestaurantPanel.add(btnSelectItem, "flowx,cell 2 3,alignx left,aligny bottom");

		JButton btnAddItem = new JButton("Add to order");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addLineItems();
			}
		});
		supplyRestaurantPanel.add(btnAddItem, "cell 2 15,alignx left,aligny bottom");

		JButton btnCheckOrder = new JButton("Check order");
		supplyRestaurantPanel.add(btnCheckOrder, "cell 4 5,alignx center,aligny center");
		btnCheckOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mainFrame, supplyOrderController.createOrderSummary());
			}
		});
		
		JButton btnRemoveFromOrder = new JButton("Remove from order");
		supplyRestaurantPanel.add(btnRemoveFromOrder, "cell 4 17");
		btnRemoveFromOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeLineItems();
			}
		});		
		
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
		supplyRestaurantPanel.add(btnProceed, "flowx,cell 6 16,alignx right,aligny bottom");

		supplyRestaurantPanel.add(btnGoBack, "cell 6 16,alignx right,aligny bottom");
		supplyRestaurantPanel.add(choiceLowOrHigh, "cell 1 4");
		
		JButton btnDeleteRow = new JButton("Delete row");
		btnDeleteRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemController.deleteItem(itemScrollPane.getSelectedObj());
			}
		});
		supplyRestaurantPanel.add(btnDeleteRow, "cell 1 14,alignx left");
		
		JButton btnAddRow = new JButton("Add row");
		btnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		supplyRestaurantPanel.add(btnAddRow, "flowx,cell 1 14,alignx left");
		
		JButton btnViewOrders = new JButton("View orders");
		btnViewOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supplyOrderScrollPane.updateList(new ArrayList<SupplyOrder>());
			}
		});
		supplyRestaurantPanel.add(btnViewOrders, "cell 5 14,alignx center");
	}

	private void createSupplyGUITextFields() {
		textFieldEnterQuantity = new JTextField();
		supplyRestaurantPanel.add(textFieldEnterQuantity, "cell 1 15,grow");
		textFieldEnterQuantity.setColumns(10);

		textFieldSearch = new JTextField();
		supplyRestaurantPanel.add(textFieldSearch, "cell 1 3,grow");
		textFieldSearch.setColumns(10);
	}

	private void createSupplyGUILabels() {
		JLabel lblResupplyHeader = new JLabel("Resupply ");
		lblResupplyHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		supplyRestaurantPanel.add(lblResupplyHeader, "cell 1 1 2 1,alignx center,aligny top");
		
		JLabel lblSupplyOrderHeader = new JLabel("View supply orders");
		lblSupplyOrderHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		supplyRestaurantPanel.add(lblSupplyOrderHeader, "cell 5 1,alignx center");
		
		JLabel lblEnterProduct = new JLabel("Enter product:");
		supplyRestaurantPanel.add(lblEnterProduct, "flowx,cell 1 3,alignx left,aligny center");
		
		JLabel lblEnterQuantity = new JLabel("Enter quantity:");
		supplyRestaurantPanel.add(lblEnterQuantity, "flowx,cell 1 15");
		
	}
	
	private void createSupplyGUIScrollPanes() {
		itemScrollPane = new GenericScrollPane<Item>(new ArrayList<Item>(), new ItemListCellRenderer());
		supplyRestaurantPanel.add(itemScrollPane, "cell 1 5 2 9,grow");
		supplyOrderScrollPane = new GenericScrollPane<SupplyOrder>(new ArrayList<SupplyOrder>(), new SupplyOrderListCellRenderer());
		supplyRestaurantPanel.add(supplyOrderScrollPane, "cell 5 5 1 9,grow");
	}
	
	private void constructSupplyRestaurantPanel() {
		supplyRestaurantPanel = new JPanel();
		layeredPane.add(supplyRestaurantPanel, "name_3150264217800");
		supplyRestaurantPanel.setLayout(new MigLayout("", "[grow][173.00px][113.00px,grow,center][][][grow][grow]", "[grow][][][][][41.00][40.00][39.00][36.00][68.00][][][][][][][grow]"));
		choiceLowOrHigh = new Choice();
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		supplyRestaurantPanel.add(rigidArea_1, "cell 0 4");
		
		createSupplyGUILabels();
		createSupplyGUIScrollPanes();
		createSupplyGUITextFields();
		createSupplyGUIButtons();
	}

	private void readItems() {
		Collection<Item> itemsFound;
		itemsFound = itemController.readItemByNameSortById(getNameFromSearchTextField(), getSortByChoice());
		itemScrollPane.updateList(itemsFound);
	}
	
	private int tryGetQuantityFromTextField() throws Exception {
		return Integer.parseInt(textFieldEnterQuantity.getText());
	}

	private String getNameFromSearchTextField() {
		return textFieldSearch.getText();
	}
	
	private void addLineItems() {
		LineItem<Item> lineItem = null;
		boolean wasAdded;
		boolean numIsCorrect;
		int quantity = 0;
		
		try {
			quantity = tryGetQuantityFromTextField();
			numIsCorrect = true;
		} catch (Exception e) {
			numIsCorrect = false;
			e.printStackTrace();
		}
		
		if (quantity > 0 && numIsCorrect) {
			lineItem = new LineItem<Item>(quantity, itemScrollPane.getSelectedObj());
			supplyOrderController.getSupplyOrder().addLineItem(lineItem);
			wasAdded = true;
		} else {
			wasAdded = false;
		}

		if (!wasAdded) {
			JOptionPane.showMessageDialog(mainFrame, "Item wasn't added, check your quantity");
		}

		if (lineItem != null) {
			JOptionPane.showMessageDialog(mainFrame, "Added item successfully"
										 + System.lineSeparator() + "Name: "
										 + lineItem.getItem().getName() + System.lineSeparator()
										 + "Quantity: " + lineItem.getQuantity());
		}
	}
	
	private void removeLineItems() {
		LineItem<Item> lineItem = null;
		try {
			int quantity = tryGetQuantityFromTextField();
			if (quantity > 0) {
				int itemId = itemScrollPane.getSelectedObj().getItemId();
				lineItem = supplyOrderController.getSupplyOrder().getLineItemById(itemId);
				supplyOrderController.getSupplyOrder().removeLineItem(lineItem);
				
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
		String messageToShow = supplyOrderController.createOrderSummary();
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
	
	private void constructChoiceLowOrHigh() {
		for (ParametersSortEnum temp : ParametersSortEnum.values()) {
			choiceLowOrHigh.add(temp.toString());
		}
	}
	
	private String getSortByChoice() {
		return choiceLowOrHigh.getItem(choiceLowOrHigh.getSelectedIndex()).toString();
	}
}