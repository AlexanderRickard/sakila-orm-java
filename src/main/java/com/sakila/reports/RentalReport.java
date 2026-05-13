package com.sakila.reports;

import com.sakila.data.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Reporte de rentas por tienda, ciudad, pais y cliente.
 * @author Alexander
 */
public class RentalReport {

    public void mostrarRentasPorTiendaCiudadPaisCliente() {

        String sql = """
                SELECT 
                    s.store_id,
                    ci.city,
                    co.country,
                    CONCAT(c.first_name, ' ', c.last_name) AS cliente,
                    COUNT(r.rental_id) AS total_rentas
                FROM rental r
                INNER JOIN customer c ON r.customer_id = c.customer_id
                INNER JOIN store s ON c.store_id = s.store_id
                INNER JOIN address a ON s.address_id = a.address_id
                INNER JOIN city ci ON a.city_id = ci.city_id
                INNER JOIN country co ON ci.country_id = co.country_id
                GROUP BY s.store_id, ci.city, co.country, cliente
                ORDER BY total_rentas DESC
                LIMIT 30
                """;

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n===== RENTAS POR TIENDA, CIUDAD, PAIS Y CLIENTE =====");

            while (rs.next()) {
                System.out.println(
                        "Tienda: " + rs.getInt("store_id") +
                                " | Ciudad: " + rs.getString("city") +
                                " | Pais: " + rs.getString("country") +
                                " | Cliente: " + rs.getString("cliente") +
                                " | Total rentas: " + rs.getInt("total_rentas")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error en reporte de rentas: " + e.getMessage());
        }
    }
}