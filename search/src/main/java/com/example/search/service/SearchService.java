package com.example.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Service
public class SearchService {

    private RestTemplate restTemplate;

    @Autowired
    public SearchService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    public Object[] search(){
        CompletableFuture<String[]> uniF = CompletableFuture.supplyAsync(()-> restTemplate.getForObject("http://university/university" , String[].class));
        CompletableFuture<String> detailsF = CompletableFuture.supplyAsync(() -> restTemplate.getForObject("http://details/details/port" , String.class));
        CompletableFuture<Object[]> resultFuture = uniF.thenCombine(detailsF,
                (university, details) -> {
                    return Stream.concat(Stream.of(details), Arrays.stream(university)).toArray();
                });
        return resultFuture.join();
    }
}
