package br.com.biblioteca.livro.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.livro.model.Livro;
import br.com.biblioteca.livro.repo.LivroRepo;

@RestController
public class LivroController {
	@Autowired
	private LivroRepo repo;

	@GetMapping("/livros")
	public ArrayList<Livro> recuperarTodos() {
		return (ArrayList<Livro>) repo.findAll();
	}

	@GetMapping("/livros/{codigo}")
	public ResponseEntity<Livro> recuperarPeloCodigo(@PathVariable int codigo) {
		Livro l = repo.findById(codigo).orElse(null);
		if (l != null) {
			return ResponseEntity.ok(l);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/livros/busca")
	public ResponseEntity<ArrayList<Livro>> buscarPorPalavra(@RequestParam(name = "palavra") String palavra) {
		ArrayList<Livro> lista = repo.findByTituloContaining(palavra);
		if (lista.size() > 0) {
			return ResponseEntity.ok(lista);
		}
		return ResponseEntity.status(404).body(null);
	}

	@PostMapping("/livros")
	public Livro cadastrarLivro(@RequestBody Livro dados) {
		System.out.println(dados);
		return repo.save(dados);
	}
	
@GetMapping("/")
public String frase() {
	return "Olá, essa é minha primeira APi!"
}
	
	
}
