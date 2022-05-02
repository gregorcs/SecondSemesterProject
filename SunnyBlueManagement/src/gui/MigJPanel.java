package gui;

import javax.swing.*;
import java.awt.*;
import net.miginfocom.swing.MigLayout;

public abstract class MigJPanel extends JPanel {

	private static final long serialVersionUID = 9104222594694661953L;

	/**
	 * Create the panel.
	 */
	public MigJPanel() {
		setBounds(100, 100, 1920, 1080);
		setLayout(new MigLayout("", "[]", "[]"));

/*
 * an example below of how to add buttons (remove at the end of the project)
  		JButton a, b;
		a = new JButton("button");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 1;
		add(a, gbc);
		
		b = new JButton("button1");
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(b, gbc);
		*/
	}

}
