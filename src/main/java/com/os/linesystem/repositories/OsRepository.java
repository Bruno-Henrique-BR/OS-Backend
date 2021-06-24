package com.os.linesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.os.linesystem.model.Os;

@Repository
public interface OsRepository extends JpaRepository<Os, Integer> {

}
