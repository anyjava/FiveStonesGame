package gui;

import java.util.Vector;

public interface LobbyGuiInter extends GuiInterface {

	void setRoomList(Vector<String> roomList);

	String getSelectRoom();
	int[] getFrameSize();
}
