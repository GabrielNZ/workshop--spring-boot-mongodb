package com.gabrielnz.workshopmongodb.config;

import com.gabrielnz.workshopmongodb.domain.Post;
import com.gabrielnz.workshopmongodb.domain.Roles;
import com.gabrielnz.workshopmongodb.domain.User;
import com.gabrielnz.workshopmongodb.dto.AuthorDTO;
import com.gabrielnz.workshopmongodb.dto.CommentDTO;
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

        User maria = new User(null, "Maria Brown","123456", "maria@gmail.com", Roles.USER);
        User alex = new User(null, "Alex Green","123456", "alex@gmail.com",Roles.USER);
        User bob = new User(null, "Bob Grey","123456", "bob@gmail.com",Roles.USER);

        userRepository.deleteAll();
        userRepository.saveAll(Arrays.asList(maria,alex,bob));

        Post post1 = new Post(null, LocalDate.now(),"Partiu viagem!", "Vou viajar para Sao Paulo. Abracos", new AuthorDTO(maria));
        Post post2 = new Post(null, LocalDate.now(),"Bom dia!", "Acordei feliz hoje", new AuthorDTO(maria));

        postRepository.deleteAll();
        postRepository.saveAll(Arrays.asList(post1,post1));

        CommentDTO comment1 = new CommentDTO("Boa viagem mano!", LocalDate.now(), new AuthorDTO(alex));
        CommentDTO comment2 = new CommentDTO("Aproveita", LocalDate.now(), new AuthorDTO(bob));
        CommentDTO comment3 = new CommentDTO("Tenha um otimo dia!", LocalDate.now(), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(comment1,comment2));
        post2.getComments().add(comment3);

        postRepository.saveAll(Arrays.asList(post1,post2));

        maria.getPosts().addAll(Arrays.asList(post1,post2));
        userRepository.save(maria);
    }
}
