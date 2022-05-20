package gui.resupply;

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
import model.UrgencyEnum;
import gui.GenericScrollPane;
import gui.MainFrame;
import gui.item.ItemListCellRenderer;
import net.miginfocom.swing.MigLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
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
	private JTextField textFieldSearch;
	
	private Choice choiceLowOrHigh;

	private GenericScrollPane<Item> itemScrollPane;
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
		constructChoiceLowOrHigh();
	}

	private void constructLayeredPane() {
		layeredPane = new JLayeredPane();
		add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
	}
	
	private void createSupplyRestaurantButtons() {
		JButton btnSelectItem = new JButton("Search");
		btnSelectItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				readItems();
			}
		});
		supplyRestaurantPanel.add(btnSelectItem, "flowx,cell 2 3,alignx left,aligny bottom");
		
		JButton btnDeleteRow = new JButton("Delete row");
		btnDeleteRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemController.deleteItem(itemScrollPane.getSelectedObj());
			}
		});
		supplyRestaurantPanel.add(btnDeleteRow, "cell 6 6,growx");
		
		JButton btnAddRow = new JButton("Add row");
		btnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		supplyRestaurantPanel.add(btnAddRow, "cell 6 7,growx");

		JLabel lblEnterQuantity = new JLabel("Enter quantity:");
		supplyRestaurantPanel.add(lblEnterQuantity, "flowx,cell 1 15");
		
		JButton btnAddItem = new JButton("Add to order");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addLineItems();
			}
		});
		supplyRestaurantPanel.add(btnAddItem, "cell 2 15,alignx left,aligny bottom");

		
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
		supplyRestaurantPanel.add(btnProceed, "flowx,cell 7 16,alignx right,aligny bottom");

		supplyRestaurantPanel.add(btnGoBack, "cell 7 16,alignx right,aligny bottom");
		choiceLowOrHigh = new Choice();
		supplyRestaurantPanel.add(choiceLowOrHigh, "cell 1 4");
		
	}

	private void createSupplyRestaurantTextFields() {
		textFieldEnterQuantity = new JTextField();
		supplyRestaurantPanel.add(textFieldEnterQuantity, "cell 1 15,grow");
		textFieldEnterQuantity.setColumns(10);


		textFieldSearch = new JTextField();
		supplyRestaurantPanel.add(textFieldSearch, "cell 1 3,grow");
		textFieldSearch.setColumns(10);
	}
	
	private void constructSupplyRestaurantPanel() {
		supplyOrderController = new SupplyOrderController();
		itemController = new ItemController();
		supplyRestaurantPanel = new JPanel();
		layeredPane.add(supplyRestaurantPanel, "name_3150264217800");
		supplyRestaurantPanel.setLayout(new MigLayout("", "[grow][173.00px][113.00px,grow,center][][][][][grow]", "[grow][][][][][41.00][40.00][39.00][36.00][68.00][][][][][][][grow]"));

		JLabel lblResupplyHeader = new JLabel("Resupply ");
		lblResupplyHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		supplyRestaurantPanel.add(lblResupplyHeader, "cell 1 1 5 1,alignx center,aligny top");
		
		JLabel lblEnterProduct = new JLabel("Enter product:");
		supplyRestaurantPanel.add(lblEnterProduct, "flowx,cell 1 3,alignx left,aligny center");

		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		supplyRestaurantPanel.add(rigidArea_1, "cell 0 4");

		itemScrollPane = new GenericScrollPane<Item>(new ArrayList<Item>(), new ItemListCellRenderer());
		supplyRestaurantPanel.add(itemScrollPane, "cell 1 5 5 9,grow");
		
		createSupplyRestaurantTextFields();
		createSupplyRestaurantButtons();

	}

	private void readItems() {
		Collection<Item> itemsFound;
		itemsFound = itemController.readItemByNameSortById(getNameFromSearchTextField(), getSortByChoice());
		itemScrollPane.updateList(itemsFound);
	}
	private int getQuantityFromTextField() throws Exception {
		return Integer.parseInt(textFieldEnterQuantity.getText());
	}

	private String getNameFromSearchTextField() {
		return textFieldSearch.getText();
	}
	
	private void addLineItems() {
		LineItem<Item> lineItem = null;
		// TODO Maybe move LineItem into a controller so GUI doesn't see the model
		try {
			int quantity = getQuantityFromTextField();
			if (quantity > 0) {
				lineItem = new LineItem<Item>(quantity, itemScrollPane.getSelectedObj());
				supplyOrderController.getSupplyOrder().addLineItem(lineItem);
			} else {
				throw new Exception();
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(mainFrame, "Item wasn't added, check your quantity");
		}

		if (lineItem != null) {
			JOptionPane.showMessageDialog(mainFrame, "Added item successfully" + System.lineSeparator() + "Name: "
					+ lineItem.getItem().getName() + System.lineSeparator() + "Quantity: " + lineItem.getQuantity());
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
