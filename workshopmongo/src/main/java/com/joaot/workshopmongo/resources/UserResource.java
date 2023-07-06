package com.joaot.workshopmongo.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joaot.workshopmongo.domain.User;
import com.joaot.workshopmongo.dto.UserDto;
import com.joaot.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDto>> findAll() {
		
		List<User> list = new ArrayList<>();
		list = service.findAll();		
		List<UserDto> listDto = list.stream().map(x -> new UserDto(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		

	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<UserDto> findById(@PathVariable("id") String id) {
		
		return ResponseEntity.ok().body(new UserDto(service.findById(id)));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDto objDto){
		
		User user =  service.insert(service.fromDto(objDto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}",method =RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable(value = "id") String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable(value = "id")String id, @RequestBody UserDto obj){
		User user = service.fromDto(obj);
		user.setId(id);
		service.update(user);
		return ResponseEntity.noContent().build();
	}
	
	
}
