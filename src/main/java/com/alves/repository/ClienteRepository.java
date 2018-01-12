package com.alves.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alves.model.Cliente;

/**
 * 
 * @author alves
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
}
