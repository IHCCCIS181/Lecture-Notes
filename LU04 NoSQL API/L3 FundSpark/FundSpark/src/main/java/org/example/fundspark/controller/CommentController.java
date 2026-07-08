package org.example.fundspark.controller;

import jakarta.validation.Valid;
import org.example.fundspark.model.Comment;
import org.example.fundspark.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/fundraisers/{fundraiserId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments(@PathVariable String fundraiserId) {
        return ResponseEntity.ok(commentService.getComments(fundraiserId));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Comment> addComment(@PathVariable String fundraiserId,
            @Valid @RequestBody Comment comment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(fundraiserId, comment));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable String fundraiserId,
            @PathVariable UUID commentId) {
        commentService.deleteComment(fundraiserId, commentId);
        return ResponseEntity.noContent().build();
    }

}
