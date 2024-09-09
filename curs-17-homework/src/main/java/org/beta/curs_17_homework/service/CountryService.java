package org.beta.curs_17_homework.service;

import org.beta.curs_17_homework.model.Country;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final List<Country> countries;

    public CountryService(List<Country> countries) {
        this.countries = countries;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public List<String> getCountryNames() {
        return countries.stream()
                .map(Country::name)
                .toList();
    }

    public String getCountryCapital(String countryName) {
        return String.valueOf(countries.stream()
                .map(Country::name)
                .filter(name -> name.equals(countryName))
                .findFirst());
    }
}
