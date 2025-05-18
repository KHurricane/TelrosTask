package org.example.telrostask.controller;

import org.example.telrostask.model.User;
import org.example.telrostask.model.UserDetails;
import org.example.telrostask.repos.UserDetailsRepository;
import org.example.telrostask.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserDetailsRepository detailsRepo;

    @GetMapping
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepo.save(user);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return userRepo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User updated) {
        User user = userRepo.findById(id).orElseThrow();
        user.setUsername(updated.getUsername());
        return userRepo.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepo.deleteById(id);
    }

    @PutMapping("/{id}/details")
    public UserDetails updateDetails(@PathVariable Long id, @RequestBody UserDetails dto) {
        User user = userRepo.findById(id).orElseThrow();
        dto.setUser(user);
        return detailsRepo.save(dto);
    }

    @GetMapping("/{id}/details")
    public UserDetails getDetails(@PathVariable Long id) {
        return detailsRepo.findByUserId(id).orElseThrow();
    }
}
