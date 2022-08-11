package me.dio.coding.votacao.bbb.repository;

import me.dio.coding.votacao.bbb.model.Parameter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends MongoRepository<Parameter, String> {
}
