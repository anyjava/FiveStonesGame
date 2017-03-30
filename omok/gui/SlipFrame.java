package gui;

import gameClient.ClientInterface;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import protocolData.ChatData;


public class SlipFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private boolean isSend;
	
	private ClientInterface client;
	
	private String message;
	
	protected String to;
	
	private JFrame frame = this;

	private JPanel m_topPan = new JPanel(), m_bottomPan = new JPanel(),
			m_centerPan = new JPanel();

	private JLabel m_labelID, m_labelMessage = new JLabel("  message");

	private JTextField m_inputID = new JTextField(10);

	private final JTextArea m_message = new JTextArea(5, 25);
	
	private JButton okButton = new JButton("OK"), cancelButton = new JButton(
			"Cancel");
	
	/*
	 *  쪽지 보낼때의 생성자.
	 */
	public SlipFrame(final String to, String message, boolean isSend, final ClientInterface client) {

		this.to = to;
		this.message = message;
		this.client = client;
		
		this.isSend = isSend;

		if (isSend) { // send
			this.setTitle("쪽지 보내기");
			m_labelID = new JLabel("To...       ");
			m_inputID.setText(to);
			
		} else {
			this.setTitle("받은쪽지");
			m_labelID = new JLabel("From...    "); // recive
			m_message.setText(message);
		}

		showComponent();

		if (isSend) {
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					client.sendSlip(m_inputID.getText(), m_message.getText(),
							 ChatData.MESSAGE_SLIP);
					frame.setVisible(false);
				}
			});

			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
				}
			});
		} else {
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
				}
			});
		}
	}
	
	/*
	 *  쪽지 받을때의 생성자.
	 */
	public SlipFrame(String sender, String message, boolean isSend) {
		this("", message, isSend, null);
		m_inputID.setText(sender);
	}

	private void showComponent() {
		m_topPan.add(m_labelID);
		m_topPan.add(m_inputID);
		m_topPan.setLayout(new FlowLayout(FlowLayout.LEFT));
		m_inputID.setEditable(false);

		m_centerPan.add(m_labelMessage);
		
		
		m_message.setLineWrap(true);
		m_centerPan.add(new JScrollPane(m_message,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		m_centerPan.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));

		m_bottomPan.add(okButton);
		if (isSend)
			m_bottomPan.add(cancelButton);

		Container cp = this.getContentPane();
		cp.add(m_topPan, BorderLayout.NORTH);
		cp.add(m_centerPan, BorderLayout.CENTER);
		cp.add(m_bottomPan, BorderLayout.SOUTH);

		this.setVisible(true);
		this.pack();
		this.setResizable(false);			
		this.setAlwaysOnTop(true);
	}
}