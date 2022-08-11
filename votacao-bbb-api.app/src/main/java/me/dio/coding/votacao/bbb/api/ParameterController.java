package me.dio.coding.votacao.bbb.api;

import lombok.RequiredArgsConstructor;
import me.dio.coding.votacao.bbb.api.dto.ParameterRequestDTO;
import me.dio.coding.votacao.bbb.api.dto.ParameterResponseDTO;
import me.dio.coding.votacao.bbb.model.Parameter;
import me.dio.coding.votacao.bbb.repository.ParameterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/parameters")
@RequiredArgsConstructor
@Transactional
public class ParameterController implements ParametersApi {

    private final ParameterRepository parameterRepository;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/{key}")
    @ResponseStatus(code = HttpStatus.OK)
    @Transactional(readOnly = true)
    public ResponseEntity<ParameterResponseDTO> findByKey(@PathVariable String key) {
        Parameter parameter = parameterRepository.findById(key)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parameter not found."));
        ParameterResponseDTO response = modelMapper.map(parameter, ParameterResponseDTO.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Transactional(readOnly = true)
    public ResponseEntity<List<ParameterResponseDTO>> list() {
        List<Parameter> parameterList = parameterRepository.findAll();
        List<ParameterResponseDTO> responseList = parameterList.stream()
                .map(p -> modelMapper.map(p, ParameterResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<ParameterResponseDTO> save(@RequestBody ParameterRequestDTO parameterRequest) {
        Parameter toSave = modelMapper.map(parameterRequest, Parameter.class);
        Parameter saved = parameterRepository.save(toSave);
        ParameterResponseDTO response = modelMapper.map(saved, ParameterResponseDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
