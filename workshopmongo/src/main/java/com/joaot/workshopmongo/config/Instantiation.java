package com.joaot.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.joaot.workshopmongo.domain.Post;
import com.joaot.workshopmongo.domain.User;
import com.joaot.workshopmongo.dto.AuthorDto;
import com.joaot.workshopmongo.dto.CommentDto;
import com.joaot.workshopmongo.repository.PostRepository;
import com.joaot.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userReposiroty;
	
	@Autowired
	private PostRepository postReposiroty;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userReposiroty.deleteAll();
		postReposiroty.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userReposiroty.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDto(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDto(maria));
       
		CommentDto c1 = new CommentDto("Parabéns pela viagem", sdf.parse("21/10/2018"),new AuthorDto(alex));
		CommentDto c2 = new CommentDto("Chama mano", sdf.parse("21/10/2018"),new AuthorDto(bob));
		CommentDto c3 = new CommentDto("Bom dia, Linda",sdf.parse("23/03/2018"),new AuthorDto(bob));
	    postReposiroty.saveAll(Arrays.asList(post1, post2));

		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		
		postReposiroty.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userReposiroty.save(maria);
	}
		
	
	}


