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
	private ReservationScrollPane scrollPane;
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
				try {
					updateScrollPane(scrollPane, reservationController.readReservationsByDate(textSearch.getText()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea, "cell 1 2");
		add(btnSearch, "cell 2 3");
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_1, "cell 1 4");
		
		scrollPane = new ReservationScrollPane();
		add(scrollPane, "cell 1 5 2 3,grow");
		
		textSearch = new JTextField();
		add(textSearch, "cell 1 3,growx");
		textSearch.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.setHorizontalAlignment(SwingConstants.LEFT);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.backToMainMenu();
			}
		});
		add(btnBack, "cell 2 8,alignx right,aligny baseline");
		

	}
	
	private void updateScrollPane(ReservationScrollPane pane, Collection<Reservation> reservations) {				//THESE 2 ARE DIFFERENT - INITIALIZE / UPDATELIST
		pane.initializeList(reservations);
	}
}
