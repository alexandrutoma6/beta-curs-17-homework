package org.beta.curs_17_homework;

import org.assertj.core.api.Assertions;
import org.beta.curs_17_homework.model.Country;
import org.beta.curs_17_homework.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountryServiceTest {

    private List<Country> countriesList;
    private CountryService countryService;

    @BeforeEach
    public void setUp() {
        countriesList = new ArrayList<>();
        countriesList.addAll(generateCountries());
        countryService = new CountryService(countriesList);
    }

    private List<Country> generateCountries() {
        return Arrays.asList(
                new Country(1, "Romania", "Bucharest", 19237691, 238397, "Europe", Arrays.asList("UKR", "MLD", "HUN", "BGR", "SRB")),
                new Country(2, "Ukraine", "Kyiv", 44134693, 603500, "Europe", Arrays.asList("POL", "BLR", "ROU", "RUS", "MLD")),
                new Country(3, "Moldova", "Chisinau", 2640438, 33846, "Europe", Arrays.asList("ROU", "UKR")),
                new Country(4, "Hungary", "Budapest", 9660351, 93028, "Europe", Arrays.asList("AUT", "SVK", "ROU", "SRB", "HRV", "SVN")),
                new Country(5, "Bulgaria", "Sofia", 6951482, 110993, "Europe", Arrays.asList("ROU", "SRB", "MKD", "GRC", "TUR")),
                new Country(6, "Poland", "Warsaw", 38386000, 312696, "Europe", Arrays.asList("DEU", "CZE", "SVK", "UKR", "BLR", "LTU")),
                new Country(7, "Germany", "Berlin", 83166711, 357022, "Europe", Arrays.asList("POL", "CZE", "AUT", "FRA", "LUX", "BEL", "NLD", "DNK")),
                new Country(8, "France", "Paris", 65273511, 551695, "Europe", Arrays.asList("ESP", "AND", "BEL", "LUX", "DEU", "CHE", "ITA")),
                new Country(9, "Italy", "Rome", 60317116, 301340, "Europe", Arrays.asList("FRA", "CHE", "AUT", "SVN", "VAT", "SMR")),
                new Country(10, "Serbia", "Belgrade", 6963764, 77474, "Europe", Arrays.asList("HUN", "ROU", "BGR", "MKD", "HRV", "BIH", "MNE"))
        );
    }

    @Test
    @DisplayName("INSTANTIATE COUNTRY SERVICE CLASS")
    public void intstantiateCountryService() {
        new CountryService(countriesList);
    }

    @Test
    @DisplayName("GET ALL COUNTRIES")
    public void getAllCountriesTest() {
        Assertions.assertThat(countryService.getCountries()).isEqualTo(countriesList);
    }

    @Test
    @DisplayName("GET ALL COUNTRY NAMES")
    public void getAllCountryNamesTest() {
        List<String> expectedCountryNames = Arrays.asList("Romania", "Ukraine", "Moldova", "Hungary", "Bulgaria", "Poland", "Germany", "France", "Italy", "Serbia");
        Assertions.assertThat(countryService.getCountryNames()).isEqualTo(expectedCountryNames);
    }

    @Test
    @DisplayName("GET COUNTRY CAPITAL")
    public void getCountryCapitalTest() {
        Assertions.assertThat(countryService.getCountryCapital("Romania")).contains("Bucharest");
        Assertions.assertThat(countryService.getCountryCapital("Nonexistent Country")).isEmpty();
    }

    @Test
    @DisplayName("GET COUNTRY POPULATION")
    public void getCountryPopulationTest() {
        Assertions.assertThat(countryService.getCountryPopulation("Romania")).contains(19237691L);
        Assertions.assertThat(countryService.getCountryPopulation("Nonexistent Country")).isEmpty();
    }

    @Test
    @DisplayName("GET COUNTRIES FROM CONTINENT")
    public void getCountriesFromContinentTest() {
        List<Country> europeanCountries = countriesList;
        Assertions.assertThat(countryService.getCountryFromContinent("Europe")).isEqualTo(europeanCountries);
        Assertions.assertThat(countryService.getCountryFromContinent("Nonexistent Continent")).isEmpty();
    }

    @Test
    @DisplayName("GET COUNTRY NEIGHBOURS")
    public void getCountryNeighboursTest() {
        List<String> expectedNeighbours = Arrays.asList("UKR", "MLD", "HUN", "BGR", "SRB");
        Assertions.assertThat(countryService.getCountryNeighbours("Romania")).contains(expectedNeighbours);
        Assertions.assertThat(countryService.getCountryNeighbours("Nonexistent Country")).isEmpty();
    }

    @Test
    @DisplayName("GET COUNTRIES WITH POPULATION LARGER THAN 40,000,000 IN EUROPE")
    public void getCountriesWithPopulationLargerThanTest() {
        List<Country> expectedCountries = Arrays.asList(
                new Country(2,"Ukraine", "Kyiv", 44134693, 603500, "Europe", Arrays.asList("POL", "BLR", "ROU", "RUS", "MLD")),
                new Country(7,"Germany", "Berlin", 83166711, 357022, "Europe", Arrays.asList("POL", "CZE", "AUT", "FRA", "LUX", "BEL", "NLD", "DNK")),
                new Country(8,"France", "Paris", 65273511, 551695, "Europe", Arrays.asList("ESP", "AND", "BEL", "LUX", "DEU", "CHE", "ITA")),
                new Country(9,"Italy", "Rome", 60317116, 301340, "Europe", Arrays.asList("FRA", "CHE", "AUT", "SVN", "VAT", "SMR"))
        );

        List<Country> actualCountries = countryService.getCountriesWithPopulationFromContinentLargerThan("Europe", 40000000);
        Assertions.assertThat(actualCountries).isEqualTo(expectedCountries);
    }


    @Test
    @DisplayName("GET COUNTRIES WITH SPECIFIED NEIGHBOUR AND WITHOUT EXCLUDED NEIGHBOUR")
    public void getCountriesWithNeighboursTest() {
        List<Country> expectedCountries = List.of(
                new Country(2,"Ukraine", "Kyiv", 44134693, 603500, "Europe", Arrays.asList("POL", "BLR", "ROU", "RUS", "MLD")),
                new Country(3,"Moldova", "Chisinau", 2640438, 33846, "Europe", Arrays.asList("ROU", "UKR")),
                new Country(4,"Hungary", "Budapest", 9660351, 93028, "Europe", Arrays.asList("AUT", "SVK", "ROU", "SRB", "HRV", "SVN")),
                new Country(5,"Bulgaria", "Sofia", 6951482, 110993, "Europe", Arrays.asList("ROU", "SRB", "MKD", "GRC", "TUR"))
        );

        Assertions.assertThat(countryService.getCountriesWithNeighbours("ROU", "HUN"))
                .containsExactlyInAnyOrderElementsOf(expectedCountries);

        Assertions.assertThat(countryService.getCountriesWithNeighbours("Nonexistent Neighbour", "HUN")).isEmpty();
    }


}
