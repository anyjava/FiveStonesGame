package protocolData;

import java.util.Vector;

public class GameData implements Protocol {

	private static final long serialVersionUID = 1L;

	public static final short SEND_STONE_LOCATION = 7100;
	public static final short SEND_RESULT = 7200;
	public static final short SEND_GAME_MESSAGE = 7300;
	public static final short EXIT_THEGAME = 7999;
	public static final short REQUEST_RETURN = 8000;
	public static final short RESPONSE_RETURN = 8100;
	
	
	private String name;
	
	private int[] stoneLocation;
	private boolean isBlack;
	private boolean isReturn;
	private short protocol;

	
	public GameData(String name, boolean isBlack, short protocol) {
		this.name = name;
		this.isBlack = isBlack;
		this.protocol = protocol;
	}
	
	public GameData(String isReturn, short protocol) {
		if (isReturn.equals("YES"))
			this.isReturn = true;
		else if (isReturn.equals("NO")) 
			this.isReturn = false;
		
		this.protocol = protocol;
	}
	
	public GameData(int[] stoneLocation, boolean isBlack, short protocol) {
		this.protocol = protocol;
		this.isBlack = isBlack;
		this.stoneLocation = stoneLocation;
	}

	public GameData(int[] location, short protocol) {
		this.protocol = protocol;
		this.stoneLocation = location;

	}

	public short getProtocol() {
		return protocol;
	}

	public Vector<String> getUserList() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		return name;
	}

	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUserList(Vector<String> userList) {
		// TODO Auto-generated method stub
	}

	public void setMessage(String message) {
		// TODO Auto-generated method stub
	}

	public boolean isBlack() {
		return isBlack;
	}

	public void setBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}

	public int[] getStoneLocation() {
		return stoneLocation;
	}
	
	@Override
	public String toString() {
		try {
			return "GameRoomData == (" + stoneLocation[0] + "," + stoneLocation[1]+ ") " + isBlack
				+ "protocol : " + protocol;
			
		} catch(NullPointerException e) {
			return "GameRoomData == " +protocol;
		}
	}

	public boolean isReturn() {
		return isReturn;
	}

}
