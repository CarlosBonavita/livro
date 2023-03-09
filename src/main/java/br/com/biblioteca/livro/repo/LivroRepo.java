package br.com.biblioteca.livro.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.biblioteca.livro.model.Livro;

public interface LivroRepo extends CrudRepository<Livro, Integer> {

	public ArrayList<Livro> findByOrderByTitulo();

	public ArrayList<Livro> findByTituloContaining(String palavraChave);
}
