package com.ecommerce.savegames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.savegames.model.Genero;
import com.ecommerce.savegames.repository.GeneroRepository;

@RestController
@RequestMapping("/genero")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GeneroController {
	
	@Autowired
	private GeneroRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Genero>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Genero> post (@RequestBody Genero genero){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(genero));
	}

}
