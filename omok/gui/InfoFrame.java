package gui;

import java.awt.Container;

import javax.swing.JTextArea;
import javax.swing.JWindow;

public class InfoFrame extends JWindow {

	private static final long serialVersionUID = 1L;
	
	private Container cp = getContentPane();
	private JTextArea m_message = new JTextArea();
	
	public InfoFrame(String message) {
		cp.add(m_message);
		cp.setLayout(null);
		m_message.setBounds(10,10,180,80);
		m_message.setEditable(false);
		m_message.setLineWrap(true);
		
		m_message.setText(message);
		
		setBounds(200,300,200,100);
		setAlwaysOnTop(true);
	}
	

	
	public static void main(String[] args) {
		new InfoFrame("ASDFSDFasdfasdfas\ndfasdfsdfasdfsSDF").setVisible(true);
	}
}
