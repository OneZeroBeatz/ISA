package com.isa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isa.model.korisnici.MenadzerRestorana;

@Repository
@Transactional
public interface MenadzerRestoranaSkladiste extends JpaRepository<MenadzerRestorana, Long> {
	List<MenadzerRestorana> findById(Long gid);
	Page<MenadzerRestorana> findAll(Pageable pageable);
}
