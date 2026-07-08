package org.example.fundspark.service;

import org.example.fundspark.model.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    List<Comment> getComments(String fundraiserId);

    Comment addComment(String fundraiserId, Comment comment);

    void deleteComment(String fundraiserId, UUID commentId);
}