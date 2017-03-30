package gui;

import gameClient.ClientLobby;
import gui.GameRoomGui.GameBoardCanvas;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JList;

import protocolData.ChatData;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements GuiInterface, LobbyGuiInter, GameLobbyInter, RoomGuiInter {

	private Container cp = this.getContentPane();
	private PanelInterface panel;
	private GameLobbyInter panelGameLobby;
	private LobbyGuiInter panelLobby;
	private RoomGuiInter panelRoom;
	private GuiInterface gui;
	
	public MainFrame(final ClientLobby client) {
		super("Network Omok Ver. 1.0");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				client.sendMessage("´ÔÀÌ ³ª°¡¼Ì½À´Ï´Ù.",  ChatData.EXIT);
				System.exit(0);
			}
		});
		
//		 when Window is Actvate, this Event call.
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				if(panelRoom != null)
					panelRoom.getM_board().drawHistory();
			}
		});
	}
	
	public String getInputText() {
		return gui.getInputText();
	}

	public JList getJList() {
		return gui.getJList();
	}

	public void setTextToInput(String string) {
		gui.setTextToInput(string);
	}

	public void setTextToLogWindow(String str) {
		gui.setTextToLogWindow(str);
	}

	public void setTotalUser(Vector<String> userList) {
		gui.setTotalUser(userList);
	}

	public void setUserList(Vector<String> userList) {
		gui.setUserList(userList);
	}

	public void setUserListFrame(UserListFrame userListFrame) {
		gui.setUserListFrame(userListFrame);
	}

	public void showMessageBox(String sender, String message, boolean option) {
		gui.showMessageBox(sender, message, option);
	}

	public void unShow() {
		gui.unShow();
	}

	public void setPanel(GameLobbyInter panel) {
		System.out.println("################### gameLobby in");
		cp.removeAll();
		
		this.panelGameLobby = panel;
		this.gui = panel;
		cp.add((Component) panel);
		
		int[] size = panelGameLobby.getFrameSize();
		
		this.setSize(size[0], size[1]);
		this.setVisible(true);
		System.out.println("################### gameLobby out");
	}
	
	public void setPanel(PanelInterface panel) {
		cp.removeAll();
		
		this.panel = panel;
		this.gui = panel;
		cp.add((Component) panel);
		
		int[] size = panel.getFrameSize();
		
		this.setSize(size[0], size[1]);
		this.setVisible(true);
	}
	
	public void setPanel(LobbyGuiInter panel) {
		cp.removeAll();
		
		this.panelLobby = panel;
		this.gui = panel;
		cp.add((Component) panelLobby);
		
		int[] size = panelLobby.getFrameSize();
		
		this.setSize(size[0], size[1]);
		this.setVisible(true);
	}
	
	public void setPanel(RoomGuiInter panel) {
		cp.removeAll();
		
		this.panelRoom = panel;
		this.gui = panel;
		cp.add((Component) panel);
		
		int[] size = panelRoom.getFrameSize();
		
		this.setSize(size[0], size[1]);
		this.setVisible(true);
	}

	public String getSelectRoom() {
		return panelLobby.getSelectRoom();
	}

	public void setRoomList(Vector<String> roomList) {
		panelLobby.setRoomList(roomList);
	}

	public String getGameInfo() {
		return panelGameLobby.getGameInfo();
	}

	public void setButtonEnable(boolean clickable) {
		panelGameLobby.setButtonEnable(clickable);
	}

	public void setCrhallenger(String string) {
		panelGameLobby.setCrhallenger(string);
	}

	public void setGameRoomInf(String info) {
		panelGameLobby.setGameRoomInf(info);
	}

	public void setRoomKing(String name) {
		panelGameLobby.setRoomKing(name);
	}

	public void setStartButton(boolean isRoomKing) {
		panelGameLobby.setStartButton(isRoomKing);
	}

	public void backOneStep(int n) {
		panelRoom.backOneStep(n);
	}

	public void drawStone(int[] stoneLocation, boolean b) {
		panelRoom.drawStone(stoneLocation, b);
	}

	public void newGame() {
		panelRoom.newGame();
	}

	public void setGameRoomInfo(String info) {
		panelRoom.setGameRoomInfo(info);
	}

	public int[] getFrameSize() {
		return null;
	}

	public GameBoardCanvas getM_board() {
		return panelRoom.getM_board();
	}
}