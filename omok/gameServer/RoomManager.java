package gameServer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class RoomManager {
	private List<GameRoomInterface> roomList;

	public RoomManager() {
		roomList = Collections
				.synchronizedList(new ArrayList<GameRoomInterface>());
	}

	public synchronized void addRoom(GameRoomInterface room) {
		roomList.add(room);
	}

	public synchronized void subRoom(int roomNumber) {
		for (GameRoomInterface temp : roomList) {
			if (temp.getNumber() == roomNumber) {
				roomList.remove(temp);
				this.subRoom(temp.getRoomName());
				
				break;
			}
		}
	}

	public synchronized void subRoom(String name) {
		for (GameRoomInterface temp : roomList)
			if (temp.getRoomName().equals(name)) {
				roomList.remove(temp);
			}
	}

	public GameRoomInterface get(int index) {
		return roomList.get(index);
	}

	public Vector<String> getStringList() {
		Vector<String> userList = new Vector<String>();
		
		
		
		for (GameRoomInterface temp : roomList) {
			if (temp.getUserCounter() == 0)
				userList.remove(temp);
			else
				userList.add(temp.getRoomName());
		}

		return userList;
	}

	public int size() {
		return roomList.size();
	}

	public ArrayList<GameRoomInterface> getCollection() {
		ArrayList<GameRoomInterface> temp = new ArrayList<GameRoomInterface>();

		for (GameRoomInterface list : roomList)
			temp.add(list);

		return temp;
	}
}
