package me.dio.coding.votacao.bbb.service;

import lombok.RequiredArgsConstructor;
import me.dio.coding.votacao.bbb.api.dto.VotingRequestDTO;
import me.dio.coding.votacao.bbb.model.Participant;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VotingService {

    private static final String VOTING_TOPIC = "votingTopic";
    private final KafkaTemplate<Object, Object> kafkaTemplate;
    private final ParticipantService participantService;

    public void vote(VotingRequestDTO request) {
        String id = request.getId();
        Participant participant = participantService.retrieveById(id);
        kafkaTemplate.send(VOTING_TOPIC, participant);
    }
}
