package com.generation.lojagames.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojagames.model.Produtos;
import com.generation.lojagames.repository.ProdutosRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutosController {
	@Autowired
	private ProdutosRepository produtoRepository;

	@GetMapping
	private ResponseEntity<List<Produtos>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());

	}

	@GetMapping("/{id}")
	private ResponseEntity<Produtos> getById(@PathVariable Long id) {
		return produtoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}

	@GetMapping("/nome")
	private List<String> gitNome() {
		return produtoRepository.findNome();
	}

	@PostMapping
	public ResponseEntity<Produtos> post(@Valid @RequestBody Produtos produtos) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtos));
	}
	
	@PutMapping
	public ResponseEntity<Produtos> put(@Valid @RequestBody Produtos produtos){
		return produtoRepository.findById(produtos.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
						.body(produtoRepository.save(produtos)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Produtos> produtos = produtoRepository.findById(id);
		
		if(produtos.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		produtoRepository.deleteById(id);
		
	}
}
