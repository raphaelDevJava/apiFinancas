package br.com.cotiinformatica.domain.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.models.dtos.CategoriaRequestDto;
import br.com.cotiinformatica.domain.models.dtos.CategoriaResponseDto;
import br.com.cotiinformatica.domain.models.entities.Categoria;
import br.com.cotiinformatica.domain.services.interfaces.CategoriaDomainService;
import br.com.cotiinformatica.infrastructure.repositories.CategoriaRepository;

@Service
public class CategoriaDomainServiceImpl implements CategoriaDomainService {

	@Autowired CategoriaRepository categoriaRepository;
	@Autowired ModelMapper modelMapper;
	
	@Override
	public CategoriaResponseDto adicionar(CategoriaRequestDto request) throws Exception {

		//REGRA DE NEGÓCIO: Não permitir o cadastro de categorias com o mesmo nome
		if(categoriaRepository.verifyExists(request.getNome())) {
			throw new IllegalArgumentException("Já existe uma categoria cadastrada com este nome. Tente outro.");
		}
		
		//capturando os dados da categoria
		var categoria = modelMapper.map(request, Categoria.class);
		
		//cadastrando no banco de dados
		categoriaRepository.save(categoria);
		
		//gerando os dados de resposta
		return modelMapper.map(categoria, CategoriaResponseDto.class);		
	}

	@Override
	public CategoriaResponseDto editar(Integer id, CategoriaRequestDto request) throws Exception {

		//buscando a categoria no banco de dados através do id
		//ou retornando erro caso a categoria não seja encontrada
		var categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));
		
		//modificando o nome da categoria
		categoria.setNome(request.getNome());
		
		//salvando a alteração no banco de dados
		categoriaRepository.save(categoria);
		
		//gerando os dados de resposta
		return modelMapper.map(categoria, CategoriaResponseDto.class);
	}

	@Override
	public CategoriaResponseDto excluir(Integer id) throws Exception {

		//buscando a categoria no banco de dados através do id
		//ou retornando erro caso a categoria não seja encontrada
		var categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));
		
		//excluindo a categoria
		categoriaRepository.delete(categoria);
		
		//gerando os dados de resposta
		return modelMapper.map(categoria, CategoriaResponseDto.class);
	}

	@Override
	public List<CategoriaResponseDto> consultar() throws Exception {

		var response = categoriaRepository.findAll()
				.stream()
				.map((categoria) -> modelMapper.map(categoria, CategoriaResponseDto.class))
				.collect(Collectors.toList());
		
		return response;
	}

	@Override
	public CategoriaResponseDto obterPorId(Integer id) throws Exception {

		//buscando a categoria no banco de dados através do id
		//ou retornando erro caso a categoria não seja encontrada
		var categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));
		
		//gerando os dados de resposta
		return modelMapper.map(categoria, CategoriaResponseDto.class);
	}	
}













