package com.ecommerce.savegames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.savegames.model.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long>{

	public List<Genero> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
}
