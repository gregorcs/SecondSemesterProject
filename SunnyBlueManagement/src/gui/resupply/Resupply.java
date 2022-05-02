package gui.resupply;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;

public class Resupply extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7068038639929039542L;

	/**
	 * Create the panel.
	 */
	public Resupply() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JLayeredPane layeredPane = new JLayeredPane();
		add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		layeredPane.add(panel, "name_2798950394800");

	}

}
