package org.finproject.controller;

import org.finproject.entity.ProgramUser;
import org.finproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(userService.list(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody ProgramUser user) {
        try {
            return new ResponseEntity<>(userService.createUser(user), HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (UnsupportedOperationException exception) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "userId") long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (NoSuchElementException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable(name = "userId") long id) {
        try {
            return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (NoSuchElementException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "userId") long id, @RequestBody ProgramUser user) {
        try {
            return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
        } catch (InvalidParameterException exception) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (NoSuchElementException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}
