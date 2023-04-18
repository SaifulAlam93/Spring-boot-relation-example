package com.practice.SpringBootCRUD.service;

import com.practice.SpringBootCRUD.entity.User;
import com.practice.SpringBootCRUD.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    UserRepo userRepo;


    public User saveUser(User user){

        return userRepo.save(user);
    };

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public User getById(Long id) {
        return userRepo.findById(id).get();
    }

    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

    public User update(Long id, User user) {

        Optional<User> old = userRepo.findById(id);
        if (old.isPresent()){
            old.get().setName(user.getName());
            old.get().setAddress(user.getAddress());
            userRepo.save(old.get());
        }

        return old.get();
    }

    public List<User> getByAddress(String add) {

        return userRepo.findByAddress(add);
    }
}
