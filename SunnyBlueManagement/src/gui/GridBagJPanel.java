package gui;

import javax.swing.*;
import java.awt.*;

public abstract class GridBagJPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public GridBagJPanel() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
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
