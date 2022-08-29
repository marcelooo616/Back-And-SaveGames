package com.ecommerce.savegames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.savegames.model.Generos;
import com.ecommerce.savegames.repository.GenerosRepository;

@RestController
@RequestMapping("/generos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GenerosController {
	
	@Autowired
	private GenerosRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Generos>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Generos> GetById(@PathVariable Long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound()
						.build());
	}
	
	@GetMapping("/generos/{genero}")
	public ResponseEntity<List<Generos>> GetByGenero(@PathVariable String genero){
		return ResponseEntity.ok(repository.findAllByGeneroContainingIgnoreCase(genero));
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Generos>> GetByDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping
	public ResponseEntity<Generos> post (@RequestBody Generos genero){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(genero));
	}
	
	@PutMapping
	public ResponseEntity<Generos> put (@RequestBody Generos genero){
		return ResponseEntity.status(HttpStatus.OK)
				.body(repository.save(genero));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
