package com.work.authorization.service;

import com.work.authorization.controller.UserDTO;
import com.work.authorization.exception.PasswordMatcherException;
import com.work.authorization.exception.UserAlreadyExistException;
import com.work.authorization.model.User;
import com.work.authorization.model.VerificationToken;
import com.work.authorization.repository.UserRepository;
import com.work.authorization.repository.VerificationTokenRepository;
import com.work.authorization.security.UserServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        if (emailExist(userDTO.getEmail())) {
            throw new UserAlreadyExistException("There is already an account registered with that email " + userDTO.getEmail());
        }
        if (usernameExist(userDTO.getUsername())) {
            throw new UserAlreadyExistException("There is already an account registered with that username " + userDTO.getUsername() + ". Please, chose another one.");
        }
        if (!passwordMatch(userDTO.getPassword(), userDTO.getMatchingPassword())) {
            throw new PasswordMatcherException("Error confirmation isn`t true!");
        }
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

    public void saveRegisteredUser(User user) {
         this.userRepository.save(user);
    }

    private boolean emailExist(String email) {
        return this.userRepository.findByEmail(email) != null;
    }

    private boolean usernameExist(String username) {
        return this.userRepository.findByUsername(username) != null;
    }

    private boolean passwordMatch(String password, String matherPassword) {
        return password.equals(matherPassword);
    }

    public void createVerificationTokenForUser(final User user, final String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }
}
