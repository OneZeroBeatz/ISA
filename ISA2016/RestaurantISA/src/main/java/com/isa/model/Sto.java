package com.isa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.isa.model.korisnici.Konobar;

@Entity
@Table(name = "sto")
public class Sto implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "oznaka")
	private int oznaka;

	@ManyToOne(optional = false)
	private Restoran restoran;

	//private Konobar konobar;
	
	public Sto() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getOznaka() {
		return oznaka;
	}

	public void setOznaka(int oznaka) {
		this.oznaka = oznaka;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

}
