package com.generation.lojagames.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "A Classificação é obrigatório")
	private String classificacao;

	@NotNull(message = "O genero é obrigatório")
	private String genero;
	
	
@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
@JsonIgnoreProperties("categoria")
private List<Produtos> produtos;


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public String getClassificacao() {
	return classificacao;
}


public void setClassificacao(String classificacao) {
	this.classificacao = classificacao;
}


public String getGenero() {
	return genero;
}


public void setGenero(String genero) {
	this.genero = genero;
}


public List<Produtos> getProdutos() {
	return produtos;
}


public void setProdutos(List<Produtos> produtos) {
	this.produtos = produtos;
}



}
