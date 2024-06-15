package com.requillion_solutions.aquarium.fishtanks;

import com.requillion_solutions.aquarium.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FishTankService {

    private final FishTankRepository fishTankRepository;

    public FishTank getFishTank(UUID id) {

        FishTank fishTank = fishTankRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fish tank not found"));
        return fishTank;
    }

    public List<FishTank> getAllFishTanks() {

        List<FishTank> tanks = fishTankRepository.findAll();

        return tanks;
    }

    public FishTank createFishTank(FishTankPostDTO dto) {

        FishTank fishTank = new FishTank(dto.getName());

        fishTank = fishTankRepository.save(fishTank);

        return fishTank;
    }

    public FishTank updateFishTank(UUID id, FishTankPostDTO dto) {

        FishTank fishTank = fishTankRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fish tank not found"));

        fishTank.setName(dto.getName());

        fishTank = fishTankRepository.save(fishTank);

        return fishTank;
    }

    public void deleteFishTank(UUID id) {

        Optional<FishTank> fishTankFound = fishTankRepository.findById(id);

        if (fishTankFound.isPresent()) {
            fishTankRepository.deleteById(id);
        }
    }
}
