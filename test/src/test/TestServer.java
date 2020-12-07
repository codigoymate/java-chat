package test;

import java.net.Socket;

import com.codigoymate.net.server.ServerRunnable;

public class TestServer extends ServerRunnable {

	public TestServer() {
		super(2020);
	}

	@Override
	public void connectionAccepted(Socket client) {
		User user = new User(client);
		Thread thread = new Thread(user);
		thread.start();
	}
	
	public static void main(String[] args) {
		TestServer server = new TestServer();
		Thread thread = new Thread(server);
		
		thread.start();
	}

}
