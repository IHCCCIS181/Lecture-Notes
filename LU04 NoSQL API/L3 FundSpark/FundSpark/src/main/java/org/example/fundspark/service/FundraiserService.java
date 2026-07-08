package org.example.fundspark.service;

import org.example.fundspark.model.Comment;
import org.example.fundspark.model.Fundraiser;

import java.util.List;
import java.util.UUID;

public interface FundraiserService {
    Fundraiser createFundraiser(Fundraiser fundraiser);

    List<Fundraiser> getAllFundraisers();

    Fundraiser getFundraiserById(String id);

    Fundraiser updateFundraiser(String id, Fundraiser fundraiser);

    void deleteFundraiser(String id);

    List<Comment> getComments(String fundraiserId);

    Comment addComment(String fundraiserId, Comment comment);

    void deleteComment(String fundraiserId, UUID commentId);
}