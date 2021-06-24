package com.os.linesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.os.linesystem.model.Tecnico;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
