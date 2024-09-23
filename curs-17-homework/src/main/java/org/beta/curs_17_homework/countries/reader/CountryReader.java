package org.beta.curs_17_homework.countries.reader;

import org.beta.curs_17_homework.countries.model.Country;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class CountryReader {

//    private final String path = "C:\\Users\\Toma Alexandru\\java-curs\\curs17\\curs-17-homework\\curs-17-homework\\src\\main\\resources\\countries2.txt";

    public List<Country> readCountries(String path) throws IOException {
        List<Country> countries = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(path))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\|");
                Country country = parseRow(parts);
                countries.add(country);
            }
        }
        return countries;
    }

    private Country parseRow(String[] parts) {
        String name = parts[0];
        String capital = parts[1];
        long population = Long.parseLong(parts[2]);
        long area = Long.parseLong(parts[3]);
        String continent = parts[4];
        List<String> neighbours = parts.length > 5 && !parts[5].isEmpty() ? List.of(parts[5].split("~")) : List.of();

        return createCountry(name, capital, population, area, continent, neighbours);
    }

    private Country createCountry(String name, String capital, long population, long area, String continent, List<String> neighbours) {
        return new Country(name, capital, population, area, continent, neighbours);

    }
}
