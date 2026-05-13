package com.sakila.controllers;

import com.sakila.data.DataContext;
import com.sakila.data.MySQLConnection;
import com.sakila.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador CRUD para la entidad Customer.
 * @author Alexander
 */
public class CustomerController extends DataContext<Customer> {

    @Override
    public boolean post(Customer entity) {
        String sql = "INSERT INTO customer(store_id, first_name, last_name, email, address_id, active) " +
                "VALUES (?, ?, ?, ?, 1, ?)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, entity.getStoreId());
            stmt.setString(2, entity.getFirstName());
            stmt.setString(3, entity.getLastName());
            stmt.setString(4, entity.getEmail());
            stmt.setBoolean(5, entity.isActive());

            stmt.executeUpdate();
            System.out.println("Cliente insertado correctamente.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean put(Customer entity) {
        String sql = "UPDATE customer SET store_id = ?, first_name = ?, last_name = ?, email = ?, active = ? WHERE customer_id = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, entity.getStoreId());
            stmt.setString(2, entity.getFirstName());
            stmt.setString(3, entity.getLastName());
            stmt.setString(4, entity.getEmail());
            stmt.setBoolean(5, entity.isActive());
            stmt.setInt(6, entity.getCustomerId());

            stmt.executeUpdate();
            System.out.println("Cliente actualizado correctamente.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "UPDATE customer SET active = 0 WHERE customer_id = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Cliente desactivado correctamente.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al desactivar cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Customer get(int id) {
        String sql = "SELECT customer_id, store_id, first_name, last_name, email, active FROM customer WHERE customer_id = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getInt("customer_id"),
                        rs.getInt("store_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getBoolean("active")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();

        String sql = "SELECT customer_id, store_id, first_name, last_name, email, active FROM customer LIMIT 20";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("customer_id"),
                        rs.getInt("store_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getBoolean("active")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }

        return customers;
    }
}