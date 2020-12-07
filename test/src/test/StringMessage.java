package test;

import com.codigoymate.net.Message;

public class StringMessage implements Message {

	private static final long serialVersionUID = 5357136987137006539L;
	private String string;
	
	public StringMessage(String string) {
		this.string = string;
	}
	
	public String getString() {
		return string;
	}
	
	public void setString(String string) {
		this.string = string;
	}
	
	@Override
	public String getType() {
		return "string";
	}

}
