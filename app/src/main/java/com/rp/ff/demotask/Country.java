package com.rp.ff.demotask;

import java.util.List;

public class Country {
    private String name;
    private List<String> cities;

    public Country(String name, List<String> cities) {
        this.name = name;
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public List<String> getCities() {
        return cities;
    }
}
