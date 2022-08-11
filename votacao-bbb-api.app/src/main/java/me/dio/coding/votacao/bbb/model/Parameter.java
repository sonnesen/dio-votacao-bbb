package me.dio.coding.votacao.bbb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "parameters")
public class Parameter {

    @Id
    private String key;
    private String value;
}
