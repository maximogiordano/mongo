package com.example.mongo.repository;

import com.example.mongo.domain.Transfer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends MongoRepository<Transfer, String> {
    boolean existsByFrom(String from);

    boolean existsByTo(String to);
}
