package com.isa.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "namirnica")
public class Namirnica implements Serializable{

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "naziv")
	private String naziv;

	@ManyToOne(optional = false)
	private PorudzbinaMenadzer porudzbinamenadzer;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "namirnica")
	private Set<StavkaPorudzbineMenadzera> listastavki;
	
	public Namirnica() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazivNamirnice() {
		return naziv;
	}

	public void setNazivNamirnice(String naziv) {
		this.naziv = naziv;
	}

	public PorudzbinaMenadzer getPorudzbinaMenadzer() {
		return porudzbinamenadzer;
	}
	
}
