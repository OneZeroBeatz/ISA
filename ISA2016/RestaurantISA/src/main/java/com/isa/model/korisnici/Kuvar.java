package com.isa.model.korisnici;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.isa.model.JeloUPorudzbini;
import com.isa.model.Porudzbina;
import com.isa.model.Restoran;

@Entity
@DiscriminatorValue("KUV")
public class Kuvar extends Korisnik{

	@ManyToOne(optional = true)
	private Restoran restoran;
		
	@Enumerated(EnumType.STRING)
	@Column(name = "tipkuvara")
	private TipKuvara tipKuvara;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "kuvar")
	private Set<JeloUPorudzbini> jelovnik;
	
	
	public Kuvar(){
		
	}

	public Restoran getRestoran() {
		return restoran;
	}
	
	public TipKuvara getTipKuvara() {
		return tipKuvara;
	}
	public void setTipKuvara(TipKuvara tipKuvara) {
		this.tipKuvara = tipKuvara;
	}
	
	

	
}
