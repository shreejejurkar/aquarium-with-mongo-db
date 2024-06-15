package com.requillion_solutions.aquarium.fishes;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FishGetDTO {

    private String id;
    private String type;

    static public FishGetDTO getDTO(Fish fish) {
        FishGetDTO dto = new FishGetDTO(fish.getId().toString(), fish.getType());
        return dto;
    }

    static public List<FishGetDTO> getDTO(List<Fish> fishes) {
        List<FishGetDTO> dtos = fishes.stream()
                .map(FishGetDTO::getDTO)
                .toList();
        return dtos;
    }
}
