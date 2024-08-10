package br.com.cotiinformatica.domain.models.entities;

import java.math.BigDecimal;
import java.util.Date;

import br.com.cotiinformatica.domain.models.enums.TipoConta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "tbl_conta")
@Data
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)
	private Date data;
	
	@Column(name = "valor", nullable = false, precision = 10, scale = 2)
	private BigDecimal valor;
	
	@Column(name = "observacoes", length = 250)
	private String observacoes;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false)
	private TipoConta tipo;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;
}
