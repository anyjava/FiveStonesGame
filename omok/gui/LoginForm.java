package gui;

import gameClient.ClientLobby;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame {
	private static final long serialVersionUID = 0L;
	
	private Container cp = this.getContentPane();
	
	private JPanel panel;
	private JTextField inputId;
	private JPasswordField inputPass;
	private ImageButton loginButton;
	
	@SuppressWarnings("serial")
	public LoginForm() {
		panel = new JPanel() {
			public void paint(Graphics g) {
				ImageIcon icon = new ImageIcon(
						URLGetter.getResource("image/loginForm.jpg"));
				g.drawImage(icon.getImage(),0,0,null,null);
				this.paintComponents(g);
			}
		};
		inputId = new JTextField(7);
		inputPass = new JPasswordField(7);
		loginButton = new ImageButton("image/loginButton.jpg", "LOGIN");
		
		panel.add(inputId);
		panel.add(inputPass);
		panel.add(loginButton);
		
		inputId.setBounds(130,175,100,25);
		inputPass.setBounds(130,205,100,25);
		loginButton.setLocation(255, 165);
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!inputId.getText().equals("")) {
					new ClientLobby(inputId.getText());
					setVisible(false);
				}
			}
		});
		
		panel.setLayout(null);
		
		cp.add(panel);
		
		this.setSize(405,325);
		this.setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		new LoginForm();
	}
}
