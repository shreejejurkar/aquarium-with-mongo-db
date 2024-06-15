package com.requillion_solutions.aquarium.fishes;

import com.requillion_solutions.aquarium.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FishService {

    private final FishRepository fishRepo;

    public Fish getFish(UUID id) {

        Fish fishTank = fishRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fish not found"));
        return fishTank;
    }

    public List<Fish> getAllFishes() {
        List<Fish> fishTanks = fishRepo.findAll();

        return fishTanks;
    }

    public Fish createFish(FishPostDTO dto) {

        Fish fish = new Fish(dto.getType());

        fish = fishRepo.save(fish);

        return fish;
    }

    public Fish updateFish(UUID id, FishPostDTO dto) {

        Fish fish = getFish(id);

        fish.setType(dto.getType());

        fish = fishRepo.save(fish);

        return fish;
    }

    public void deleteFish(UUID id) {

        fishRepo.deleteById(id);
    }
}
