package test;

import java.net.Socket;

import com.codigoymate.net.Message;
import com.codigoymate.net.server.Connection;

public class User extends Connection {

	public User(Socket socket) {
		super(socket);
	}

	@Override
	public void receiveMessage(Message msg) {
		switch (msg.getType()) {
		case "string": {
			StringMessage strmsg = (StringMessage) msg;
			strmsg.setString("Echo: " + strmsg.getString());
			this.sendMessage(strmsg);
			} break;
		}
	}

}
