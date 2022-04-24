package antifraud.controller;

import antifraud.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class RegistrationController {

    @PostMapping(value = "/api/auth/user")
    @ResponseBody
    public synchronized ResponseEntity<Object> ResponseEntity (@RequestBody User user) {
        return ResponseEntity.ok().body(
                new ConcurrentHashMap<>(Map.of(1,2))
        );
    }
}
