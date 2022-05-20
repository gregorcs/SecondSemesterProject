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
import java.util.ArrayList;
import java.util.Collection;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Choice;

public class DecorationGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4802415561708298203L;
	private MainFrame mainFrame;
	private GenericScrollPane<Decoration> scrollPane;
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
		
		scrollPane = new GenericScrollPane<Decoration>(new ArrayList<Decoration>(), new DecorationListCellRenderer());
		panel.add(scrollPane, "cell 1 3 3 5,grow");
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.backToMainMenu();
			}
		});
		
		JButton btnAdd = new JButton("Add decoration");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAskForDecorationInfoDialog("add");
			}
		});
		panel.add(btnAdd, "cell 4 4");
		
		JButton btnAddStock = new JButton("Update decoration");
		btnAddStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createAskForDecorationInfoDialog("update");
			}
		});
		panel.add(btnAddStock, "cell 4 5");
		
		JButton btnDelete = new JButton("Delete decoration");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decorationController.deleteDecoration(scrollPane.getSelectedObj());
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
				Collection<Decoration> decorationsFound;
				//TODO FINISH SEARCH
				decorationsFound = decorationController.readAllDecorations();
				scrollPane.updateList(decorationsFound);
			}
		});
		
		Choice choiceStock = new Choice();
		panel.add(choiceStock, "cell 1 8");
		panel.add(btnSearch, "cell 1 8,alignx left");
		
	}
	
	public void refresh() {
		this.revalidate();
		this.repaint();
	}
	
	private void createAskForDecorationInfoDialog(String actionType) {
		JFrame frame;
		JPanel panel;
		JButton btnOk;
		JDialog dialog;
		JLabel lblName, lblDepartment, lblStock;
		JTextField textFieldName, textFieldDepartment, textFieldStock;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 400);
		panel = new JPanel();
		panel.setBounds(100, 100, 400, 400);
		frame.getContentPane().add(panel);
		
		dialog = new JDialog(frame , "Enter decoration details");
		dialog.getContentPane().setLayout(new FlowLayout());
		lblName = new JLabel("Name: ");
		lblDepartment = new JLabel("Department: ");
		lblStock = new JLabel("Stock: ");
		
		textFieldName = new JTextField(8);
		textFieldDepartment = new JTextField(8);
		textFieldStock = new JTextField(8);
		
		btnOk = new JButton("create");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				switch (actionType) {
				case "add":
					decorationController.createDecoration(textFieldName.getText(), textFieldDepartment.getText(),
							Integer.parseInt(textFieldStock.getText()));
					break;
				case "update":
					decorationController.updateDecoration(scrollPane.getSelectedObj(), textFieldName.getText(), 
							textFieldDepartment.getText(), Integer.parseInt(textFieldStock.getText()));
				}

			}
		});
		
		dialog.getContentPane().add(lblName);
		dialog.getContentPane().add(textFieldName);
		dialog.getContentPane().add(lblDepartment);
		dialog.getContentPane().add(textFieldDepartment);
		dialog.getContentPane().add(lblStock);
		dialog.getContentPane().add(textFieldStock);
		dialog.getContentPane().add(btnOk);
		dialog.pack();
		dialog.setVisible(true);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
