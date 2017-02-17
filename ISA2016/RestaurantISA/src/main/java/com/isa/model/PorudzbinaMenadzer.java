package com.isa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "porudzbinaMenadzer")
public class PorudzbinaMenadzer implements Serializable{

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "od")
	@Temporal(TemporalType.DATE)
	private Date aktivnoOd;
	
	@Column(name = "do")
	@Temporal(TemporalType.DATE)
	private Date aktivnoDo;
	
	
	// TODO: Srediti ovo.. DODATI NOVU TABELU!
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "namirnica")
	//private Set<Namirnica> listaNamirnica;
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "pice")
	//private Set<Pice> listaPica;
	
	public PorudzbinaMenadzer() {
		
	}
	
}
