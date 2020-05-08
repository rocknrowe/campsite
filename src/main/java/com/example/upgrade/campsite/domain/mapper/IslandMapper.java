package com.example.upgrade.campsite.domain.mapper;

import com.example.upgrade.campsite.api.v1.controllers.resources.Island;
import com.example.upgrade.campsite.domain.entity.IslandDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * The interface Island mapper.
 */
@Mapper
public interface IslandMapper {


    Island islandDTOtoIsland(IslandDTO dto);
    IslandDTO islandToIslandDTO(Island island);
    
    List<Island> islandDTOsToIslands(List<IslandDTO> islandDTOS);
}
