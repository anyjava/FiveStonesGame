package gui;

// Mouse Click Event in Game Canvas execute in GameRoomGui.class/
import gameClient.ClientInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import protocolData.ChatData;
import protocolData.GameLobbyData;
import protocolData.GameData;
import protocolData.LobbyData;

class EventExecute extends WindowAdapter implements ActionListener{
	
	PanelInterface gui;
	ClientInterface client;
	
	protected EventExecute(PanelInterface gui, ClientInterface client) {
		this.gui = gui;
		this.client = client;
	}
	
	public void windowClosing(WindowEvent e) {
		client.sendMessage("님이 나가셨습니다.",  ChatData.EXIT);
		System.exit(0);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Create Game Room")) {
			String roomName;
			try {
				// when user input empty String, reinput!
				while( (roomName = getRoomName()).equals("") );	
				
			} catch(NullPointerException e1) {
				// when user input null, this method exit!
				return;
			}
			
			client.sendMessage(roomName, LobbyData.CREATE_ROOM);

			
		} else if(e.getActionCommand().equals("Enter Game Room")) {
			String roomName = ((LobbyGuiInter)gui).getSelectRoom();
			if (roomName == null) {
				JOptionPane.showConfirmDialog(null, 
						"Select Game Room!!.", "Notice!", JOptionPane.DEFAULT_OPTION);
				return;
			}
			client.sendMessage(roomName, LobbyData.ENTER_TO_ROOM);
			
		} else if(e.getActionCommand().equals("SEND")) {
			System.out.println("-------------------SEND CHAT MESSAGE!!");
			sendChatMessage();
			
		} else if(e.getActionCommand().equals("EXIT")) {
			this.windowClosing(null);
			
		} else if (e.getActionCommand().equals("나가기")) {
			client.sendMessage(null, GameLobbyData.EXIT_ROOM);
			
		} else if (e.getActionCommand().equals("EXIT GAME")) {
			int bool = JOptionPane.showConfirmDialog(null, 
					"Really Exit Game? You lost this game.", "Notice!", JOptionPane.YES_NO_OPTION); 
			if(bool == 0) client.sendMessage(null, GameData.EXIT_THEGAME);
			else return;
			
		}else if (e.getActionCommand().equals("START")) {
			client.sendMessage(null, GameLobbyData.GAME_START);
		
		} else if (e.getActionCommand().equals("READY")) {
			((GameLobby)gui).m_startButton.setText("CANCEL");
			client.sendMessage(null, GameLobbyData.GAME_READY);
			
		} else if (e.getActionCommand().equals("CANCEL")) {
			((GameLobby)gui).m_startButton.setText("READY");
			client.sendMessage(null, GameLobbyData.CANCEL_READY);
			
		} else if(e.getActionCommand().equals("Total User")) {
			gui.setUserListFrame(new UserListFrame(100, 200, this.client));
			client.sendMessage(null,ChatData.SEND_TOTAL_USER);
			
		} else if(e.getActionCommand().equals("한수 물리기")) {
			client.sendMessage(null, GameData.REQUEST_RETURN);
			
			/*
			 *  In input... Enter Event...
			 */
		} else {
			System.out.println("&&&&&&&&&&&&&&&&&&&&&" + e.getActionCommand());
			sendChatMessage();
		}
	}
	
	private String getRoomName() {
		return JOptionPane.showInputDialog("방제목을 입력하세요~!");
	}
	/*
	 *  send Event - 메세지 보내기.
	 */
	private void sendChatMessage() {
		if(!gui.getInputText().equals("")) {
			client.sendMessage(gui.getInputText(),  ChatData.MESSAGE);
			gui.setTextToInput("");
		}
	}

	public void setGui(PanelInterface gui) {
		this.gui = gui;
	}
}