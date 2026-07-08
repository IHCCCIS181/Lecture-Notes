package org.example.fundspark.repository;

import org.example.fundspark.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByFundraiserId(String fundraiserId);

    Optional<Comment> findByFundraiserIdAndId(String fundraiserId, String id);
}