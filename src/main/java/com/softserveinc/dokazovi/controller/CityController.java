package com.softserveinc.dokazovi.controller;

import com.softserveinc.dokazovi.dto.city.CityDTO;
import com.softserveinc.dokazovi.service.CityService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.softserveinc.dokazovi.controller.EndPoints.CITY;

@RestController
@RequestMapping(CITY)
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    @ApiOperation("Get all cities")
    public ResponseEntity<List<CityDTO>> getAllCities() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cityService.findAllCities());
    }
}