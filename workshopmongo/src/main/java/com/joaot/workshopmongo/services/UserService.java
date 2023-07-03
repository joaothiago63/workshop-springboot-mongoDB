package com.joaot.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaot.workshopmongo.domain.User;
import com.joaot.workshopmongo.repository.UserRepository;
import com.joaot.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User>findAll(){
		return repository.findAll();
	}
	
	public User findById(String id) {
		
	Optional<User> test = repository.findById(id);
	
	return test.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
				
	}
	
}