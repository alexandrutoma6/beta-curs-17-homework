package org.beta.curs_17_homework.countries.controller;

import org.beta.curs_17_homework.countries.exceptions.ResourceNotFoundException;
import org.beta.curs_17_homework.countries.model.Country;
import org.beta.curs_17_homework.countries.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/")
    public String index(){
        return "Controller";
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries(){
        return countryService.getCountries();
    }

    @GetMapping("/countries/{countryName}")
    public String getCountryCapital(@PathVariable String countryName){
        return countryService.getCountryCapital(countryName).orElseThrow(()-> new ResourceNotFoundException("Could not fount this country: " + countryName));
    }
}
