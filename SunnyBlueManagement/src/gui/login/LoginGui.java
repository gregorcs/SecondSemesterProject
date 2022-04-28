package gui.login;

import gui.GridBagJPanel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;

public class LoginGui extends GridBagJPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2105575596170057918L;
	private JTextField textFieldPassword;
	private JTextField textFieldUserName;
	private JLabel lblLogin;

	/**
	 * Create the panel.
	 */
	public LoginGui() {
		GridBagLayout gridBagLayout = (GridBagLayout) getLayout();
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 24, 0, 0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		
		lblLogin = new JLabel("Welcome");
		lblLogin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 19));
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.gridheight = 2;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 0);
		gbc_lblLogin.gridx = 6;
		gbc_lblLogin.gridy = 1;
		add(lblLogin, gbc_lblLogin);
		
		textFieldUserName = new JTextField();
		GridBagConstraints gbc_textFieldUserName = new GridBagConstraints();
		gbc_textFieldUserName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldUserName.gridx = 6;
		gbc_textFieldUserName.gridy = 3;
		add(textFieldUserName, gbc_textFieldUserName);
		textFieldUserName.setColumns(10);
		
		textFieldPassword = new JTextField();
		GridBagConstraints gbc_textFieldPassword = new GridBagConstraints();
		gbc_textFieldPassword.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPassword.gridx = 6;
		gbc_textFieldPassword.gridy = 4;
		add(textFieldPassword, gbc_textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JButton btnNewButton = new JButton("Log in");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 5;
		add(btnNewButton, gbc_btnNewButton);

	}

}
