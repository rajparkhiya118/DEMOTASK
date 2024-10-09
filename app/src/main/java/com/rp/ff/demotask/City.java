package com.rp.ff.demotask;

// City.java
public class City {
    private String name;
    private boolean isFavorite;

    public City(String name) {
        this.name = name;
        this.isFavorite = false;
    }

    public String getName() {
        return name;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
