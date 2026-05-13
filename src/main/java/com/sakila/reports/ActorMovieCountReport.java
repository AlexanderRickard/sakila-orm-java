package com.sakila.reports;

import com.sakila.data.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

/**
 * Reporte usando HashMap para contar peliculas por actor.
 * @author Alexander
 */
public class ActorMovieCountReport {

    public void mostrarCantidadPeliculasPorActor() {

        HashMap<String, Integer> actorMovies = new HashMap<>();

        String sql = """
                SELECT 
                    CONCAT(a.first_name, ' ', a.last_name) AS actor,
                    COUNT(fa.film_id) AS total_peliculas
                FROM actor a
                INNER JOIN film_actor fa
                ON a.actor_id = fa.actor_id
                GROUP BY actor
                ORDER BY total_peliculas DESC
                LIMIT 20
                """;

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                actorMovies.put(
                        rs.getString("actor"),
                        rs.getInt("total_peliculas")
                );
            }

            System.out.println("\n===== CANTIDAD DE PELICULAS POR ACTOR =====");

            for (Map.Entry<String, Integer> entry : actorMovies.entrySet()) {

                System.out.println(
                        entry.getKey() +
                                " | Total peliculas: " +
                                entry.getValue()
                );
            }

        } catch (SQLException e) {

            System.out.println("Error en reporte HashMap: " + e.getMessage());
        }
    }
}