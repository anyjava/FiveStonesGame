package gui;

/*
 * Version 1.01 챗티창 라인보호.
 */

import gameClient.ClientInterface;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;



@SuppressWarnings("serial")
public class LobbyGui extends JPanel implements LobbyGuiInter, PanelInterface{

	private ClientInterface client;
	
	private EventExecute event;
	
	private UserListFrame userListFrame;

	/*
	 * Component of Room List Panel
	 */
	private JPanel m_roomListPanel = new JPanel() {
		public void paint(Graphics g) {
			this.paintComponents(g);
		}
	};

	private JList m_roomList = new JList();
	
	private JTable m_roomListTable;

	private JScrollPane m_scPaneRoomList;

	private ImageButton m_createRoomButton, m_enterRoomButton;

	/*
	 * Component of User List Panel
	 */
	private JPanel m_userListPanel = new JPanel() {
		public void paint(Graphics g) {
			this.paintComponents(g);
		}
	};
	private JList m_userList = new JList();
	private JPopupMenu popup;
	private JScrollPane m_scPaneUserList;
	private ImageButton m_totalUserButton;

	/*
	 * Component of Chat Window
	 */
	private JPanel m_logWinPanel = new JPanel() {
		public void paint(Graphics g) {
			this.paintComponents(g);
		}
	};

	private JTextArea m_logWindow = new JTextArea(5, 20);

	protected JTextField m_textInput = new JTextField();

	private ImageButton m_sendButton = new ImageButton("image/lobbySendButton.jpg", "SEND","image/lobbySendButtonOver.jpg");

	private JScrollPane m_scPaneLogWin;
	
	private JScrollBar m_vScroll;

	/*
	 * Component of Info Panel
	 */
	private JPanel m_infoPanel = new JPanel() {
		public void paint(Graphics g) {
			this.paintComponents(g);
		}
	};

	private JLabel m_infoLabel = new JLabel();

	private ImageButton m_exitButton = new ImageButton("image/lobbyExitButton.jpg", "EXIT","image/lobbyExitButtonOver.jpg");

	/*
	 * 임시...
	 */
	private Vector<String> vc = new Vector<String>(1);

	
	/*
	 * 생성자.
	 */
	public LobbyGui(ClientInterface client) {
		this.client = client;
		event = new EventExecute(this, client);
		
		vc.add("");
		excute();
	}
	
	public void paint(Graphics g) {
		ImageIcon icon = new ImageIcon(
				URLGetter.getResource("image/lobbyBg.jpg"));
		g.drawImage(icon.getImage(),0,0,null,null);
		this.paintComponents(g);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Network FIve Eyes Ver. 1.0");
		Container cp = frame.getContentPane();
		cp.add(new LobbyGui(null));
		
		frame.setSize(340,440);
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		System.out.println("Exit");
	}

	private void excute() {
		add(generatorRoomListPanel());
		add(generatorUserListPanel());
		add(generatorChatWindowPanel());
		add(generatorInfoPanel());
		setLayout(null);
	}
	
	private JPanel generatorRoomListPanel() {
		m_createRoomButton = new ImageButton("image/makeRoomButton.jpg", "Create Game Room","image/makeRoomButtonOver.jpg");
		m_enterRoomButton = new ImageButton("image/enterRoomButton.jpg", "Enter Game Room","image/enterRoomButtonOver.jpg");

		m_roomList.setListData(vc);

		m_scPaneRoomList = new JScrollPane(m_roomList,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
//		m_scPaneRoomList.setBorder(new TitledBorder("Game Room List"));
		m_scPaneRoomList.setBounds(5,5,150,100);

		m_roomListPanel.add(m_createRoomButton);
		m_createRoomButton.setBounds(10, 110, 65, 44);
		m_roomListPanel.add(m_enterRoomButton);
		m_enterRoomButton.setBounds(85, 110, 65, 44);

		m_roomListPanel.add(m_scPaneRoomList);
		m_roomListPanel.setLayout(null);
		m_roomListPanel.setBounds(5,25,160,160);
		
//		m_roomListPanel.setBorder(new TitledBorder("SDfsd"));
		
		m_createRoomButton.addActionListener(event);
		m_enterRoomButton.addActionListener(event);

		return m_roomListPanel;
	}

	private JPanel generatorUserListPanel() {
		m_userList.setListData(vc);

		m_scPaneUserList = new JScrollPane(m_userList,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		m_scPaneUserList.setBorder(new TitledBorder("User List"));
		m_scPaneUserList.setBounds(5, 5, 150, 100);
		
		m_totalUserButton = new ImageButton("image/lobbyTotalUserButton.jpg", "Total User","image/lobbyTotalUserButtonOver.jpg");
		m_userListPanel.add(m_totalUserButton);
		m_totalUserButton.setBounds(5,110,150,44);
		m_totalUserButton.addActionListener(event);
		
		m_userListPanel.add(m_scPaneUserList);
		m_userListPanel.setLayout(null);
		m_userListPanel.setBounds(165, 25, 200,160);
		
//		m_userListPanel.setBorder(new TitledBorder("SDfsd"));		
		m_userList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showPopupMenu(e);
			}
		});
		
