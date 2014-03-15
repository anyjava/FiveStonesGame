package protocolData;

import java.util.Vector;

public class LobbyData implements Protocol{

	private static final long serialVersionUID = 1L;
	
	public static final short SEND_ROOMLIST = 5000;
	public static final short ENTER_TO_ROOM = 5100;
	public static final short CREATE_ROOM = 5200;
	public static final short EXIT_GAME = 9999;
	
	private String name;
	private Vector<String> roomList, userList;
	private String RoomName;
	private int roomNumber;
	private short protocol;
	
	public LobbyData(String name, String roomName, short protocol) {
		this.name = name;
		this.RoomName = roomName;
		this.protocol = protocol;
	}
	
	public LobbyData(Vector<String> roomList, short protocol) {
		this.roomList = roomList;
		this.protocol = protocol;
	}
	
	public Vector<String> getRoomList() {
		return roomList;
	}
	
	public String getRoomName() {
		return RoomName;
	}

	public short getProtocol() {
		return protocol;
	}
	
	public String toString() {
		return "Lobby Data " + name + " " + RoomName + " roomList : " + roomList + " " + protocol; 
	}
	
	public String getName() {
		return name;
	}

	public String getMessage() {
		return RoomName;
	}

	public void setName(String name) {
		this.name = name;
		
	}

	public Vector<String> getUserList() {
		return userList;
	}
	

	public void setUserList(Vector<String> userList) {
		this.userList = new Vector<String>();
		this.userList = userList;
	}

	public int getRoomNumber() {
		return roomNumber;
	}
	

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void setMessage(String message) {
		// TODO Auto-generated method stub
		
	}
	
}
