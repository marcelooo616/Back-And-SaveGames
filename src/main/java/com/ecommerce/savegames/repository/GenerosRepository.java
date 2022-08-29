package com.ecommerce.savegames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.savegames.model.Generos;

@Repository
public interface GenerosRepository extends JpaRepository<Generos, Long> {
	
	public List<Generos> findAllByGeneroContainingIgnoreCase(String genero);
	public List<Generos> findAllByDescricaoContainingIgnoreCase(String descricao);

}
