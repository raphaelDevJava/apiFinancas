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

import br.com.cotiinformatica.domain.models.dtos.ContaRequestDto;
import br.com.cotiinformatica.domain.models.dtos.ContaResponseDto;
import br.com.cotiinformatica.domain.services.interfaces.ContaDomainService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/contas")
public class ContaController {

	@Autowired ContaDomainService contaDomainService;
	
	@PostMapping
	public ContaResponseDto post(@RequestBody @Valid ContaRequestDto request) throws Exception {
		return contaDomainService.adicionar(request);
	}
	
	@PutMapping("{id}")
	public ContaResponseDto put(@PathVariable("id") Integer id, @RequestBody @Valid ContaRequestDto request) throws Exception {
		return contaDomainService.editar(id, request);
	}
	
	@DeleteMapping("{id}")
	public ContaResponseDto delete(@PathVariable("id") Integer id) throws Exception {
		return contaDomainService.excluir(id);
	}
	
	@GetMapping
	public List<ContaResponseDto> getAll() throws Exception {
		return contaDomainService.consultar();
	}
	
	@GetMapping("{id}")
	public ContaResponseDto getById(@PathVariable("id") Integer id) throws Exception {
		return contaDomainService.obterPorId(id);
	}
}
