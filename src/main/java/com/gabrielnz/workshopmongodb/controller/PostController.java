package com.gabrielnz.workshopmongodb.controller;

import com.gabrielnz.workshopmongodb.domain.Post;
import com.gabrielnz.workshopmongodb.dto.AuthorDTO;
import com.gabrielnz.workshopmongodb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(postService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text",defaultValue = "") String text) {
        List<Post> list = postService.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post post, AuthorDTO author) {
        post.setAuthor(author);
        return ResponseEntity.ok().body(postService.save(post));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
