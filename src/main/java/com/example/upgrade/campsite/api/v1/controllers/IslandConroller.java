package com.example.upgrade.campsite.api.v1.controllers;


import com.example.upgrade.campsite.api.v1.controllers.resources.Island;
import com.example.upgrade.campsite.domain.service.IslandService;
import com.example.upgrade.campsite.model.Error;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/campsite/v1")
public class IslandConroller {

    IslandService islandService;

    public IslandConroller(final IslandService islandService){
        this.islandService = islandService;
    }

    @ApiOperation(value = "Returns island.", nickname = "getIslands", notes = "Returns islands.", response = Island.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Creation successful", response = Island.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 404, message = "Reservations not found"),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    @RequestMapping(value = "/islands",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Island>> getIslands(){
//        return ResponseEntity.ok(Lists.newArrayList(new Island(1L, "island1"), new Island(2l, "island2")));
        return ResponseEntity.ok(islandService.getIslands());
    }

    @ApiOperation(value = "Creates an island.", nickname = "saveIsland", notes = "", response = Island.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Creation succesfull", response = Island.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 409, message = "Could not save, conflict with existing resource", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/islands",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Island> saveIsland(@Valid @RequestBody Island body){
        return ResponseEntity.ok(islandService.saveIsland(body));
    }

    @ApiOperation(value = "Creates an island.", nickname = "saveIsland", notes = "", response = Island.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Creation succesfull", response = Island.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 409, message = "Could not save, conflict with existing resource", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @PostMapping(value = "/islands/media")
    ResponseEntity<String> saveIslandVideo(@RequestParam("file") MultipartFile file){
        String mediaFileId;
        try {

            mediaFileId = islandService.saveIslandMedia(file);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("error");
        }
        return ResponseEntity.ok( mediaFileId);
    }

    @ApiOperation(value = "Creates an island.", nickname = "saveIsland", notes = "", response = Island.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Creation succesfull", response = Island.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 409, message = "Could not save, conflict with existing resource", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @GetMapping(value = "/islands/media")
    ResponseEntity<File> getIslandMedia(@RequestParam("mediaFileId") String mediaFileId){
        File mediaFile = null;
        try {
            mediaFile = islandService.getIslandMedia(mediaFileId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok( mediaFile);
    }
}
