package com.requillion_solutions.aquarium.fishtanks;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FishTankGetDTO {

    private String id;
    private String name;

    static public FishTankGetDTO getDTO(FishTank tank) {
        FishTankGetDTO dto = new FishTankGetDTO(
                tank.getId().toString(),
                tank.getName());
        return dto;
    }

    static public List<FishTankGetDTO> getDTO(List<FishTank> tanks) {
        List<FishTankGetDTO> dtos = tanks.stream()
                .map(FishTankGetDTO::getDTO)
                .toList();
        return dtos;
    }
}
