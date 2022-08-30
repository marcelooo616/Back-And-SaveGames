package com.ecommerce.savegames.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;





@Entity
@Table(name = "produtos")
public class Produtos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size( max = 100)
	private String nome;
	
	
	@NotBlank
	private String sobre;
	
	@NotBlank
	@Size( max = 20)
	private String plataforma;
	
	@NotNull
	private double valor;
	
	@NotBlank
	private String lancamento ;
	
	private String foto;
	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private Genero genero;
	
	@OneToMany(mappedBy = "produtos", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("produtos")
	private List<Avaliacoes> avaliacoes;
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String getSobre() {
		return sobre;
	}
	public void setSobre(String sobre) {
		this.sobre = sobre;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getLancamento() {
		return lancamento;
	}
	public void setLancamento(String lancamento) {
		this.lancamento = lancamento;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public List<Avaliacoes> getAvaliacoes() {
		return avaliacoes;
	}
	public void setAvaliacoes(List<Avaliacoes> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}
	
	
	
	
	

}
