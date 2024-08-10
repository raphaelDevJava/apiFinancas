package br.com.cotiinformatica.domain.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaRequestDto {

	@NotEmpty(message = "Por favor, informe o nome da categoria.")
	@Size(min = 6, max = 50, message = "Nome da categoria deve ter de 6 a 50 caracteres.")
	private String nome;
}
