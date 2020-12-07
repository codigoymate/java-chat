package com.codigoymate.chat.client;

import com.codigoymate.chat.StringMessage;
import com.codigoymate.chat.UserNameMessage;
import com.codigoymate.net.Message;
import com.codigoymate.net.client.ClientRunnable;

public class ChatClient extends ClientRunnable {

	private Chat chat;
	private String userName;
	
	public ChatClient(Chat chat, String address, int port, String userName) {
		super(address, port);
		this.chat = chat;
		this.userName = userName;
	}

	@Override
	public void receiveMessage(Message msg) {
		switch (msg.getType()) {
		case "string":
			chat.getMainWindow().getOutput().append(((StringMessage) msg).getString() + "\n");
			break;
		case "connected":
			sendMessage(new UserNameMessage(userName));
			break;

		default:
			break;
		}
	}

}
