package br.com.cotiinformatica.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.domain.models.dtos.CategoriaRequestDto;
import br.com.cotiinformatica.domain.models.dtos.CategoriaResponseDto;
import br.com.cotiinformatica.domain.services.interfaces.CategoriaDomainService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/categorias")
public class CategoriaController {

	@Autowired CategoriaDomainService categoriaDomainService;
	
	@PostMapping
	public CategoriaResponseDto post(@RequestBody @Valid CategoriaRequestDto request) throws Exception {
		return categoriaDomainService.adicionar(request);
	}
	
	@PutMapping("{id}")
	public CategoriaResponseDto put(@PathVariable("id") Integer id, @RequestBody @Valid CategoriaRequestDto request) throws Exception {
		return categoriaDomainService.editar(id, request);
	}
	
	@DeleteMapping("{id}")
	public CategoriaResponseDto delete(@PathVariable("id") Integer id) throws Exception {
		return categoriaDomainService.excluir(id);
	}
	
	@GetMapping
	public List<CategoriaResponseDto> getAll() throws Exception {
		return categoriaDomainService.consultar();
	}
	
	@GetMapping("{id}")
	public CategoriaResponseDto getById(@PathVariable("id") Integer id) throws Exception {
		return categoriaDomainService.obterPorId(id);
	}
}







