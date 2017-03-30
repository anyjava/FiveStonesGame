package gui;

import gameClient.ClientInterface;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class UserListFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Container cp;
	private JList m_userList;
	private JScrollPane m_scList;
	private JButton m_sendButton, m_closeButton;
	
	protected ClientInterface client;
	
	public UserListFrame(int x, int y, final ClientInterface client) {
		super("전체 접속자 목록");
		
		this.client = client;
		
		cp = this.getContentPane();
		
		m_userList = new JList();
		m_scList = new JScrollPane(m_userList,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		m_scList.setBounds(5,5,185,330);
		
		m_sendButton = new JButton("SEND");
		m_sendButton.setBounds(5,340,90,30);
		
		m_sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (m_userList.getSelectedValue() != null) {
					client.setSlipTo((String) m_userList.getSelectedValue());
					new SlipFrame((String) m_userList.getSelectedValue(), null, true, client);
					
				} else {
					JOptionPane.showConfirmDialog(null, 
							"Select User!!.", "Notice!", JOptionPane.DEFAULT_OPTION);
				}
			}
		});
		
		m_closeButton = new JButton("CLOSE");
		m_closeButton.setBounds(100,340,90,30);
		
		m_closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		cp.add(m_scList);
		cp.add(m_sendButton);
		cp.add(m_closeButton);
		cp.setLayout(null);
		
		this.setBounds(x,y,200,400);
		this.setResizable(false);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
			}
		});
	}
	
	public void setUserList(Vector<String> list) {
		m_userList.setListData(list);
	}
	
	public static void main(String[] args) {
		new UserListFrame(0,0,null);
	}

}
