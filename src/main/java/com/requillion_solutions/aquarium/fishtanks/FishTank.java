package com.requillion_solutions.aquarium.fishtanks;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Setter
@Getter
@Document("fish tanks")
@NoArgsConstructor
public class FishTank {

    @Id
    public UUID id;

    public String name;

    public FishTank(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "FishTank[id=%s, type='%s']",
                id.toString(), name);
    }
}
