package com.example.university.controller;

import com.example.university.service.UniverstyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UniversityController {

    private UniverstyService universtyService;

    @Autowired
    public UniversityController(UniverstyService universtyService){
        this.universtyService = universtyService;
    }

    @GetMapping("/university")
    public ResponseEntity<List<String>> getUniversitiesByCountry(@RequestParam(required = false) List<String> countries){
        if(countries == null || countries.isEmpty()){
            return new ResponseEntity<>(universtyService.getAllUniversities() , HttpStatus.OK);
        }
        return new ResponseEntity<>(universtyService.getUniversitiesByCountries(countries) , HttpStatus.OK);
    }
}
