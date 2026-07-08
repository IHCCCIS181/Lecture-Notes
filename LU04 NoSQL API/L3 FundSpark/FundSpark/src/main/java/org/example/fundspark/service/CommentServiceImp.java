package org.example.fundspark.service;

import org.example.fundspark.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImp implements CommentService {

    private final FundraiserService fundraiserService;

    public CommentServiceImp(FundraiserService fundraiserService) {
        this.fundraiserService = fundraiserService;
    }

    @Override
    public List<Comment> getComments(String fundraiserId) {
        return fundraiserService.getComments(fundraiserId);
    }

    @Override
    public Comment addComment(String fundraiserId, Comment comment) {
        return fundraiserService.addComment(fundraiserId, comment);
    }

    @Override
    public void deleteComment(String fundraiserId, String commentId) {
        fundraiserService.deleteComment(fundraiserId, commentId);
    }
}