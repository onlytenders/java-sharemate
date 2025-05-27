package com.practice.sharemate.user.repository;

import com.practice.sharemate.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class UserRepository {

    private final Map<Long, User> users = new HashMap<>();
    private Long idCounter = 1L;

    public User save(User user) {

        if (user.getId() == null) {
            user.setId(idCounter++);
        }

        users.put(user.getId(), user);

        return user;
    }

    public User findById(Long id) {
        return users.get(id);
    }

    public User findByEmail(String email) {
        for (User u : users.values()) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }

        return null;
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User delete(Long userId) {
        return users.remove(userId);
    }
}
