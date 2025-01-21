package com.example.university.service;

import com.example.university.exception.InvalidCountryException;
import com.example.university.pojo.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class UniverstyService implements IUniversityService{

    private RestTemplate restTemplate;
    private final String API_URL = "http://universities.hipolabs.com/search";

    @Autowired
    public UniverstyService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<String> getAllUniversities() {
        University[] universities = restTemplate.getForObject(API_URL , University[].class);
        return Arrays.stream(universities)
                .map(University::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getUniversityByCountry(String country) {
        String url = API_URL + "?country=" + country;
        try{
            University[] universities = restTemplate.getForObject(url , University[].class);
            return Arrays.stream(universities)
                    .map(University::getName)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new InvalidCountryException(country + " is not a valid country");
        }
    }

    @Override
    public List<String> getUniversitiesByCountries(List<String> countries) {
        List<CompletableFuture<List<String>>> futures  = countries.stream()
                .map( country -> CompletableFuture.supplyAsync(() -> getUniversityByCountry(country)))
                .collect(Collectors.toList());

        return futures.stream()
                .flatMap(future -> future.join().stream())
                .collect(Collectors.toList());
    }
}
