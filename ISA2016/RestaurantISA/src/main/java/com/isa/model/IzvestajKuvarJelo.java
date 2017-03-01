package com.isa.model;

import com.isa.model.korisnici.Kuvar;

public class IzvestajKuvarJelo {

	private int prosecnaOcena;
	private Kuvar kuvar;
	private IzvestajJelo izvestajjelo;

	public IzvestajKuvarJelo() {

	}

	public int getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(int prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public Kuvar getKuvar() {
		return kuvar;
	}

	public void setKuvar(Kuvar kuvar) {
		this.kuvar = kuvar;
	}

	public IzvestajJelo getIzvestajjelo() {
		return izvestajjelo;
	}

	public void setIzvestajjelo(IzvestajJelo izvestajjelo) {
		this.izvestajjelo = izvestajjelo;
	}

}


/*
@Entity
@Table(name = "izvestajkuvarjelo")
public class IzvestajKuvarJelo implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "prosecnaocena")
	private int prosecnaOcena;

	@ManyToOne(optional = true)
	private Kuvar kuvar;

	@ManyToOne(optional = true)
	private IzvestajJelo izvestajjelo;

	public IzvestajKuvarJelo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(int prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public Kuvar getKuvar() {
		return kuvar;
	}

	public void setKuvar(Kuvar kuvar) {
		this.kuvar = kuvar;
	}

	public IzvestajJelo getIzvestajjelo() {
		return izvestajjelo;
	}

	public void setIzvestajjelo(IzvestajJelo izvestajjelo) {
		this.izvestajjelo = izvestajjelo;
	}

}
*/