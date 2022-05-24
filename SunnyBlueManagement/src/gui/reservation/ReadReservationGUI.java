package gui.reservation;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;

import controller.ReservationController;
import gui.GenericScrollPane;
import gui.MainFrame;
import model.ReservationFolder.Reservation;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;

public class ReadReservationGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7335505866400342972L;
	private MainFrame mainFrame;
	private JTextField textSearch;
	private GenericScrollPane<Reservation> scrollPane;
	private ReservationController reservationController;
	/**
	 * Create the panel.
	 */
	public ReadReservationGUI(final MainFrame mainFrame) {
		setBounds(100, 100, 1920, 1080);
		this.mainFrame = mainFrame;
		this.reservationController = new ReservationController();

		setLayout(new MigLayout("", "[grow][212.00px][245.00][grow]", "[grow][][][][][][158.00,grow,top][20px][][grow]"));
		
		JLabel lblHeader = new JLabel("Search reservations");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblHeader, "cell 1 1 2 1,alignx center,aligny center");
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea, "cell 1 2");
		add(btnSearch, "flowx,cell 2 3");
		
		JButton btnDelete = new JButton("Cancel reservation");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		add(btnDelete, "cell 3 3");
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_1, "cell 1 4");
		
		JButton btnDetails = new JButton("Show details");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				details();
			}
		});
		add(btnDetails, "cell 3 4");
		
		scrollPane = new GenericScrollPane<Reservation>(new ArrayList<Reservation>(), new ReservationListCellRenderer());
		add(scrollPane, "cell 1 5 2 3,grow");
		
		textSearch = new JTextField();
		add(textSearch, "cell 1 3,growx");
		textSearch.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		add(btnBack, "cell 2 8,alignx right,aligny baseline");
		

	}
	
	private void back() {
		textSearch.setText("");
		updateScrollPane(scrollPane, new ArrayList<>()); //CLEANS THE PANEL
		mainFrame.backToMainMenu();
	}
	
	private void search() {
		try {
			updateScrollPane(scrollPane, reservationController.readReservationsByDate(textSearch.getText()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void delete() {
		int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel this reservation?", "Confirm deletion", JOptionPane.YES_NO_OPTION);
		if(dialogResult != JOptionPane.YES_OPTION){
			return;
		}
		Reservation reservation = scrollPane.getSelectedObj();
		if(reservationController.delete(reservation)) {
	        JOptionPane.showMessageDialog(null, "Reservation cancelled!");
	        search();
		}
		else {
	        JOptionPane.showMessageDialog(null, "There was an error while cancelling reservation!", "Deletion failed", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void details() {
		Reservation reservation = scrollPane.getSelectedObj();
		if(reservation == null) return;
		String messageToShow = reservationController.constructDetails(reservation);
		JOptionPane.showMessageDialog(mainFrame, messageToShow);
	}
	
	private void updateScrollPane(GenericScrollPane<Reservation> pane, Collection<Reservation> reservations) {
		pane.initializeList(reservations, new ReservationListCellRenderer());
	}
}
