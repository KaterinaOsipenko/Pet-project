package com.work.authorization;

import com.work.authorization.model.User;
import com.work.authorization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User("Katerina", "Osypenko", "kate", "katerina.osypenko@gmail.com", passwordEncoder.encode( "3005"), true);
        User user2 = new User("Artem", "Zuboque", "artem", "artem.zuboque@gmail.com", passwordEncoder.encode( "456987"), false);
        User user3 = new User("Alexandra", "Martynenko", "sasha", "alexangra.martynenko@gmail.com", passwordEncoder.encode( "123554"), false);

        List<User> userList = Arrays.asList(user1, user2, user3);

        this.userRepository.saveAll(userList);
    }
}
