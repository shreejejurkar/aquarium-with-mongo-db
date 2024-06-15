package com.requillion_solutions.aquarium.fishes;

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
@RequestMapping(path = "/api/v1/fishes",  produces = {MediaType.APPLICATION_JSON_VALUE})
public class FishController {

    private final FishService fishService;

    /**
     * Get all fishes
     * @return A list of fishes
     */
    @GetMapping
    public ResponseEntity<List<FishGetDTO>> getAllFishes(
    ) {
        log.info("Get all fishes");

        List<FishGetDTO> response = FishGetDTO.getDTO(fishService.getAllFishes());

        return ResponseEntity.ok(response);
    }


    /**
     * Get a specific fish
     * @param id - the UUID of the fish
     * @return THe fish (if it exists)
     */
    @GetMapping("/{id}")
    public ResponseEntity<FishGetDTO> getFish(
            @PathVariable("id") UUID id
    ) {
        log.info("Get fish {}", id);

        FishGetDTO response = FishGetDTO.getDTO(fishService.getFish(id));

        return ResponseEntity.ok(response);
    }


    /**
     * Create a fish
     * @param dto Definition of the fish
     * @return a fish (not in a tank)
     */
    @PostMapping
    public ResponseEntity<FishGetDTO> createFish(
            @RequestBody FishPostDTO dto
    ) {
        log.info("Create fish");

        FishGetDTO response = FishGetDTO.getDTO(fishService.createFish(dto));

        return ResponseEntity.ok(response);
    }


    /**
     * Update the details of the fish
     * @param id the UUID of the fish to be updated
     * @param dto the details to be changed
     * @return the updated fish
     */
    @PutMapping("/{id}")
    public ResponseEntity<FishGetDTO> updateFish(
            @PathVariable("id") UUID id,
            @RequestBody FishPostDTO dto
    ) {
        log.info("Update fish {}", id);

        FishGetDTO response = FishGetDTO.getDTO(fishService.updateFish(id, dto));

        return ResponseEntity.ok(response);
    }


    /**
     * Delete the given fish if it exists (idempotent)
     * @param id the UUID of the fish to be deleted
     * @return void
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFish(
            @PathVariable("id") UUID id
    ) {
        log.info("Delete fish {}", id);

        fishService.deleteFish(id);

        return ResponseEntity.ok().build();
    }
}
