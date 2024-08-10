package br.com.cotiinformatica.domain.services.interfaces;

import java.util.List;

import br.com.cotiinformatica.domain.models.dtos.CategoriaRequestDto;
import br.com.cotiinformatica.domain.models.dtos.CategoriaResponseDto;

public interface CategoriaDomainService {

	CategoriaResponseDto adicionar(CategoriaRequestDto request) throws Exception;

	CategoriaResponseDto editar(Integer id, CategoriaRequestDto request) throws Exception;

	CategoriaResponseDto excluir(Integer id) throws Exception;

	List<CategoriaResponseDto> consultar() throws Exception;

	CategoriaResponseDto obterPorId(Integer id) throws Exception;
}
