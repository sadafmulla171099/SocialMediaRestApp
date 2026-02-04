package com.restapi.example.rest_web_services.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restapi.example.rest_web_services.DAOService.UserDaoService;
import com.restapi.example.rest_web_services.model.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    public UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService){
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> retreiveAllUsers(){
        return userDaoService.retreiveAllUsers();
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable int id){
       return userDaoService.findbyId(id);
    }
    
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
		userDaoService.deleteById(id);
	}
    

    @PostMapping("/users")
    public void createUser(@Valid @RequestBody User user){
        userDaoService.saveUser(user);
        URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(user.getId())
				.toUri();
        ResponseEntity.created(location).build();
    }
}
