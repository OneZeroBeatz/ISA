package com.isa.model;

import java.util.Date;
import java.util.List;

public class IzvestajJelo {

	private Date datumod;
	private Date datumdo;
	private int ocena;
	private Jelo jelo;
	private List<IzvestajKuvarJelo> izvestajKuvarJelo;
	
	public IzvestajJelo() {

	}

	public Date getDatumod() {
		return datumod;
	}

	public void setDatumod(Date datumod) {
		this.datumod = datumod;
	}

	public Date getDatumdo() {
		return datumdo;
	}

	public void setDatumdo(Date datumdo) {
		this.datumdo = datumdo;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public Jelo getJelo() {
		return jelo;
	}

	public void setJelo(Jelo jelo) {
		this.jelo = jelo;
	}

	public List<IzvestajKuvarJelo> getIzvestajKuvarJelo() {
		return izvestajKuvarJelo;
	}

	public void setIzvestajKuvarJelo(List<IzvestajKuvarJelo> izvestajKuvarJelo) {
		this.izvestajKuvarJelo = izvestajKuvarJelo;
	}

}



/*
@Entity
@Table(name = "izvestajjelo")
public class IzvestajJelo implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "datumod", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date datumod;

	@Column(name = "datumdo", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date datumdo;

	@Column(name = "ocena")
	private int ocena;

	@ManyToOne(optional = true)
	private Jelo jelo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "izvestajjelo")
	private Set<IzvestajKuvarJelo> izvestajKuvarJelo;
	
	public IzvestajJelo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatumod() {
		return datumod;
	}

	public void setDatumod(Date datumod) {
		this.datumod = datumod;
	}

	public Date getDatumdo() {
		return datumdo;
	}

	public void setDatumdo(Date datumdo) {
		this.datumdo = datumdo;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public Jelo getJelo() {
		return jelo;
	}

	public void setJelo(Jelo jelo) {
		this.jelo = jelo;
	}

}*/
