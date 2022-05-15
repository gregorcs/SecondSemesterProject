package gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.resupply.DecorationGUI;
import gui.resupply.SupplyGUI;

import java.awt.GridBagLayout;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 5771240247116622335L;
	private JPanel contentPane;
	private MainMenu mainMenuPanel;
	private JPanel resupplyPanel;
	private JLayeredPane layeredPane;
	private DecorationGUI decorationPanel;

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
		/**
		 * first layered pane 
		 */
		layeredPane = new JLayeredPane();
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 0;
		gbc_layeredPane.gridy = 0;
		contentPane.add(layeredPane, gbc_layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		/**
		 * panels added
		 */
		mainMenuPanel = new MainMenu(this);
		layeredPane.add(mainMenuPanel, "name_1816740339900");
		
		resupplyPanel = new SupplyGUI(this);
		layeredPane.add(resupplyPanel, "name_1894438046500");
		
		decorationPanel = new DecorationGUI();
		layeredPane.add(decorationPanel, "name_98119803398600");
		
	}

	public JLayeredPane getLayeredPane() {
		return this.layeredPane;
	}
	
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public void backToMainMenu() {
		switchPanels(mainMenuPanel);
	}
}
