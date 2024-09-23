package org.beta.curs_17_homework;

import org.beta.curs_17_homework.countries.model.Country;
import org.beta.curs_17_homework.countries.service.CountryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;


    @Test
    @DisplayName("GET ALL COUNTRIES")
    public void getAllCountriesTest() {
        assertThat(countryService.getCountries()).hasSize(236);
    }

//    @Test
//    @DisplayName("GET ALL COUNTRY NAMES")
//    public void getAllCountryNamesTest() {
//        assertThat(countryService.getCountryNames()).;
//    }

    @Test
    @DisplayName("GET COUNTRY CAPITAL")
    public void getCountryCapitalTest() {
        assertThat(countryService.getCountryCapital("Romania")).contains("Bucharest");
        assertThat(countryService.getCountryCapital("Nonexistent Country")).isEmpty();
    }

    @Test
    @DisplayName("GET COUNTRY POPULATION")
    public void getCountryPopulationTest() {
        assertThat(countryService.getCountryPopulation("Romania")).contains(19861408L);
        assertThat(countryService.getCountryPopulation("Nonexistent Country")).isEmpty();
    }

    @Test
    @DisplayName("GET COUNTRIES FROM CONTINENT")
    public void getCountriesFromContinentTest() {
        assertThat(countryService.getCountryFromContinent("Europe")).hasSize(52);
        assertThat(countryService.getCountryFromContinent("Nonexistent Continent")).isEmpty();
    }

    @Test
    @DisplayName("GET COUNTRY NEIGHBOURS")
    public void getCountryNeighboursTest() {
        List<String> expectedNeighbours = Arrays.asList("BGR", "HUN", "MDA", "SRB", "UKR");
        assertThat(countryService.getCountryNeighbours("Romania")).contains(expectedNeighbours);
        assertThat(countryService.getCountryNeighbours("Nonexistent Country")).isEmpty();
    }

    @Test
    @DisplayName("GET COUNTRIES WITH POPULATION LARGER THAN 40,000,000 IN EUROPE")
    public void getCountriesWithPopulationLargerThanTest() {
        List<Country> actualCountries = countryService.getCountriesWithPopulationFromContinentLargerThan("Asia", 1377422165);
        assertThat(actualCountries.size()).isEqualTo(1);
    }


    @Test
    @DisplayName("GET COUNTRIES WITH SPECIFIED NEIGHBOUR AND WITHOUT EXCLUDED NEIGHBOUR")
    public void getCountriesWithNeighboursTest() {
        assertThat(countryService.getCountriesWithNeighbours("ROU", "HUN").size()).isEqualTo(3);

        assertThat(countryService.getCountriesWithNeighbours("Nonexistent Neighbour", "HUN")).isEmpty();
    }
}
