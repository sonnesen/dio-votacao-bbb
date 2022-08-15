package me.dio.coding.votacao.bbb.api;

import lombok.RequiredArgsConstructor;
import me.dio.coding.votacao.bbb.api.dto.ParticipantRequestDTO;
import me.dio.coding.votacao.bbb.api.dto.ParticipantResponseDTO;
import me.dio.coding.votacao.bbb.model.Participant;
import me.dio.coding.votacao.bbb.service.ParticipantService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/participants")
@RequiredArgsConstructor
public class ParticipantController implements ParticipantsApi {

    private final ParticipantService participantService;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<ParticipantResponseDTO> retrieveById(@PathVariable String id) {
        Participant participant = participantService.retrieveById(id);
        ParticipantResponseDTO response = modelMapper.map(participant, ParticipantResponseDTO.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<ParticipantResponseDTO>> retrieveAll() {
        List<Participant> participantList = participantService.retrieveAll();
        List<ParticipantResponseDTO> responseList = participantList.stream()
                .map(p -> modelMapper.map(p, ParticipantResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<ParticipantResponseDTO> save(@RequestBody ParticipantRequestDTO parameterRequest) {
        Participant toSave = modelMapper.map(parameterRequest, Participant.class);
        Participant saved = participantService.save(toSave);
        ParticipantResponseDTO response = modelMapper.map(saved, ParticipantResponseDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
