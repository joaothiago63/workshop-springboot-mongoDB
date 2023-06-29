package com.joaot.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.joaot.workshopmongo.domain.User;
import com.joaot.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	UserRepository repository;

	@Override
	public void run(String... args) throws Exception {
		
		repository.deleteAll();
		
		User maria = new User(null,"Maria","Maria@gmail.com");
		User alex = new User(null,"Alex","Alex@gmail.com");
		User bob = new User(null,"Bob","Bob@gmail.com");
		
		repository.saveAll(Arrays.asList(maria,alex,bob));
		
		
	}

}
