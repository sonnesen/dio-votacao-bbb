package me.dio.coding.votacao.bbb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "participants")
public class Participant {

    @Id
    private String id;
    private String name;
}
