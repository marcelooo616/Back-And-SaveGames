package com.ecommerce.savegames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.savegames.model.Avaliacoes;

@Repository
public interface AvaliacoesRepository extends JpaRepository<Avaliacoes, Long>{
	
	public List<Avaliacoes>findAllByNotaContainingIgnoreCase(String nota);

}
