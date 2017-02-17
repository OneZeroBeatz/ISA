package com.isa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isa.model.Restoran;

@Repository
@Transactional
public interface RestoranSkladiste extends JpaRepository<Restoran,Long> {
	List<Restoran> findById(Long gid);
	Page<Restoran> findAll(Pageable pageable);
	
}
