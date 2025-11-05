package com.restapi.example.rest_web_services.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restapi.example.rest_web_services.DAOService.UserDaoService;
import com.restapi.example.rest_web_services.model.User;

@RestController
@RequestMapping("/api/users")
public class UserController {
    public UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService){
        this.userDaoService = userDaoService;
    }

    @GetMapping("/list")
    public List<User> retreiveAllUsers(){
        return userDaoService.retreiveAllUsers();
    }

    @GetMapping("/search/{id}")
    public User findById(@PathVariable int id){
       return userDaoService.findbyId(id);
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user){
        userDaoService.saveUser(user);
        URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(user.getId())
				.toUri();
        ResponseEntity.created(location).build();
    }
}
