package com.codigoymate.chat;

import com.codigoymate.net.Message;

public class UserNameMessage implements Message {

	private static final long serialVersionUID = -3133139179437116745L;
	
	private String userName;
	
	public UserNameMessage() {
		
	}
	
	public UserNameMessage(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String getType() {
		return "user-name";
	}
	
	public String getUserName() {
		return userName;
	}

}
