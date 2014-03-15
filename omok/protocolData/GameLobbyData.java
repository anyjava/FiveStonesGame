package protocolData;

public class GameLobbyData implements Protocol{

	private static final long serialVersionUID = 1L;
	
	public static final short GAME_READY = 6100;
	public static final short CANCEL_READY = 6199;
	public static final short GAME_START = 6200;
	public static final short EXIT_ROOM = 9100;
	
	
	private short protocol;
	private String roomName;
	private String userName, roomKing;
	private String message; 
	
	public GameLobbyData(String name, String roomName, short protocol) {
		this.userName = name;
		this.roomName = roomName;
		this.protocol = protocol;
	}

	public short getProtocol() {
		return protocol;
	}

	public String getName() {
		return userName;
	}

	public String getMessage() {
		return message;
	}

	public void setName(String name) {
		this.userName = name;
	}

	public String getRoomName() {
		return roomName;
	}
	
	public String toString() {
		return "{GameLobbyData} " + userName + " " + roomName + " " + protocol;
	}

	public String getRoomKing() {
		return roomKing;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public void setRoomKing(String roomKing) {
		this.roomKing = roomKing;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
