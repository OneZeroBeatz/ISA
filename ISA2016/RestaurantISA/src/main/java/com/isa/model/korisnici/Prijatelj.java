package com.isa.model.korisnici;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "listaPrijatelja")
public class Prijatelj {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "idGosta")
	private Long idGosta;
	
	@Column(name = "idPrijatelja")
	private Long idPrijatelja;

	public Prijatelj(Long idGosta, Long idPrijatelja) {
		super();
		this.idGosta = idGosta;
		this.idPrijatelja = idPrijatelja;
	}
	
	public Long getIdGosta() {
		return idGosta;
	}
	
	public void setIdGosta(Long idGosta) {
		this.idGosta = idGosta;
	}
	
	public Long getIdPrijatelja() {
		return idPrijatelja;
	}
	
	public void setIdPrijatelja(Long idPrijatelja) {
		this.idPrijatelja = idPrijatelja;
	}

}
