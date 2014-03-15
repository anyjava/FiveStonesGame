package gameServer;


import java.util.Vector;

import protocolData.ChatData;
import protocolData.Protocol;

public interface ServerInterface {
    static int LOBBY = 1;
	static int IN_GAME_ROOMKING = 2;
	static int IN_GAME_CRHARANGER = 3;
	static int IN_GAME_VIWER = 4;
	
	void broadcasting(Protocol data);
	void subSocket(String name);
	void sendSlip(ChatData data);
	ClientManager getUserList();
	Vector<String> getStringUser();
}
