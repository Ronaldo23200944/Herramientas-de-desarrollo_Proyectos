package com.marketplace.controller;

import com.marketplace.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private List<User> users = new ArrayList<>();
    private Long idCounter = 1L;

    @GetMapping
    public List<User> getAll() {
        return users;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {

        if (user.getNombre() == null || user.getEmail() == null) {
            return ResponseEntity.badRequest().build();
        }

        user.setId(idCounter++);
        users.add(user);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        boolean removed = users.removeIf(u -> u.getId().equals(id));

        if (!removed) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
}