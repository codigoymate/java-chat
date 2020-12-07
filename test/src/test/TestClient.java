package test;

import com.codigoymate.net.Message;
import com.codigoymate.net.client.ClientRunnable;

public class TestClient extends ClientRunnable {

	public TestClient() {
		super("localhost", 2020);
	}

	public static void main(String[] args) {
		TestClient client = new TestClient();
		Thread thread = new Thread(client);
		thread.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		
		client.sendMessage(new StringMessage("Hola servidor"));
	}

	@Override
	public void receiveMessage(Message msg) {
		switch (msg.getType()) {
		case "string": {
			StringMessage strmsg = (StringMessage) msg;
			System.out.println(strmsg.getString());
			} break;
		}
	}
	
}
