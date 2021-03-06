package com.codigoymate.chat.server;

import java.net.Socket;

import com.codigoymate.chat.StringMessage;
import com.codigoymate.chat.UserNameMessage;
import com.codigoymate.net.Message;
import com.codigoymate.net.server.Connection;

public class UserConnection extends Connection {

	private ChatServer server;
	private String userName;
	
	public UserConnection(ChatServer server, Socket socket) {
		super(socket);
		this.server = server;
	}

	@Override
	public void receiveMessage(Message msg) {
		switch (msg.getType()) {
		case "user-name":
			userName = ((UserNameMessage) msg).getUserName();
			
			server.getConnections().forEach(u -> u.sendMessage(
					new StringMessage(userName + " conectado.")));
			break;
		case "string": {
			String strmsg = userName + ": " + ((StringMessage)msg).getString();
			
			server.getConnections().forEach(u -> u.sendMessage(
					new StringMessage(strmsg)));
		}break;
		}
	}

}
