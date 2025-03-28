package com.example.mongo.repository;

import com.example.mongo.domain.Account;
import com.example.mongo.projection.AccountProjection;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    @Aggregation(pipeline = {
            // filter by id
            "{ $match: { id: ?0 } }",
            // add id field as string
            "{ $set: { id_string: { $toString: '$_id' } } }",
            // left join incoming transfers
            "{ $lookup: { from: 'transfers', localField: 'id_string', foreignField: 'to', as: 'incoming' } }",
            // left join outgoing transfers
            "{ $lookup: { from: 'transfers', localField: 'id_string', foreignField: 'from', as: 'outgoing' } }",
            // keep values of interest
            "{ $project: { owner: 1, balance: 1, incoming: { _id: 1, instant: 1, from: 1, amount: 1 }, " +
                    "outgoing: { _id: 1, instant: 1, to: 1, amount: 1 } } }"})
    Optional<AccountProjection> getAccountProjection(String id);
}
