package gameServer;

import java.util.ArrayList;
import java.util.Vector;

import protocolData.*;

public class Lobby implements LobbyInterface {

	private ClientManager userList;
	private RoomManager roomList;
	
	public Lobby() {
		userList = new ClientManager();
		roomList = new RoomManager();
	}

	public void broadcasting(Protocol data) {
		System.out.println("in broadcast : " + data);
		LogFrame.print("in broadcast : " + data);
		
		for (GameServer temp : userList.getCollection())
			if(temp.getUserLocation() == ServerInterface.LOBBY) {
				try {
					temp.sendMessage(data);
				} catch (Exception e) {
					// Null 예외처리
					userList.subUser( temp );
					System.out.println(temp.getUserName() + "을 연결을 해제 하였습니다.");
				}
			}
	}
	
	public void subSocket(String name) {
		userList.subUser(name);
	}
	
	public void sendSlip(ChatData data) {
		System.out.println("in sendSlip : " + data.getReceiver());
		LogFrame.print("in sendSlip : " + data.getReceiver());
		try {
			userList.get(data.getReceiver()).sendMessage(data);
		} catch(Exception e) {
			System.out.println("[Throw] Send Slip Exception~!!!");
			LogFrame.print("[Throw] Send Slip Exception~!!!");
			
			userList.subUser( data.getReceiver() );
		}
		
	}
	

	public void addGamer(GameServer gameServer) {
		userList.addUser(gameServer);
	}
	
	public void addRoom(GameRoomInterface room) {
		roomList.addRoom(room);
	}

	protected ArrayList<GameServer> getClientList_Lobby() {
		return userList.getCollection();
	}
	

	protected ArrayList<GameRoomInterface> getRoomList() {
		return roomList.getCollection();
	}
	

	public ClientManager getUserList() {
		return userList;
	}

	public void subRoom(int roomNumber) {
		roomList.subRoom(roomNumber);
		System.out.println("SUB ROOM succeed!!");
	}

	public void printState() {
		System.out.println("=========== state ==============");
		System.out.println("user List : " + userList.getStringList());
		System.out.println("Client : " + userList.getCollection());
		System.out.println("Room : " + roomList.getCollection());
		
		LogFrame.print("=========== state ==============");
		LogFrame.print("user List : " + userList.getStringList());
		LogFrame.print("Client : " + userList.getCollection());
		LogFrame.print("Room : " + roomList.getCollection());
		
	}

	public GameRoomInterface getSelectedRoom(String roomName) {
		for(GameRoomInterface temp : roomList.getCollection())
			if(temp.getRoomName().equals(roomName))
				return temp;
		
		return null;
	}

	public Vector<String> getRoomListAsString() {
		return roomList.getStringList();
	}

	public Vector<String> getStringUser() {
		Vector<String> user = new Vector<String>();
		
		for (GameServer temp : userList.getCollection())
			if(temp.getUserLocation() == ServerInterface.LOBBY)
				user.add(temp.getUserName());
		
		return user;
	}
	
}