package com.isa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jelo")
public class Jelo {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "naziv")
	private String naziv;
	
	@Column(name = "opis")
	private String opis;
	
	@Column(name = "cena")
	private Float cena;
	
	public Jelo() {
		
	}
}
