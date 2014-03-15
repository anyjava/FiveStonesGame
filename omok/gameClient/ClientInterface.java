package gameClient;

import gui.GuiInterface;

public interface ClientInterface {
	void sendMessage(String message, short state);
	void sendSlip(String to, String text, short message_slip);
	GuiInterface getGui();
	void changeGui(GuiInterface gui);
	void sendMessage(int[] is);
	void setSlipTo(String slipTo);
	boolean isRoomKing();
}
