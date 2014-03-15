package gui;

import gui.GameRoomGui.GameBoardCanvas;

import java.util.Vector;

public interface RoomGuiInter extends PanelInterface {
	int BLACK_STONE = 0;
	int WHITE_STONE = 1;
	
	void drawStone(int[] stoneLocation, boolean b);
	void backOneStep(int n);
	void setGameRoomInfo(String info);
	void newGame();
	void setRoomList(Vector<String> roomList);
	GameBoardCanvas getM_board();
}
