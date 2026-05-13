package com.sakila.reports;

import com.sakila.data.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Reporte que muestra actores y peliculas relacionadas.
 * @author Alexander
 */
public class ActorFilmReport {

    public void mostrarActoresYPeliculas() {

        String sql = """
                SELECT 
                    a.first_name,
                    a.last_name,
                    f.title
                FROM actor a
                INNER JOIN film_actor fa ON a.actor_id = fa.actor_id
                INNER JOIN film f ON fa.film_id = f.film_id
                ORDER BY a.last_name, a.first_name
                LIMIT 30
                """;

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n===== ACTORES Y PELICULAS =====");

            while (rs.next()) {

                String actor = rs.getString("first_name") + " " + rs.getString("last_name");
                String pelicula = rs.getString("title");

                System.out.println(actor + " | Pelicula: " + pelicula);
            }

        } catch (SQLException e) {

            System.out.println("Error en reporte de actores y peliculas: " + e.getMessage());
        }
    }
}