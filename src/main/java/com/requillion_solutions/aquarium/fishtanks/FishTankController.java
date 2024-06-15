package com.requillion_solutions.aquarium.fishtanks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(path = "/api/v1/fish-tanks",  produces = {MediaType.APPLICATION_JSON_VALUE})
public class FishTankController {

    private final FishTankService fishTankService;

    /**
     * Get all fish tanks
     * @return A list of fish tanks
     */
    @GetMapping
    public ResponseEntity<List<FishTankGetDTO>> getFishTanks(
    ) {
        log.info("Get all fish tanks");

        List<FishTankGetDTO> response = FishTankGetDTO.getDTO(fishTankService.getAllFishTanks());

        return ResponseEntity.ok(response);
    }

    /**
     * Get a specific fish tank
     * @param id - the UUID of the fish tank
     * @return THe fish tank (if it exists)
     */
    @GetMapping("/{id}")
    public ResponseEntity<FishTankGetDTO> getFishTank(
            @PathVariable("id") UUID id
    ) {
        log.info("Get fish tank {}", id);

        FishTankGetDTO response = FishTankGetDTO.getDTO(fishTankService.getFishTank(id));

        return ResponseEntity.ok(response);
    }

    /**
     * Create a fish
     * @param dto Definition of the fish
     * @return a fish (not in a tank)
     */
    @PostMapping
    public ResponseEntity<FishTankGetDTO> createFishTank(
            @RequestBody FishTankPostDTO dto
    ) {
        log.info("Create fish tank");

        FishTankGetDTO response = FishTankGetDTO.getDTO(fishTankService.createFishTank(dto));

        return ResponseEntity.ok(response);
    }

    /**
     * Update the details of the fish tank
     * @param id the UUID of the fish tank to be updated
     * @param dto the details to be changed
     * @return the updated fish tank
     */
    @PutMapping("/{id}")
    public ResponseEntity<FishTankGetDTO> updateFishTank(
            @PathVariable("id") UUID id,
            @RequestBody FishTankPostDTO dto
    ) {
        log.info("Update fish tank {}", id);

        FishTankGetDTO response = FishTankGetDTO.getDTO(fishTankService.updateFishTank(id, dto));

        return ResponseEntity.ok(response);
    }

    /**
     * Delete the given fish tank if it exists (idempotent)
     * @param id the UUID of the fish tank to be deleted
     * @return void
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFishTank(
            @PathVariable("id") UUID id
    ) {
        log.info("Delete fish tank {}", id);

        fishTankService.deleteFishTank(id);

        return ResponseEntity.ok().build();
    }
}
