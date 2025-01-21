package com.example.university.service;

import java.util.List;

public interface IUniversityService {
    List<String> getAllUniversities();
    List<String> getUniversityByCountry(String country);
    List<String> getUniversitiesByCountries(List<String> countries);
}
