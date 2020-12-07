package com.codigoymate.chat.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.codigoymate.chat.ConnectedMessage;
import com.codigoymate.net.server.ServerRunnable;

public class ChatServer extends ServerRunnable {

	private List<UserConnection> connections = new ArrayList<>();
	
	public ChatServer(int port) {
		super(port);
	}

	@Override
	public void connectionAccepted(Socket client) {
		UserConnection conn = new UserConnection(this, client);
		Thread thread = new Thread(conn);
		thread.start();
		
		connections.add(conn);
		
		conn.sendMessage(new ConnectedMessage());
	}
	
	public List<UserConnection> getConnections() {
		return connections;
	}
	
	@Override
	public void stop() {
		
		connections.forEach(c -> {
			if (c.isRunning()) {
				c.stop();
			}
		});
		
		super.stop();
	}
	
	public static void main(String[] args) {
		ChatServer server = new ChatServer(2020);
		Thread thread = new Thread(server);
		
		thread.start();
	}

}
