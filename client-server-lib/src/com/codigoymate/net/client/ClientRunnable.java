package com.codigoymate.net.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.codigoymate.net.AbstractNetRunnable;
import com.codigoymate.net.Message;

public abstract class ClientRunnable extends AbstractNetRunnable {

	private String address;
	private int port;
	private Socket socket;
	
	public ClientRunnable(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	@Override
	public void start() {
		try {
			socket = new Socket(address, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
	
	@Override
	public void stop() {
		super.stop();
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
