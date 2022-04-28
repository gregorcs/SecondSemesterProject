package gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.login.LoginGui;

import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private LoginGui loginPanel;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.rowWeights = new double[]{1.0};
		gbl_contentPane.columnWeights = new double[]{1.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 0;
		gbc_layeredPane.gridy = 0;
		contentPane.add(layeredPane, gbc_layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		loginPanel = new LoginGui();
		layeredPane.add(loginPanel, "name_82071188656100");
		
		panel = new JPanel();
		layeredPane.add(panel, "name_69172224462400");
		GridBagConstraints gbc = new GridBagConstraints();

		/**
		 * 
		 */
	}

}
