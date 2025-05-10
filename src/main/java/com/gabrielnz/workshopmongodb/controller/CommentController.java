package com.gabrielnz.workshopmongodb.controller;

import com.gabrielnz.workshopmongodb.domain.Post;
import com.gabrielnz.workshopmongodb.dto.CommentDTO;
import com.gabrielnz.workshopmongodb.service.PostService;
import com.gabrielnz.workshopmongodb.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CommentController {
    @Autowired
    PostService postService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<List<CommentDTO>> findByPost(@PathVariable String id) {
        if (postService.findById(id) == null) throw new ObjectNotFoundException("Post not found");
        return ResponseEntity.ok().body(postService.findById(id).getComments());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/{id}")
    public ResponseEntity<Post> create(@PathVariable String id, CommentDTO commentDTO) {
        if (postService.findById(id) == null) throw new ObjectNotFoundException("Post not found");
        Post post = postService.findById(id);
        post.getComments().add(commentDTO);
        return ResponseEntity.ok().body(postService.save(post));
    }

    @PreAuthorize("@postService.isOwner(#postId, authentication.principal.id) or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id, CommentDTO commentDTO) {
        if (postService.findById(id) == null) throw new ObjectNotFoundException("Post not found");
        postService.findById(id).getComments().remove(commentDTO);
        return ResponseEntity.noContent().build();
    }

    public boolean isOwner(String postId, String userId) {
        Post post = postService.findById(postId);
        return post != null && post.getAuthor().getId().equals(userId);
    }
}
