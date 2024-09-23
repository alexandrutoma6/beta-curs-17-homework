package org.beta.curs_17_homework.countries.model;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public record Country(
        int id,
        String name,
        String capital,
        long population,
        long area,
        String continent,
        List<String> neighbours
) {

    private static final AtomicInteger nextId = new AtomicInteger(1);

    public Country(String name,
                   String capital,
                   long population,
                   long area,
                   String continent,
                   List<String> neighbours) {
        this(nextId.getAndIncrement(), name, capital, population, area, continent, neighbours);
    }

    public Country(int id, String name, String capital, long population, long area, String continent, List<String> neighbours) {
        this.id = id;
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.continent = continent;
        this.neighbours = List.copyOf(neighbours);  // To ensure immutability
    }
}
