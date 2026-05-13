package com.sakila.models;

/**
 * Modelo que representa una película de la base de datos Sakila.
 * @author Alexander
 */
public class Film {

    private int filmId;
    private String title;
    private String description;
    private int releaseYear;
    private double rentalRate;

    public Film() {
    }

    public Film(int filmId, String title, String description, int releaseYear, double rentalRate) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.rentalRate = rentalRate;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }

    @Override
    public String toString() {
        return filmId + " | " + title + " | " + releaseYear + " | RD/US$: " + rentalRate;
    }
}