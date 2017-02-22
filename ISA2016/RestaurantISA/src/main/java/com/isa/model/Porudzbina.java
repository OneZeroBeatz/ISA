package com.isa.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.isa.model.korisnici.Konobar;
import com.isa.model.korisnici.Sanker;

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
	
	@ManyToOne(optional = true)
	private Restoran restoran;
	
	@ManyToOne(optional = true, cascade = CascadeType.MERGE)
	private Konobar konobar;
	
	@ManyToOne(optional = true, cascade = CascadeType.MERGE)
	private Sto sto;
	
	@ManyToOne(optional = true, cascade = CascadeType.ALL)
	private Sanker sanker;
	
	@Column(name = "spremna_jela")
	private boolean spremnaJela; 
	
	@Column(name = "spremna_pica")
	private boolean spremnaPica; 
	
	
	@Column(name = "vremeprimanja")
//	@Type(type="date")
	private String vremePrimanja; 

	//TODO: Sacuvati datume kao datum a ne string
	
	@Column(name = "vremenaplate")
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
	
	public Restoran getRestoran() {
		return restoran;
	}
	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}
	
	public Long getId() {
		return id;
	}
	
	public Sanker getSanker() {
		return sanker;
	}
	public void setSanker(Sanker sanker) {
		this.sanker = sanker;
	}

	public boolean isSpremnaJela() {
		return spremnaJela;
	}
	
	public boolean isSpremnaPica() {
		return spremnaPica;
	}
	
	public void setSpremnaJela(boolean spremnaJela) {
		this.spremnaJela = spremnaJela;
	}
	
	public void setSpremnaPica(boolean spremnaPica) {
		this.spremnaPica = spremnaPica;
	}
	
	
	public Sto getSto() {
		return sto;
	}
	public void setSto(Sto sto) {
		this.sto = sto;
	}
	
	public Konobar getKonobar() {
		return konobar;
	}
	public void setKonobar(Konobar konobar) {
		this.konobar = konobar;
	}
	
}
