package com.sakila.utils;

import com.sakila.models.Film;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Clase para exportar peliculas a CSV.
 * @author Alexander
 */
public class CSVExporter {

    public void exportarPeliculasCSV(String nombreArchivo, List<Film> peliculas) {

        try (FileWriter writer = new FileWriter(nombreArchivo)) {

            writer.append("filmId,title,description,releaseYear,rentalRate\n");

            for (Film film : peliculas) {

                writer.append(String.valueOf(film.getFilmId())).append(",");
                writer.append(film.getTitle()).append(",");
                writer.append(film.getDescription().replace(",", " ")).append(",");
                writer.append(String.valueOf(film.getReleaseYear())).append(",");
                writer.append(String.valueOf(film.getRentalRate())).append("\n");
            }

            System.out.println("Archivo CSV exportado correctamente: " + nombreArchivo);

        } catch (IOException e) {

            System.out.println("Error exportando CSV: " + e.getMessage());
        }
    }
}