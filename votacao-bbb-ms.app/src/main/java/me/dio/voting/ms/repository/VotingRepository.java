package me.dio.voting.ms.repository;

import me.dio.voting.ms.model.Voting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingRepository extends MongoRepository<Voting, String> {
}
