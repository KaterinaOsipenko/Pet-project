package com.work.authorization.service;

import com.work.authorization.controller.UserDTO;
import com.work.authorization.model.User;
import com.work.authorization.model.VerificationToken;
import com.work.authorization.repository.UserRepository;
import com.work.authorization.repository.VerificationTokenRepository;
import com.work.authorization.security.UserServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends UserServiceDetails {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User registerUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUsername(userDTO.getUsername());
        user.setAdmin(userDTO.isAdmin());
        return userRepository.save(user);
    }

    public VerificationToken getVerificationToken(String verificationToken) {
        return tokenRepository.findByToken(verificationToken);
    }

    public User saveRegisteredUser(User user) {
        return this.userRepository.save(user);
    }

    public void createVerificationTokenForUser(final User user, final String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }
}
