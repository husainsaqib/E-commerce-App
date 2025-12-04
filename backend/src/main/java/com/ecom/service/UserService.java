package com.ecom.service;
import com.ecom.model.User;
import com.ecom.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository repo, BCryptPasswordEncoder encoder) { this.repo = repo; this.encoder = encoder; }

    public User register(String username, String email, String password) {
        if (repo.existsByUsername(username)) throw new RuntimeException("username exists");
        if (repo.existsByEmail(email)) throw new RuntimeException("email exists");
        User u = new User();
        u.setUsername(username); u.setEmail(email); u.setPasswordHash(encoder.encode(password));
        return repo.save(u);
    }

    public Optional<User> findByUsername(String username) { return repo.findByUsername(username); }
}

