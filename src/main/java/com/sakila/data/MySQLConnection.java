package com.sakila.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Conexion a MySQL Sakila Database
 * Autor: Alexander Rickards
 */
public class MySQLConnection {

    // URL de conexion
    private static final String URL =
            "jdbc:mysql://localhost:3306/sakila";

    // Usuario MySQL
    private static final String USER = "root";

    // Password MySQL
    private static final String PASSWORD = "Root12345!";

    /**
     * Metodo para obtener conexion
     */
    public static Connection getConnection() {

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("Conexion exitosa a Sakila DB");

        } catch (SQLException e) {

            System.out.println("Error de conexion");
            e.printStackTrace();

        }

        return connection;
    }
}