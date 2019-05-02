package br.com.productserver.models;

public class MessageResponse {

	private String message;

	public MessageResponse() {}
	
	public MessageResponse(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toJson() {
		return "{\"message\": \""+message+"\"}";
	}

	@Override
	public String toString() {
		return "{\"message\": \""+message+"\"}";
	}

	public String toXML() {
		return "<message>"+message+"</message>";
	}	
}