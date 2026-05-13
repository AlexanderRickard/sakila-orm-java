package com.sakila.data;

import java.sql.Connection;

/**
 * Clase abstracta padre para ORM Sakila
 * Autor: Alexander Rickards
 */

public abstract class DataContext<T> implements IDataPost<T> {

    // Conexion protegida
    protected Connection connection;

    /**
     * Constructor
     */
    public DataContext() {

        connection = MySQLConnection.getConnection();
    }

    /**
     * Metodo final:
     * no puede ser override
     */
    public final void showConnectionStatus() {

        if (connection != null) {

            System.out.println("Conexion activa");

        } else {

            System.out.println("Sin conexion");
        }
    }
}