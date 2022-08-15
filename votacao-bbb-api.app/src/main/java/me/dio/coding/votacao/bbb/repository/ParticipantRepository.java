package me.dio.coding.votacao.bbb.repository;

import me.dio.coding.votacao.bbb.model.Participant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends MongoRepository<Participant, String> {
}