		return m_userListPanel;
	}
	
	private void showPopupMenu(MouseEvent e) {
		JMenuItem menuSendSlip = new JMenuItem("쪽지 보내기");
		menuSendSlip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SlipFrame((String) m_userList.getSelectedValue(), null, true, client);
			}
		});

		popup = new JPopupMenu();
		popup.add(menuSendSlip);
		popup.show(m_userList, e.getX(), e.getY());
	}
	

	private JPanel generatorChatWindowPanel() {
		m_scPaneLogWin = new JScrollPane(m_logWindow,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		m_scPaneLogWin.setBorder(new TitledBorder("Chatting Window"));
		m_logWindow.setEditable(false);
		m_logWindow.setLineWrap(true);
		m_scPaneLogWin.setBounds(5,5,310,150);
		m_vScroll = m_scPaneLogWin.getVerticalScrollBar();
		m_vScroll.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
//				System.out.println("************** EVENT!!!" + e.getValueIsAdjusting());
				if(!e.getValueIsAdjusting()) m_vScroll.setValue(m_vScroll.getMaximum());
//				m_vScroll.removeAdjustmentListener(this);
				
			}
		});
		
		
		m_logWinPanel.add(m_textInput);
		m_textInput.setBounds(5, 160, 170, 30);
		m_logWinPanel.add(m_sendButton);
		m_sendButton.setBounds(180, 160, 65, 30);
		m_logWinPanel.add(m_exitButton);
		m_exitButton.setBounds(250,160,65,30);

		m_logWinPanel.add(m_scPaneLogWin);
		m_logWinPanel.setLayout(null);
		m_logWinPanel.setBounds(5, 205, 320, 200);
		
		
//		m_logWinPanel.setBorder(new TitledBorder("SDfsd"));
		
		m_textInput.setName("input");
		m_textInput.addActionListener(event);
		m_sendButton.addActionListener(event);
		
		return m_logWinPanel;
	}

	private JPanel generatorInfoPanel() {
		m_infoPanel.add(m_infoLabel);
		m_infoLabel.setBounds(5,5,190,100);
		
		
		
		m_infoPanel.setLayout(null);
		m_infoPanel.setBounds(330, 205, 200,200);

//		m_infoPanel.setBorder(new TitledBorder("SDfsd"));
		
		m_exitButton.addActionListener(event);
		
		return m_infoPanel;
	}

	
	/*
	 * public mothod! interface implements.....
	 */
	public void setTextToLogWindow(String str) {
		m_logWindow.append(str);
	}

	public void setUserList(Vector<String> UserList) {
		m_userList.setListData(UserList);
	}

	public void showMessageBox(String sender, String message, boolean option) {
		new SlipFrame(sender, message, false);
	}

	public JList getUserList() {
		return m_userList;
	}

	public JList getJList() {
		return m_userList;
	}
	

	public void unShow() {
		this.setVisible(false);
	}


	public String getInputText() {
		return m_textInput.getText();
	}


	public void setTextToInput(String string) {
		m_textInput.setText(string);
	}


	public void setRoomList(Vector<String> roomList) {
		m_roomList.setListData(roomList);
	}


	public String getSelectRoom() {
		String temp = (String)m_roomList.getSelectedValue();
		return temp;
	}

	public void setTotalUser(Vector<String> userList) {
		userListFrame.setUserList(userList);
	}


	public void setUserListFrame(UserListFrame userListFrame) {
		this.userListFrame = userListFrame;
	}


	public void setPanel(PanelInterface panel) {
		// TODO Auto-generated method stub
		
	}


	public int[] getFrameSize() {
		int[] size = {340,440};
		return size;
	}

	public void setPanel(GameLobbyInter panel) {
	}
	public void setPanel(LobbyGuiInter panel) {
	}
	public void setPanel(RoomGuiInter panel) {
	}
}

class RoomTableModel implements TableModel {
	

	public int getRowCount() {
		return 3;
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Class<?> getColumnClass(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}
	
}