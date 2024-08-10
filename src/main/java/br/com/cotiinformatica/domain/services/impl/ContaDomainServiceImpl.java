package br.com.cotiinformatica.domain.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.models.dtos.ContaRequestDto;
import br.com.cotiinformatica.domain.models.dtos.ContaResponseDto;
import br.com.cotiinformatica.domain.models.entities.Conta;
import br.com.cotiinformatica.domain.models.enums.TipoConta;
import br.com.cotiinformatica.domain.services.interfaces.ContaDomainService;
import br.com.cotiinformatica.infrastructure.repositories.CategoriaRepository;
import br.com.cotiinformatica.infrastructure.repositories.ContaRepository;

@Service
public class ContaDomainServiceImpl implements ContaDomainService {

	@Autowired ContaRepository contaRepository;
	@Autowired CategoriaRepository categoriaRepository;
	@Autowired ModelMapper modelMapper;
	
	@Override
	public ContaResponseDto adicionar(ContaRequestDto request) throws Exception {

		//verificar se a categoria existe no banco de dados
		var categoria = categoriaRepository.findById(request.getCategoriaId())
				.orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));
		
		//capturandos os dados da conta
		var conta = modelMapper.map(request, Conta.class);
		//relacionando a conta com a categoria
		conta.setCategoria(categoria);
		
		//salvando no banco de dados
		contaRepository.save(conta);
		
		//retornando os dados da resposta
		return modelMapper.map(conta, ContaResponseDto.class);
	}

	@Override
	public ContaResponseDto editar(Integer id, ContaRequestDto request) throws Exception {

		//consultando a conta no banco de dados através do ID
		var conta = contaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));
				
		//consultando a conta no banco de dados através do ID
		var categoria = categoriaRepository.findById(request.getCategoriaId())
				.orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));
		
		//modificando os dados da conta
		conta.setNome(request.getNome());
		conta.setData(request.getData());
		conta.setValor(BigDecimal.valueOf(request.getValor()));
		conta.setTipo(TipoConta.valueOf(request.getTipo()));
		conta.setObservacoes(request.getObservacoes());
		conta.setCategoria(categoria);
		
		//salvando as alterações no banco de dados
		contaRepository.save(conta);
		
		//retornando os dados da resposta
		return modelMapper.map(conta, ContaResponseDto.class);
	}

	@Override
	public ContaResponseDto excluir(Integer id) throws Exception {

		//consultando a conta no banco de dados através do ID
		var conta = contaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));
		
		//excluindo a conta do banco de dados
		contaRepository.delete(conta);
		
		//retornando os dados da resposta
		return modelMapper.map(conta, ContaResponseDto.class);
	}

	@Override
	public List<ContaResponseDto> consultar() throws Exception {

		//consultando todas as contas e copiar os dados para uma lista da classe DTO
		var response = contaRepository.findAll()
				.stream()
				.map((conta) -> modelMapper.map(conta, ContaResponseDto.class))
				.collect(Collectors.toList());
		
		return response;
	}

	@Override
	public ContaResponseDto obterPorId(Integer id) throws Exception {

		//consultando a conta no banco de dados através do ID
		var conta = contaRepository.findById(id)
					.orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));
		
		//retornando os dados
		return modelMapper.map(conta, ContaResponseDto.class);
	}

}
