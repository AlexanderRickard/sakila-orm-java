package com.sakila.reports;

import com.sakila.data.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Reporte de pagos por tienda, ciudad, pais y cliente.
 * @author Alexander
 */
public class PaymentReport {

    public void mostrarPagosPorTiendaCiudadPaisCliente() {

        String sql = """
                SELECT 
                    s.store_id,
                    ci.city,
                    co.country,
                    CONCAT(c.first_name, ' ', c.last_name) AS cliente,
                    COUNT(p.payment_id) AS cantidad_pagos,
                    SUM(p.amount) AS total_pagado,
                    AVG(p.amount) AS promedio_pago
                FROM payment p
                INNER JOIN customer c ON p.customer_id = c.customer_id
                INNER JOIN store s ON c.store_id = s.store_id
                INNER JOIN address a ON s.address_id = a.address_id
                INNER JOIN city ci ON a.city_id = ci.city_id
                INNER JOIN country co ON ci.country_id = co.country_id
                GROUP BY s.store_id, ci.city, co.country, cliente
                ORDER BY total_pagado DESC
                LIMIT 30
                """;

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n===== PAGOS POR TIENDA, CIUDAD, PAIS Y CLIENTE =====");

            while (rs.next()) {

                System.out.println(
                        "Tienda: " + rs.getInt("store_id") +
                                " | Ciudad: " + rs.getString("city") +
                                " | Pais: " + rs.getString("country") +
                                " | Cliente: " + rs.getString("cliente") +
                                " | Cantidad pagos: " + rs.getInt("cantidad_pagos") +
                                " | Total pagado: " + rs.getDouble("total_pagado") +
                                " | Promedio pago: " + rs.getDouble("promedio_pago")
                );
            }

        } catch (SQLException e) {

            System.out.println("Error en reporte de pagos: " + e.getMessage());
        }
    }
}