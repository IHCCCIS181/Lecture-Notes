package org.example.fundspark.service;

import org.example.fundspark.model.Comment;
import org.example.fundspark.model.Fundraiser;
import org.example.fundspark.repository.CommentRepository;
import org.example.fundspark.repository.FundraiserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FundraiserServiceImp implements FundraiserService {

    private final FundraiserRepository fundraiserRepository;
    private final CommentRepository commentRepository;

    public FundraiserServiceImp(FundraiserRepository fundraiserRepository, CommentRepository commentRepository) {
        this.fundraiserRepository = fundraiserRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Fundraiser createFundraiser(Fundraiser fundraiser) {
        if (fundraiser.getId() == null || fundraiser.getId().isBlank()) {
            fundraiser.setId(java.util.UUID.randomUUID().toString());
        }
        if (fundraiser.getComments() == null) {
            fundraiser.setComments(new ArrayList<>());
        }
        return fundraiserRepository.save(fundraiser);
    }

    @Override
    public List<Fundraiser> getAllFundraisers() {
        return fundraiserRepository.findAll();
    }

    @Override
    public Fundraiser getFundraiserById(String id) {
        return fundraiserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fundraiser not found"));
    }

    @Override
    public Fundraiser updateFundraiser(String id, Fundraiser fundraiser) {
        Fundraiser existingFundraiser = getFundraiserById(id);
        existingFundraiser.setTitle(fundraiser.getTitle());
        existingFundraiser.setDescription(fundraiser.getDescription());
        existingFundraiser.setOwner(fundraiser.getOwner());
        existingFundraiser.setStartDate(fundraiser.getStartDate());
        existingFundraiser.setEndDate(fundraiser.getEndDate());
        existingFundraiser.setTargetAmount(fundraiser.getTargetAmount());
        existingFundraiser.setCurrentAmount(fundraiser.getCurrentAmount());
        return fundraiserRepository.save(existingFundraiser);
    }

    @Override
    public void deleteFundraiser(String id) {
        if (!fundraiserRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fundraiser not found");
        }
        fundraiserRepository.deleteById(id);
    }

    @Override
    public List<Comment> getComments(String fundraiserId) {
        return commentRepository.findByFundraiserId(fundraiserId);
    }

    @Override
    public Comment addComment(String fundraiserId, Comment comment) {
        getFundraiserById(fundraiserId);
        if (comment.getId() == null) {
            comment.setId(java.util.UUID.randomUUID().toString());
        }
        if (comment.getPostedAt() == null) {
            comment.setPostedAt(LocalDateTime.now());
        }
        comment.setFundraiserId(fundraiserId);
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(String fundraiserId, String commentId) {
        Comment comment = commentRepository.findByFundraiserIdAndId(fundraiserId, commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));

        if (comment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
        }

        commentRepository.delete(comment);
    }
}