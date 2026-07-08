package org.example.fundspark.service;

import org.example.fundspark.model.Comment;
import org.example.fundspark.model.Fundraiser;
import org.example.fundspark.repository.FundraiserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FundraiserServiceImp implements FundraiserService {

    private final FundraiserRepository fundraiserRepository;

    public FundraiserServiceImp(FundraiserRepository fundraiserRepository) {
        this.fundraiserRepository = fundraiserRepository;
    }

    @Override
    public Fundraiser createFundraiser(Fundraiser fundraiser) {
        if (fundraiser.getId() == null || fundraiser.getId().isBlank()) {
            fundraiser.setId(UUID.randomUUID().toString());
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
        return getFundraiserById(fundraiserId).getComments();
    }

    @Override
    public Comment addComment(String fundraiserId, Comment comment) {
        Fundraiser fundraiser = getFundraiserById(fundraiserId);
        if (comment.getId() == null) {
            comment.setId(UUID.randomUUID());
        }
        if (comment.getPostedAt() == null) {
            comment.setPostedAt(LocalDateTime.now());
        }
        fundraiser.getComments().add(comment);
        fundraiserRepository.save(fundraiser);
        return comment;
    }

    @Override
    public void deleteComment(String fundraiserId, UUID commentId) {
        Fundraiser fundraiser = getFundraiserById(fundraiserId);
        boolean removed = fundraiser.getComments().removeIf(comment -> commentId.equals(comment.getId()));
        if (!removed) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
        }
        fundraiserRepository.save(fundraiser);
    }
}