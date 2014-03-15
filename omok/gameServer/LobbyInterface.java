package gameServer;

import java.util.Vector;

public interface LobbyInterface extends ServerInterface {

	Vector<String> getRoomListAsString();
	void addRoom(GameRoomInterface room);
	void addGamer(GameServer server);
	void subRoom(int roomNumber);
	void printState();
	GameRoomInterface getSelectedRoom(String roomName);

}
