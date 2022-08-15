package me.dio.coding.votacao.bbb.api;

import lombok.RequiredArgsConstructor;
import me.dio.coding.votacao.bbb.api.dto.VotingRequestDTO;
import me.dio.coding.votacao.bbb.service.VotingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/voting")
@RequiredArgsConstructor
@Transactional
public class VotingController implements VotingApi {

    private final VotingService votingService;

    @Override
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Void> vote(@RequestBody VotingRequestDTO request) {
        votingService.vote(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
