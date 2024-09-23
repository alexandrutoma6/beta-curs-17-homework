package org.beta.curs_17_homework.countries.service;
import lombok.SneakyThrows;
import org.beta.curs_17_homework.countries.model.Country;
import org.beta.curs_17_homework.countries.reader.CountryReader;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final List<Country> countries = new ArrayList<>();

    @SneakyThrows
    public CountryService(CountryReader countryReader) {
        this.countries.addAll(countryReader.readCountries("C:\\Users\\Toma Alexandru\\java-curs\\curs17\\curs-17-homework\\curs-17-homework\\src\\main\\resources\\countries2.txt"));
    }

    public List<Country> getCountries() {
        return countries;
    }

    public List<String> getCountryNames() {
        return countries.stream()
                .map(Country::name)
                .toList();
    }

    public Optional<String> getCountryCapital(String countryName) {
        return countries.stream()
                .filter(country -> country.name().equalsIgnoreCase(countryName))
                .findFirst()
                .map(Country::capital);
    }


    public Optional<Long> getCountryPopulation(String countryName) {
        return countries.stream()
                .filter(country -> country.name().equals(countryName))
                .findFirst()
                .map(Country::population);
    }

    public List<Country> getCountryFromContinent(String continent) {
        return countries.stream()
                .filter(country -> country.continent().equals(continent))
                .toList();
    }

    public Optional<List<String>> getCountryNeighbours(String countryName) {
        return countries.stream()
                .filter(country -> country.name().equals(countryName))
                .findFirst()
                .map(Country::neighbours);
    }

    public List<Country> getCountriesWithPopulationFromContinentLargerThan(String continent, long population) {
        return countries.stream()
                .filter(country -> country.continent().equals(continent))
                .filter(country -> country.population() > population)
                .toList();
    }

    public List<Country> getCountriesWithNeighbours(String neighbour, String notNeighbour) {
        return countries.stream()
                .filter(country -> country.neighbours().contains(neighbour))
                .filter(country -> !country.name().equals(notNeighbour))
                .filter(country -> !country.neighbours().contains(notNeighbour))
                .toList();
    }


}
