package com.gabrielnz.workshopmongodb.service;

import com.gabrielnz.workshopmongodb.domain.Post;
import com.gabrielnz.workshopmongodb.repository.PostRepository;
import com.gabrielnz.workshopmongodb.service.exception.DataBaseIntegrity;
import com.gabrielnz.workshopmongodb.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(String id) {
        return postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }
    public List<Post> findByTitle(String text) {
        return postRepository.findByTitleContainingIgnoreCase(text);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void delete(String id) {
        try {
            postRepository.deleteById(id);
        }catch(DataIntegrityViolationException e) {
            throw new DataBaseIntegrity("Database integrity violation");
        }
    }
}
