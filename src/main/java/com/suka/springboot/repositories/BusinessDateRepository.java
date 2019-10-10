package com.suka.springboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suka.springboot.BusinessDate;

@Repository
public interface BusinessDateRepository extends JpaRepository<BusinessDate, Long> {
	
	public Optional<BusinessDate> findById(Long id);
}
