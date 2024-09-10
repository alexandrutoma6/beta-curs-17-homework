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
        return countries.stream()
                .filter(country -> country.name().equals(countryName))
                .findFirst()
                .map(Country::capital)
                .orElseThrow(() -> new IllegalArgumentException("Country not found: " + countryName));
    }


    public long getCountryPopulation(String countryName) {
        return countries.stream()
                .filter(country -> country.name().equals(countryName))
                .findFirst()
                .map(Country::population)
                .orElseThrow(() -> new IllegalArgumentException("Country not found: " + countryName));
    }

    public List<Country> getCountryFromContinent(String continent) {
        return countries.stream()
                .filter(country -> country.continent().equals(continent))
                .toList();
    }

    public List<String> getCountryNeighbours(String countryName) {
        return countries.stream()
                .filter(country -> country.name().equals(countryName))
                .findFirst()
                .map(Country::neighbours)
                .orElseThrow(() -> new IllegalArgumentException("Country not found: " + countryName));
    }

    public List<Country> getCountriesWithPopulationFromContinentLargerThan(String continent, long population) {
        return countries.stream()
                .filter(country -> country.continent().equals(continent))
                .filter(country -> country.population() > population)
                .toList();
    }

    public List<Country> getCountriesWithNeighbours(String neighbour, String notNeighbour) {
        return countries.stream()
                .filter(country -> country.neighbours().contains(neighbour) && !(country.neighbours().contains(notNeighbour)))
                .toList();
    }

}
