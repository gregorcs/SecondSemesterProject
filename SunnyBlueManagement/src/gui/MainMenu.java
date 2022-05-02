package gui;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;

public class MainMenu extends JPanel implements Runnable {

	private static final long serialVersionUID = -2547880461104225298L;

	/**
	 * Create the panel.
	 */
	public MainMenu() {
		setBounds(100, 100, 1920, 1080);
		setLayout(new MigLayout("", "[][][center][]", "[][][][][][][][]"));
		//setLayout(new MigLayout("align 50% 50%"));
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Main menu");
		add(lblNewJgoodiesLabel, "cell 2 0");
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_2, "cell 2 1");
		
		JButton btnNewButton = new JButton("Reserve Table");
		add(btnNewButton, "cell 2 2");
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_1, "cell 2 3");
		
		JButton btnNewButton_2 = new JButton("Resupply restaurant");
		add(btnNewButton_2, "cell 2 4");
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea, "cell 2 5");
		
		JButton btnNewButton_1 = new JButton("Resupply kitchen");
		add(btnNewButton_1, "cell 2 6");
		
		JLabel lblConnectionStatus = new JLabel("Connection");
		add(lblConnectionStatus, "cell 0 7");
		
		JLabel lblConnectionOutput = new JLabel("");
		add(lblConnectionOutput, "cell 1 7");

	}

	@Override
	public void run() {
		
		
	}
}
