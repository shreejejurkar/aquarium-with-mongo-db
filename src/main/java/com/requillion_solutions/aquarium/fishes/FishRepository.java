package com.requillion_solutions.aquarium.fishes;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FishRepository extends MongoRepository<Fish, UUID> {

    public List<Fish> findAll();

    public Optional<Fish> findFirstById(UUID id);

    public Optional<Fish> findFirstByType(String type);
}
