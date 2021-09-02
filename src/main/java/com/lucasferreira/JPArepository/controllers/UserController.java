package com.lucasferreira.JPArepository.controllers;

import com.lucasferreira.JPArepository.entities.User;
import com.lucasferreira.JPArepository.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador Rest para definir os endpoints (caminhos) para acessar os webservices
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    // HTTP - GET
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> result = repository.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Page<User>> findById(@PathVariable long id, Pageable pageable) {
        Page<User> result = repository.findById(id, pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<User>> findAll(Pageable pageable) {
        Page<User> result = repository.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/search-salary")
    public ResponseEntity<Page<User>> searchSalary(
            @RequestParam(defaultValue = "0") Double minSalary,
            @RequestParam(defaultValue = "1000000000") Double maxSalary,
            Pageable pageable) {
        Page<User> result = repository.findBySalaryBetween(minSalary, maxSalary, pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/search-name")
    public ResponseEntity<Page<User>> searchName(
            @RequestParam(defaultValue = "") String name,
            Pageable pageable) {
        Page<User> result = repository.findByNameContainingIgnoreCase(name, pageable);
        return ResponseEntity.ok(result);
    }
}
