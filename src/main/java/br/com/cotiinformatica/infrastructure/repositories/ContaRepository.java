package br.com.cotiinformatica.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.domain.models.entities.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

}
