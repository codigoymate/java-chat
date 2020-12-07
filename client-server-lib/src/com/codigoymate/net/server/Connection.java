package com.codigoymate.net.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.codigoymate.net.AbstractNetRunnable;
import com.codigoymate.net.Message;

public abstract class Connection extends AbstractNetRunnable {

	protected Socket socket;
	
	public Connection(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void start() {
		
	}

	@Override
	public void process() {
		try {
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			
			Object obj = in.readObject();
			
			if (obj instanceof Message) {
				receiveMessage((Message) obj);
			}
			
		} catch (IOException e) {
			running = false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public abstract void receiveMessage(Message obj);
	
	public void sendMessage(Message obj) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() {
		super.stop();
		
		try {
			if (!socket.isClosed())
				socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
