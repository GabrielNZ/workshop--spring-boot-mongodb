package com.gabrielnz.workshopmongodb.config;

import com.gabrielnz.workshopmongodb.domain.Post;
import com.gabrielnz.workshopmongodb.domain.User;
import com.gabrielnz.workshopmongodb.repository.PostRepository;
import com.gabrielnz.workshopmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        User maria = new User(null, "Maria Brown","123456", "maria@gmail.com");
        User alex = new User(null, "Alex Green","123456", "alex@gmail.com");
        User bob = new User(null, "Bob Grey","123456", "bob@gmail.com");

        Post post1 = new Post(null, LocalDate.now(),"Partiu viagem!", "Vou viajar para Sao Paulo. Abracos", maria);
        Post post2 = new Post(null, LocalDate.now(),"Bom dia!", "Acordei feliz hoje", maria);

        userRepository.deleteAll();
        userRepository.saveAll(Arrays.asList(maria,alex,bob));
        postRepository.deleteAll();
        postRepository.saveAll(Arrays.asList(post1,post1));
    }
}
