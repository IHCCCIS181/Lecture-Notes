package org.example.fundspark.service;

import org.example.fundspark.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getComments(String fundraiserId);

    Comment addComment(String fundraiserId, Comment comment);

    void deleteComment(String fundraiserId, String commentId);
}