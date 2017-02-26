package com.isa.pomocni;

public class Poruka {
	
	private String message;
	
	public Poruka(){}

	public Poruka(String message){
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}	
}
