package com.sakila.reports;

import com.sakila.data.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Reporte de inventario de peliculas.
 * @author Alexander
 */
public class InventoryReport {

    public void mostrarInventarioPeliculas() {

        String sql = """
                SELECT f.title, COUNT(i.inventory_id) AS total
                FROM film f
                INNER JOIN inventory i ON f.film_id = i.film_id
                GROUP BY f.title
                ORDER BY total DESC
                LIMIT 20
                """;

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n===== INVENTARIO DE PELICULAS =====");

            while (rs.next()) {
                System.out.println(
                        rs.getString("title") +
                                " | Cantidad en inventario: " +
                                rs.getInt("total")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error en reporte de inventario: " + e.getMessage());
        }
    }
}