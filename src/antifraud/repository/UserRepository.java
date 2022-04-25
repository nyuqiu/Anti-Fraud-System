package antifraud.repository;

import antifraud.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class UserRepository {
    final private Map<String, User> users = new ConcurrentHashMap<>();

    public User findUserByUsername(String username) {
        return users.get(username);
    }

    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    public boolean isEmpty() {
        return users.isEmpty();
    }

    public List<User> listOfUsers() {
        return new ArrayList<>(users.values()).stream()
                .sorted().collect(Collectors.toList());
    }
}