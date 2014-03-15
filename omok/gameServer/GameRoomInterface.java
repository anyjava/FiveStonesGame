package gameServer;

import protocolData.Protocol;

public interface GameRoomInterface extends ServerInterface {

	String getRoomName();
	String getRoomKingName();
	void subRoom();
    int getNumber();
	boolean isAllReady();
	void sendTo(int toGamer, Protocol data);
	void moveTurn(int userLocation);
	boolean addStone(int[] stoneLocation, boolean b);
	void newGame();
	void exitRoomMaster(Protocol data);
	void subLastStone(int n);
	boolean isStart();
	void setStart(boolean isStart);
	int getUserCounter();
}
