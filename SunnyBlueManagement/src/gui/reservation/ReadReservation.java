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
		setBounds(100, 100, 1920, 1080);
		this.mainFrame = mainFrame;
		this.reservationController = new ReservationController();

		setLayout(new MigLayout("", "[grow][212.00px][245.00][grow]", "[grow][][][][158.00,top][20px][][grow]"));
		
		JLabel lblHeader = new JLabel("Search reservations");
		lblHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblHeader, "cell 1 1,aligny center");
		
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
		add(btnSearch, "cell 2 2");
		
		scrollPane = new ReservationScrollPane();
		add(scrollPane, "cell 1 3 2 3,grow");
		
		textSearch = new JTextField();
		add(textSearch, "cell 1 2,growx");
		textSearch.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.backToMainMenu();
			}
		});
		add(btnBack, "cell 2 6,alignx center,aligny baseline");
		

	}
	
	private void updateScrollPane(ReservationScrollPane pane, Collection<Reservation> reservations) {				//THESE 2 ARE DIFFERENT - INITIALIZE / UPDATELIST
		pane.initializeList(reservations);
	}
}
