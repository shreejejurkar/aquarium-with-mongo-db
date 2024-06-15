package com.requillion_solutions.aquarium.fishtanks;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FishTankRepository extends MongoRepository<FishTank, UUID> {

    public List<FishTank> findAll();

    public Optional<FishTank> findFirstById(UUID id);

    public Optional<FishTank> findFirstByName(String type);
}
