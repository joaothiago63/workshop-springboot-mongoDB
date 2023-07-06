package com.joaot.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaot.workshopmongo.domain.User;
import com.joaot.workshopmongo.dto.UserDto;
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
	
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public void update(User obj) {
	 User user =  findById(obj.getId());
	 updateObject(user,obj);
	 repository.save(user);
		
	}
	
	public User fromDto(UserDto obj) {
		return new User(obj.getId(),obj.getName(),obj.getEmail());
	}
	
	private void updateObject(User user, User obj) {
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
	}
}