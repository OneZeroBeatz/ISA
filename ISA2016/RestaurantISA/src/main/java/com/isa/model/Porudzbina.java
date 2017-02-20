package com.isa.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "porudzbina")
public class Porudzbina  {
	
	
	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "porudzbina")
	private Set<JeloUPorudzbini> jelovnik;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "porudzbina")
	private Set<PiceUPorudzbini> picovnik;
	
	//TODO: Ubaciti i sto za koji vazi ponuda
	
	@Column(name = "vremePrimanja")
//	@Type(type="date")
	private String vremePrimanja; 

	//TODO: Sacuvati datume kao datum a ne string
	
	@Column(name = "vremeNaplata")
//	@Type(type="date")
	private String vremeNaplate;
	
	public Porudzbina() {
		
	}

	public String getVremeNaplate() {
		return vremeNaplate;
	}
	
	public String getVremePrimanja() {
		return vremePrimanja;
	}
	
	public void setVremeNaplate(String vremeNaplate) {
		this.vremeNaplate = vremeNaplate;
	}
	
	public void setVremePrimanja(String vremePrimanja) {
		this.vremePrimanja = vremePrimanja;
	}
	
}
