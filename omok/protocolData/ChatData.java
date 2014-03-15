package protocolData;

import java.util.Vector;

public class ChatData implements Protocol {
	public static final short ENTER = 1000;
	public static final short LOGIN_CHECK = 1100;
	public static final short LOGIN_ERROR = 1200;
	public static final short MESSAGE = 2000;
	public static final short MESSAGE_SLIP = 2100;
	public static final short EXIT = 3000;
	public static final short SEND_USER_LIST = 4000;
	public static final short SEND_TOTAL_USER = 4100;
	
	private static final long serialVersionUID = 1L;

	private String name = null,
						   message = null;
	
	private String receiver;
	
	private short state = 0;

	private Vector<String> userList = null;
	
	
	public ChatData(String name, String message, short state) {
		this.name = name;
		this.message = message;
		this.state = state;
		if ( ChatData.SEND_USER_LIST == state)
			userList = new Vector<String>();
	}
	
	public ChatData(String receiverName, String name, String message, short state) {
		this(name, message, state);
		receiver = receiverName;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public short getState() {
		return state;
	}

	public Vector<String> getUserList() {
		return userList;
	}
 
	public String getName() {
		return name;
	}

	public String getMessage() {
		return message;
	}

	public void setUserList(Vector<String> userList) {
		this.userList =  null;
		this.userList =  userList;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name + " " + message + " " + state + "\n" + userList;
	}

	public String getReceiver() {
		return receiver;
	}

	public short getProtocol() {
		return state;
	}

	public void setProtocol(short exit) {
		state = exit;
	}
}

