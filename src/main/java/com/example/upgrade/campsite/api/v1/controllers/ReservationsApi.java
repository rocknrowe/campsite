/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.18).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.example.upgrade.campsite.api.v1.controllers;

import com.example.upgrade.campsite.model.Error;
import com.example.upgrade.campsite.model.Reservation;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Api(value = "reservations", description = "the reservations API")
public interface ReservationsApi {

    @ApiOperation(value = "Cancels a reservation.", nickname = "deleteReservation", notes = "Cancels (Deletes) a reservation.", tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "The resource was deleted successfully."),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 404, message = "Reservation not found", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    @RequestMapping(value = "/reservations/{id}",
        produces = { "application/json" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteReservation(@ApiParam(value = "Reservation Id",required=true) @PathVariable("id") String id);

    @ApiOperation(value = "Returns a reservation.", nickname = "getReservation", notes = "Optional extended description in CommonMark or HTML.", response = Reservation.class, tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = Reservation.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 404, message = "Reservation not found"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    @RequestMapping(value = "/reservations/{id}",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Reservation> getReservation(@ApiParam(value = "Reservation Id",required=true) @PathVariable("id") String id);


    @ApiOperation(value = "Returns reservations.", nickname = "getReservations", notes = "Returns reservation.", response = Reservation.class, tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Creation successful", response = Reservation.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 404, message = "Reservations not found"),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    @RequestMapping(value = "/reservations",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<Reservation>> getReservations();


    @ApiOperation(value = "Modifies a reservation.", nickname = "modifyReservation", notes = "Modifies a reservation.", response = Reservation.class, tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Modification succesfull", response = Reservation.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    @RequestMapping(value = "/reservations/{id}",
        produces = { "application/json" },
        method = RequestMethod.PATCH)
    ResponseEntity<Reservation> modifyReservation(@ApiParam(value = "Reservation Id",required=true) @PathVariable("id") String id,
                                                  @RequestBody Reservation partialUpdate);


    @ApiOperation(value = "Creates a reservation.", nickname = "saveReservation", notes = "", response = Reservation.class, tags={  })
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Creation succesfull", response = Reservation.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
    @RequestMapping(value = "/reservations",
        produces = { "application/json" },
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Reservation> saveReservation(@Valid @RequestBody Reservation body);

}
