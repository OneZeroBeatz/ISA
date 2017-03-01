package com.isa.model.korisnici;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.isa.model.Porudzbina;
import com.isa.model.Restoran;
import com.isa.model.SmenaUDanu;

@Entity
@DiscriminatorValue("KN")
public class Konobar extends Korisnik{

	@ManyToOne(optional = true, cascade = CascadeType.MERGE)
	private Restoran restoran;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "konobar")
	private Set<Porudzbina> porudzbine;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "konobar")
	private Set<Porudzbina> porudzbine1;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "konobar", cascade = {CascadeType.ALL})
	private Set<SmenaUDanu> smenaudanu;
	
	public Konobar() {
	}
	
	public Restoran getRestoran() {
		return restoran;
	}
	public Set<Porudzbina> getPorudzbine() {
		return porudzbine;
	}
	public Set<Porudzbina> getPorudzbine1() {
		return porudzbine1;
	}
	
	public void setPorudzbine(Set<Porudzbina> porudzbine) {
		this.porudzbine = porudzbine;
	}
	public void setPorudzbine1(Set<Porudzbina> porudzbine1) {
		this.porudzbine1 = porudzbine1;
	}
	
}
