package com.practice.SpringBootCRUD.controller;

import com.practice.SpringBootCRUD.dto.UserDto;
import com.practice.SpringBootCRUD.entity.User;
import com.practice.SpringBootCRUD.repository.UserRepo;
import com.practice.SpringBootCRUD.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/user")
//    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public ResponseEntity<User> saveUser(@RequestBody User user){

//        return  userService.saveUser(user);
        return  new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping(value = "/user")
    public List<UserDto> getAll() throws IOException {

        List<User> users = userService.getAll();
        List<UserDto> userDtos = new ArrayList<>();

        users.forEach((c) ->{
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(c,userDto);
            userDtos.add(userDto);
        });

        if (userDtos==null){
            throw new RuntimeException("Data not found..");
        }

        return  userDtos;
    }

    @GetMapping(value = "/user/{id}")
    public User getByID(@PathVariable(value = "id") Long id){
        return  userService.getById(id);
    }


    @GetMapping(value = "/userByAddress")
    public ResponseEntity<?> getByAddress(@RequestParam(value = "address") String add){
    try {
    List<User> users = userService.getByAddress(add);
        return ResponseEntity.ok(users);
    }catch (Exception e){
        e.printStackTrace();
        return ResponseEntity.internalServerError().body("Error"+ e.getMessage());
     }
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable(value = "id") Long id){
        userService.deleteById(id);
      return ResponseEntity.ok().build();
    }


    @PutMapping(value = "/user/{id}")
    public User deleteByID(@PathVariable(value = "id") Long id, @RequestBody User user){
      return userService.update(id,user);
    }

}
