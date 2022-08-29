package com.ecommerce.savegames.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.savegames.model.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long>{

	public List<Produtos> findAllByNomeContainingIgnoreCase(String nome);
	public List<Produtos> findAllByGeneroContainingIgnoreCase(String genero);
	public List<Produtos> findAllByPlataformaContainingIgnoreCase(String  plataforma);
	public List<Produtos> findAllByLancamentoContainingIgnoreCase(String lancamento);
	
	
	
}



