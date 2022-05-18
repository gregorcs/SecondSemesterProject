package gui.reservation;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;

import controller.ReservationController;
import gui.MainFrame;
import model.ReservationFolder.Reservation;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ReadReservation extends JPanel {
	private MainFrame mainFrame;
	private JTextField textSearch;
	private ReservationScrollPane scrollPane;
	private ReservationController reservationController;
	/**
	 * Create the panel.
	 */
	public ReadReservation(final MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.reservationController = new ReservationController();

		setLayout(new MigLayout("", "[212.00px][245.00]", "[][][][158.00,top][20px][]"));
		
		JLabel lblHeader = new JLabel("Search reservations");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblHeader, "cell 0 0,aligny center");
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateScrollPane(scrollPane, reservationController.readReservationsByDate(textSearch.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		add(btnSearch, "cell 1 1");
		
		scrollPane = new ReservationScrollPane();
		add(scrollPane, "cell 0 2 2 3,grow");
		
		textSearch = new JTextField();
		add(textSearch, "cell 0 1,growx");
		textSearch.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.backToMainMenu();
			}
		});
		add(btnBack, "cell 1 5,alignx center,aligny baseline");
		

	}
	
	private void updateScrollPane(ReservationScrollPane pane, Collection<Reservation> reservations) {				//THESE 2 ARE DIFFERENT - INITIALIZE / UPDATELIST
		pane.initializeList(reservations);
	}
}
