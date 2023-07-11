package com.joaot.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaot.workshopmongo.domain.Post;
import com.joaot.workshopmongo.repository.PostRepository;
import com.joaot.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public Post findById(String id) {
		Optional<Post> op = repository.findById(id);
		return op.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitleContaining(String text){
		return repository.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text){
		return repository.fullSearch(text);
	}
	
}
