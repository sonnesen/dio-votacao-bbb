package me.dio.coding.votacao.bbb.service;

import lombok.RequiredArgsConstructor;
import me.dio.coding.votacao.bbb.model.Participant;
import me.dio.coding.votacao.bbb.repository.ParticipantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    @Transactional(readOnly = true)
    public Participant retrieveById(String id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Participant not found."));
    }

    @Transactional(readOnly = true)
    public List<Participant> retrieveAll() {
        return participantRepository.findAll();
    }

    @Transactional
    public Participant save(Participant toSave) {
        return participantRepository.save(toSave);
    }
}
