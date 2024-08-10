package br.com.cotiinformatica.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.domain.models.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

	/*
	 * JPQL -> Java Persistence Query Language
	 */
	@Query("SELECT COUNT(c) > 0 FROM Categoria c WHERE c.nome = :nome")
	boolean verifyExists(@Param("nome") String nome);
}
