package antifraud.controller;

import antifraud.domain.User;
import antifraud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class RegistrationController {
    @Autowired
    UserRepository userRepo;
    @Autowired
    PasswordEncoder encoder;

    @PostMapping(value = "/api/auth/user")
    @ResponseBody
    public synchronized ResponseEntity<Object> registerUser(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        if (userRepo.findUserByUsername(user.getUsername()) == null) {
            return ResponseEntity.status(401).body(
                    new ConcurrentHashMap<>(Map.of("id", user.getId(),
                            "name", user.getName(),
                            "username", user.getUsername()))
            );
        } else if (userRepo.findUserByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(409).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/api/auth/list")
    @ResponseBody
    public synchronized ResponseEntity<Object> getUserList() {
        if (userRepo.isEmpty()) {
            return ResponseEntity.ok().body(new ArrayList<User>());
        } else {
            return ResponseEntity.ok().body(userRepo.listOfUsers());
        }
    }

    @DeleteMapping(value = "/api/auth/user/{username}")
    @ResponseBody
    public synchronized ResponseEntity<Object> deleteUser(@PathVariable String username) {
        if (userRepo.findUserByUsername(username) == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(
                    new ConcurrentHashMap<>(Map.of("username", username,
                            "status", "Deleted successfully!")));
        }
    }
}
