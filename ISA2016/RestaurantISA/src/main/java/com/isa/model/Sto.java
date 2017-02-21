package com.isa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sto")
public class Sto implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "oznaka")
	private int oznaka;
	
	@Column(name = "segment")
	private String segemnt;
	
	@Column(name = "zauzetost")
	private Boolean zauzet;
	
	@Column(name = "brojmesta")
	private int brojmesta;
	
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
	
	public String getSegemnt() {
		return segemnt;
	}

	public void setSegemnt(String segemnt) {
		this.segemnt = segemnt;
	}
	
	public Boolean getZauzet() {
		return zauzet;
	}
	
	public void setZauzet(Boolean zauzet) {
		this.zauzet = zauzet;
	}
	
	public int getBrojmesta() {
		return brojmesta;
	}
	
	public void setBrojmesta(int brojmesta) {
		this.brojmesta = brojmesta;
	}
	
	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}
}
