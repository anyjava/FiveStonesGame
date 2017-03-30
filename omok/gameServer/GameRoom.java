package gameServer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import protocolData.ChatData;
import protocolData.Protocol;

public class GameRoom implements GameRoomInterface , Serializable {
	
	private static final long serialVersionUID = 1L;
	private static int roomNO = 1;
	
	private ClientManager userList;
	private int number;
	private String roomName;
	
	private StoneAlgol analysisStone;
	private boolean isStart = false;
	
	public GameRoom(String roomName, ClientManager userList) {
		this.roomName = roomName;
		this.userList = userList;
		this.analysisStone = new StoneAlgol();
		number = roomNO++;
	}
	
	public void broadcasting(Protocol data) {
		System.out.println("in broadcast : " + data);
		LogFrame.print("in broadcast : " + data);
		
		for (GameServer temp : userList.getCollection(number)) {
			if (!(temp.getUserLocation() == ServerInterface.LOBBY)) { 
				try {
					temp.sendMessage(data);
				} catch (Exception e) {
					userList.subUser( temp );
					System.out.println( temp.getName() + "의 연결을 끊었습니다." );
				}
			}
		}
	}

	public void sendSlip(ChatData data) {
		try {
			userList.get(data.getReceiver()).sendMessage(data);
		} catch (Exception e) {
			userList.subUser( data.getReceiver() );
			System.out.println( "의 연결을 끊었습니다." );
		}		
	}

	public int getNumber() {
		return number;
	}

	public String getRoomName() {
		return roomName;
	}
	
	public ClientManager getUserList() {
		return userList;
	}

	public String getRoomKingName() {
		return null; 
	}

	public Vector<String> getStringUser() {
		Vector<String> stringList = new Vector<String>();
		ArrayList<GameServer> gamerList = userList.getCollection(number);
		
		try {
			stringList.add(find(gamerList, ServerInterface.IN_GAME_ROOMKING).getUserName());
		} catch (NullPointerException e) {
			stringList.add("empty!");
		}

		try {
			stringList.add(find(gamerList, ServerInterface.IN_GAME_CRHARANGER).getUserName());
		} catch (NullPointerException e) {
			stringList.add("empty!");
		}
		

		for(GameServer temp : gamerList)
			if (temp.getUserLocation() == ServerInterface.IN_GAME_VIWER)
				stringList.add(temp.getUserName());
	
		return stringList;
	}

	private GameServer find(ArrayList<GameServer> userList, int location) {
		for(GameServer temp : userList)
			if (temp.getUserLocation() == location)
				return temp;
			
		return null;		
		
	}

	public String toString() {
		return roomName;
	}

	public void subSocket(String name) {
		userList.subUser(name);
	}

	public void subRoom() {
		// TODO Auto-generated method stub
		
	}

	public boolean isAllReady() {
		for(GameServer temp : userList.getCollection(number))
			if(!temp.isReady()) return false;
		
		return true;
	}

	public void sendTo(int toGamer, Protocol data) {
		GameServer reciver = find(userList.getCollection(number), toGamer); 
		try {
			reciver.sendMessage(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void moveTurn(int userLocation) {
		if(userLocation == IN_GAME_ROOMKING) userLocation = IN_GAME_CRHARANGER;
		else userLocation = IN_GAME_ROOMKING;
		
		for(GameServer temp : userList.getCollection(number))
			if(temp.getUserLocation() == userLocation) temp.setMyTurn(!temp.isMyTurn());
	}

	public boolean addStone(int[] stoneLocation, boolean isBlack) {
		int result = analysisStone.addStone(stoneLocation, isBlack);
		
		if (result == 1) return true;
		else return false;

	}
	
	public void subLastStone(int n) {
		analysisStone.subLastStone();
		if (n==2) subLastStone(1);
	}

	public void newGame() {
		analysisStone = new StoneAlgol();
		
	}

	public void exitRoomMaster(Protocol data) {
		for(GameServer temp : userList.getCollection(this.number)) 
			if (!(temp.getUserLocation() == ServerInterface.LOBBY)) 
				temp.toGameServer(data);
	}

	public boolean isStart() {
		return isStart;
	}
	

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public int getUserCounter() {
		return userList.getCollection(this.number).size();
	}
	
}