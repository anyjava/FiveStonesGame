package gui;

import gameClient.ClientLobby;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel implements PanelInterface {
	
	private ClientLobby client;

	private JTextField inputId;
	private JPasswordField inputPass;
	private ImageButton loginButton;
	
	@SuppressWarnings("serial")
	public LoginPanel(final ClientLobby client) {
		this.client = client;
		
		inputId = new JTextField(7);
		inputPass = new JPasswordField(7);
		loginButton = new ImageButton("image/loginButton.jpg", "LOGIN","image/loginButtonOver.jpg");
		
		add(inputId);
		add(inputPass);
		add(loginButton);
		
		inputId.setBounds(130,175,100,25);
		inputPass.setBounds(130,205,100,25);
		loginButton.setLocation(255, 165);
		
		inputId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!inputId.getText().equals("")) {
					client.setName(inputId.getText());
					client.changePanel(new LobbyGui(client));
				}
			}
		});
		
		inputPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!inputId.getText().equals("")) {
					client.setName(inputId.getText());
					client.changePanel(new LobbyGui(client));
				}
			}
		});
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!inputId.getText().equals("")) {
					client.setName(inputId.getText());
					client.changePanel(new LobbyGui(client));
				}
			}
		});
		
		setLayout(null);
	}
	
	public void paint(Graphics g) {
		ImageIcon icon = new ImageIcon(
				URLGetter.getResource("image/loginForm.jpg"));
		g.drawImage(icon.getImage(),0,0,null,null);
		this.paintComponents(g);
	}
	
	public int[] getFrameSize() {
		int size[] = new int[2];
		size[0] = 405;
		size[1] = 325;
		return size;
	}
	
	public static void main(String[] args) {
		new LoginForm();
	}
	
	

	public String getInputText() {
		return null;
	}

	public JList getJList() {
		return null;
	}

	public void setTextToInput(String string) {
	}
	public void setTextToLogWindow(String str) {
	}
	public void setTotalUser(Vector<String> userList) {
	}
	public void setUserList(Vector<String> userList) {
	}
	public void setUserListFrame(UserListFrame userListFrame) {
	}
	public void showMessageBox(String sender, String message, boolean option) {
	}
	public void unShow() {
	}
	public void setPanel(PanelInterface panel) {
	}
	public void setPanel(GameLobbyInter panel) {
	}
	public void setPanel(LobbyGuiInter panel) {
	}
	public void setPanel(RoomGuiInter panel) {
	}
}
