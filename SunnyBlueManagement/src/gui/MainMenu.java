package gui;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class MainMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainMenu() {
		setBounds(100, 100, 1920, 1080);
		setLayout(new MigLayout("", "[][][]", "[][][][][]"));
		//setLayout(new MigLayout("align 50% 50%"));
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("New JGoodies label");
		add(lblNewJgoodiesLabel, "cell 1 0");
		
		JButton btnNewButton = new JButton("New button");
		add(btnNewButton, "cell 1 1");
		
		JButton btnNewButton_2 = new JButton("New button");
		add(btnNewButton_2, "cell 1 2");
		
		JButton btnNewButton_1 = new JButton("New button");
		add(btnNewButton_1, "cell 1 3");
		
		JLabel lblNewLabel = new JLabel("New label");
		add(lblNewLabel, "cell 0 4");

	}
}
