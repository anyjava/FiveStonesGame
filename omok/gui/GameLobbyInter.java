package gui;

public interface GameLobbyInter extends GuiInterface {

	void setRoomKing(String name);
	void setCrhallenger(String string);
	void setGameRoomInf(String info);
	void setStartButton(boolean isRoomKing);
	void setButtonEnable(boolean clickable);
	String getGameInfo();
	int[] getFrameSize();
	
	
}
