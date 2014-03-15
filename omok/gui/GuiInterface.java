package gui;
import java.util.Vector;

import javax.swing.JList;

public interface GuiInterface {
	public void setTextToLogWindow(String str);
	public void setUserList(Vector<String> userList);
	public void showMessageBox(String sender, String message, boolean option);
	public JList getJList();
	public void unShow();
	public String getInputText();
	public void setTextToInput(String string);
	public void setTotalUser(Vector<String> userList);
	public void setUserListFrame(UserListFrame userListFrame);
	public void setPanel(PanelInterface panel);
	public void setPanel(GameLobbyInter panel);
	public void setPanel(LobbyGuiInter panel);
	public void setPanel(RoomGuiInter panel);
}
