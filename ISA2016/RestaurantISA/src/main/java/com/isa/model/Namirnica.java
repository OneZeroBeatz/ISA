package com.isa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "namirnica")
public class Namirnica implements Serializable{

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "naziv")
	private String nazivNamirnice;

	public Namirnica() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazivNamirnice() {
		return nazivNamirnice;
	}

	public void setNazivNamirnice(String nazivNamirnice) {
		this.nazivNamirnice = nazivNamirnice;
	}

}
