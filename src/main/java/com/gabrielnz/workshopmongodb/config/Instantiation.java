package com.gabrielnz.workshopmongodb.config;

import com.gabrielnz.workshopmongodb.domain.User;
import com.gabrielnz.workshopmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User maria = new User(null, "Maria Brown","123456", "maria@gmail.com");
        User alex = new User(null, "Alex Green","123456", "alex@gmail.com");
        User bob = new User(null, "Bob Grey","123456", "bob@gmail.com");

        userRepository.deleteAll();
        userRepository.saveAll(Arrays.asList(maria,alex,bob));
    }
}
