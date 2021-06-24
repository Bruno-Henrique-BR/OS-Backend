package com.os.linesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.os.linesystem.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
