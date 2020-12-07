package com.codigoymate.chat;

import com.codigoymate.net.Message;

public class ConnectedMessage implements Message {
	private static final long serialVersionUID = 6779505967412713627L;

	@Override
	public String getType() {
		return "connected";
	}

}
