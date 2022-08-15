package me.dio.voting.ms.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "voting")
@Builder
public class Voting {

    @Id
    private String id;
    private Participant participant;
    private LocalDateTime computedAt;
}
