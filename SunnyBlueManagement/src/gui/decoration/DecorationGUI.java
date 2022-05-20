package gui.decoration;

import javax.swing.JPanel;

import controller.DecorationController;
import gui.GenericScrollPane;
import gui.MainFrame;
import model.Decoration;
import net.miginfocom.swing.MigLayout;

import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.Collection;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class DecorationGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4802415561708298203L;
	private MainFrame mainFrame;
	private GenericScrollPane<Decoration> list;
	private DecorationController decorationController;
	private JTextField textField;
	
	public DecorationGUI(final MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setBounds(100, 100, 1920, 1080);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		decorationController = new DecorationController();
		
		JLayeredPane layeredPane = new JLayeredPane();
		add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("", "[grow][grow][87.00][][grow]", "[grow][][][grow][][][][][][grow]"));
		layeredPane.add(panel, "name_97268705302100");
		
		JLabel lblHeader = new JLabel("Decoration management");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblHeader, "cell 1 1 3 1,alignx center,aligny center");
		
		list = new GenericScrollPane<Decoration>(decorationController.readAllDecorations(), new DecorationListCellRenderer());
		panel.add(list, "cell 1 3 3 5,grow");
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.backToMainMenu();
			}
		});
		
		JButton btnAdd = new JButton("Add decoration");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAskForDecorationInfoDialog();
			}
		});
		panel.add(btnAdd, "cell 4 4");
		
		JButton btnAddStock = new JButton("Update decoration");
		btnAddStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnAddStock, "cell 4 5");
		
		JButton btnDelete = new JButton("Delete decoration");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decorationController.deleteDecoration(list.getSelectedObj());
			}
		});
		panel.add(btnDelete, "cell 4 6");
		
		JLabel lblSearch = new JLabel("Search:");
		panel.add(lblSearch, "flowx,cell 1 8");
		
		textField = new JTextField();
		panel.add(textField, "cell 1 8,alignx left");
		textField.setColumns(10);
		panel.add(btnBack, "cell 4 9,alignx right,aligny bottom");
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				decorationController = new DecorationController();
				Collection<Decoration> decorationsFound;
				decorationsFound = decorationController.readAllSwitch(list.getDepartmentFromChoice(), list.getStockSortFromChoice());
				list.updateList(decorationsFound);
				*/
			}
		});
		panel.add(btnSearch, "cell 1 8,alignx left");
		
	}
	
	public void refresh() {
		this.revalidate();
		this.repaint();
	}

	
	private void createAskForDecorationInfoDialog() {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 400, 400);
		JPanel panel = new JPanel();
		panel.setBounds(100, 100, 400, 400);
		JButton button = new JButton("create");
		frame.add(panel);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JDialog dialog = new JDialog(frame , "Enter decoration details");
		dialog.setLayout(new FlowLayout());
		JLabel lblName = new JLabel("Name: ");
		JLabel lblDepartment = new JLabel("Department: ");
		JLabel lblStock = new JLabel("Stock: ");
		
		JTextField textFieldName = new JTextField(8);
		JTextField textFieldDepartment = new JTextField(8);
		JTextField textFieldStock = new JTextField(8);
		
		dialog.add(lblName);
		dialog.add(textFieldName);
		dialog.add(lblDepartment);
		dialog.add(textFieldDepartment);
		dialog.add(lblStock);
		dialog.add(textFieldStock);
		dialog.add(button);
		dialog.pack();
		dialog.setVisible(true);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}