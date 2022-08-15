package me.dio.voting.ms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dio.voting.ms.model.Participant;
import me.dio.voting.ms.model.Voting;
import me.dio.voting.ms.repository.VotingRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class VotingService {

    private final ModelMapper modelMapper;
    private final VotingRepository votingRepository;

    @Transactional
    @KafkaListener(topics = {"votingTopic"}, groupId = "votingGroupMS")
    public void execute(ConsumerRecord<String, String> consumer) {
        log.info("Vote received for: {}", consumer.value());

        JsonNode jsonNode = getJsonNode(consumer);
        Participant participant = modelMapper.map(jsonNode, Participant.class);
        Voting voting = Voting.builder()
                .participant(participant)
                .computedAt(LocalDateTime.now())
                .build();

        votingRepository.save(voting);

        log.info("Vote computed");
    }

    private JsonNode getJsonNode(ConsumerRecord<String, String> consumer)  {
        try {
            return new ObjectMapper().readTree(consumer.value());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot process json value", e);
        }
    }
}
