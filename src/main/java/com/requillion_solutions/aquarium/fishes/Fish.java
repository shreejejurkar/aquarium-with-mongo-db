package com.requillion_solutions.aquarium.fishes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Setter
@Getter
@Document("fishes")
@NoArgsConstructor
public class Fish {

    @Id
    public UUID id;

    public String type;

    public Fish(String type) {
        this.id = UUID.randomUUID();
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format(
                "Fish[id=%s, type='%s']",
                id.toString(), type);
    }
}
