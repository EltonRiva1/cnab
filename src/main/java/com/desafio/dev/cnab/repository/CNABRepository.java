package com.desafio.dev.cnab.repository;

import com.desafio.dev.cnab.model.CNAB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CNABRepository extends JpaRepository<CNAB, Long> {
}
