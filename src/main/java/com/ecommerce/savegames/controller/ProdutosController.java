package com.ecommerce.savegames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.savegames.model.Produtos;
import com.ecommerce.savegames.repository.ProdutosRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutosController {
	
	@Autowired
	private ProdutosRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Produtos>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Produtos> GetById(@PathVariable Long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound()
						.build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produtos>> GetByNome(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/genero/{genero}")
	public ResponseEntity<List<Produtos>> GetByGenero(@PathVariable String genero){
		return ResponseEntity.ok(repository.findAllByGeneroContainingIgnoreCase(genero));
	}
	
	@GetMapping("/lancamento/{lancamento}")
	public ResponseEntity<List<Produtos>> GetByLancamento(@PathVariable String lancamento){
		return ResponseEntity.ok(repository.findAllByLancamentoContainingIgnoreCase(lancamento));
	}
	
	@GetMapping("/plataforma/{plataforma}")
	public ResponseEntity<List<Produtos>> GetByPlataforma(@PathVariable String plataforma){
		return ResponseEntity.ok(repository.findAllByPlataformaContainingIgnoreCase(plataforma));
	}
	
	@PostMapping
	public ResponseEntity<Produtos> post (@RequestBody Produtos produtos){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(produtos));
	}
	
	@PutMapping
	public ResponseEntity<Produtos> put (@RequestBody Produtos produtos){
		return ResponseEntity.status(HttpStatus.OK)
				.body(repository.save(produtos));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	
	
	
	
	
	

}
