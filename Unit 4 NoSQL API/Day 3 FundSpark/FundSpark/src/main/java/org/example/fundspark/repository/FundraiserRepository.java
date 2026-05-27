package org.example.fundspark.repository;

import org.example.fundspark.model.Fundraiser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundraiserRepository extends MongoRepository<Fundraiser, String> {
}
