package com.gabrielnz.workshopmongodb.repository;

import com.gabrielnz.workshopmongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository  extends MongoRepository<Post, String> {
}
