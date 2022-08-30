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

import com.ecommerce.savegames.model.Avaliacoes;
import com.ecommerce.savegames.repository.AvaliacoesRepository;

@RestController
@RequestMapping("/avaliacoes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AvaliacoesController {

	@Autowired
	private AvaliacoesRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Avaliacoes>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Avaliacoes> post (@RequestBody Avaliacoes avaliacoes){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(avaliacoes));
	}
}
