package br.com.cotiinformatica.domain.models.dtos;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContaRequestDto {

	@NotEmpty(message = "Por favor, informe o nome da conta.")
	@Size(min = 6, max = 100, message = "O nome da conta deve ter de 6 a 100 caracteres.")
	private String nome;
	
	@NotNull(message = "`Por favor, informe a data da conta.")
	private Date data;
	
	@NotNull(message = "Por favor, informe o valor da conta.")
	private Double valor;
	
	@NotEmpty(message = "Por favor, informe o tipo da conta.")
	@Pattern(regexp = "^(RECEBER|PAGAR)$", 
			message = "Por favor, informe apenas 'RECEBER' ou 'PAGAR'.")
	private String tipo;
		
	@NotNull(message = "Por favor, informe o id da categoria.")
	private Integer categoriaId;
	
	private String observacoes;
}
