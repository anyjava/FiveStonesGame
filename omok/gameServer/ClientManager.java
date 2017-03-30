package gameServer;

import java.util.ArrayList;
import java.util.Vector;

public class ClientManager {
	
	private ArrayList<GameServer> clientList;
	
	public ClientManager() {
		 clientList = new ArrayList<GameServer>();
	}
	
	public synchronized void addUser(GameServer clinet) {
		clientList.add(clinet);
	}
	
	public synchronized GameServer subUser(GameServer client) {
		GameServer returnInstance = null;
		for (GameServer temp : clientList) 
			if(temp.name.equals(client.name)) {
				returnInstance = temp;
				clientList.remove(temp);
			};
			
		return returnInstance;
	}

	public synchronized GameServer subUser(String name) {
		for (int i = 0; i < clientList.size(); i++) {
			if ((clientList.get(i).name).equals(name)) {
				clientList.remove(i);
			}
		}
		System.out.println("현재접속자수: " + clientList.size());
		LogFrame.print("현재접속자수: " + clientList.size());
		return null;
	}
	
	public GameServer get(int index) {
		return clientList.get(index);
	}
	
	public GameServer get(String name) {
		for (GameServer temp : clientList)
			if (temp.getUserName().equals(name))
				return temp;
		
		return null;
	}
	
	public Vector<String> getStringList() {
		Vector<String> userList = new Vector<String>();
		
		for (GameServer temp : clientList) 
			userList.add(temp.name);

		return userList;
	}
	
	public int size() {
		return clientList.size();
	}

	public ArrayList<GameServer> getCollection() {
		return clientList;
	}
	
	public ArrayList<GameServer> getCollection(int roomNumber) {
		ArrayList<GameServer> list = new ArrayList<GameServer>();
		
		for (GameServer temp : clientList)
			if(temp.getRoomNumber() == roomNumber)
				list.add(temp);
		
		return list;
		
	}
}