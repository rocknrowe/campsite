package com.example.upgrade.campsite.domain.service;

import com.example.upgrade.campsite.api.v1.controllers.resources.Island;
import com.example.upgrade.campsite.domain.entity.IslandDTO;
import com.example.upgrade.campsite.domain.entity.ReservationDTO;
import com.example.upgrade.campsite.domain.exceptions.DatesUnavailableException;
import com.example.upgrade.campsite.domain.exceptions.ReservationDatesInvalidException;
import com.example.upgrade.campsite.domain.exceptions.ReservationNotFoundException;
import com.example.upgrade.campsite.domain.mapper.IslandMapper;
import com.example.upgrade.campsite.domain.repository.IslandRepository;
import com.example.upgrade.campsite.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The type Reservation service.
 */
@Service
public class IslandService {

    private IslandRepository islandRepository;
    private IslandMapper islandMapper;

    /**
     * Instantiates a new Reservation service.
     *
     * @param islandRepository the reservation repository
     * @param islandMapper
     */
    @Autowired
    public IslandService(final IslandRepository islandRepository, IslandMapper islandMapper) {
        this.islandRepository = islandRepository;
        this.islandMapper = islandMapper;
    }

    public List<Island> getIslands() {
        return islandMapper.islandDTOsToIslands(islandRepository.findAll());
    }

    public Island saveIsland(final Island island) {

        IslandDTO islandDTO = islandRepository.save(islandMapper.islandToIslandDTO(island));
        return islandMapper.islandDTOtoIsland(islandDTO);
    }

    public String saveIslandMedia(final MultipartFile mediaFile) throws Exception {

        UUID mediaFileId = UUID.randomUUID();
        String folder = "/medias/";
        byte[] imageBytes = mediaFile.getBytes();
        Path path = Paths.get(folder + mediaFile.getOriginalFilename() + mediaFileId);
        Files.write(path, imageBytes);
        return mediaFileId.toString();
    }

    public File getIslandMedia(String mediaFileId) {

        String folder = "/medias/";
        Path path = Paths.get(folder + "small.mp452c53537-8036-4ffd-92d5-39c87614d253");
        return path.toFile();
    }
}
