package com.isa.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//TODO impl
public class Restoran {

	@Id
	@GeneratedValue
	private Long id;
	
	public Restoran() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
