package com.joaot.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.joaot.workshopmongo.domain.Post;
import com.joaot.workshopmongo.domain.User;
import com.joaot.workshopmongo.repository.PostRepository;
import com.joaot.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userrepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null,"Maria","Maria@gmail.com");
		User alex = new User(null,"Alex","Alex@gmail.com");
		User bob = new User(null,"Bob","Bob@gmail.com");
		
		userrepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null, sdf.parse("20/10/2001"), "Birthday", "Faço 22 Hoje", maria);
		
		postRepository.save(post1);
	}

}
