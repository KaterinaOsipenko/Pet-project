package com.work.authorization.repository;

import com.work.authorization.AuthorizationApplication;
import com.work.authorization.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AuthorizationApplication.class)
@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void getByUsername() {
        assertEquals("kate", userRepository.findByUsername("kate").getUsername());
    }
}