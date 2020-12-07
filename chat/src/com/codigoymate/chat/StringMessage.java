package com.codigoymate.chat;

import com.codigoymate.net.Message;

public class StringMessage implements Message {

	private static final long serialVersionUID = 3561801876810862439L;
	
	private String string;
	
	public StringMessage() {
		
	}
	
	public StringMessage(String string) {
		this.string = string;
	}
	
	@Override
	public String getType() {
		return "string";
	}
	
	public String getString() {
		return string;
	}

}
