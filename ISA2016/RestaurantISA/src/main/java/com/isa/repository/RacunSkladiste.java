package com.isa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isa.model.RacunKonobar;

@Repository
@Transactional
public interface RacunSkladiste extends JpaRepository<RacunKonobar, Serializable> {

	RacunKonobar save(RacunKonobar racunKonobar);


}
