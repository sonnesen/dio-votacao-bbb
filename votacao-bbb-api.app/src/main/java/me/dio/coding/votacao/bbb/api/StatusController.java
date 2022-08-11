package me.dio.coding.votacao.bbb.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController implements StatusApi {

    @GetMapping(value = "/api/v1/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok(("UP"));
    }
}
