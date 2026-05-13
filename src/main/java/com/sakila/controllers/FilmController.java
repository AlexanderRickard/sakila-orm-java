package com.sakila.controllers;

import com.sakila.data.DataContext;
import com.sakila.data.MySQLConnection;
import com.sakila.models.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmController extends DataContext<Film> {

    @Override
    public boolean post(Film entity) {
        String sql = "INSERT INTO film(title, description, release_year, language_id, rental_duration, rental_rate, replacement_cost) " +
                "VALUES (?, ?, ?, 1, 3, ?, 19.99)";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entity.getTitle());
            stmt.setString(2, entity.getDescription());
            stmt.setInt(3, entity.getReleaseYear());
            stmt.setDouble(4, entity.getRentalRate());

            stmt.executeUpdate();
            System.out.println("Película insertada correctamente.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar película: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean put(Film entity) {
        String sql = "UPDATE film SET title = ?, description = ?, release_year = ?, rental_rate = ? WHERE film_id = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entity.getTitle());
            stmt.setString(2, entity.getDescription());
            stmt.setInt(3, entity.getReleaseYear());
            stmt.setDouble(4, entity.getRentalRate());
            stmt.setInt(5, entity.getFilmId());

            stmt.executeUpdate();
            System.out.println("Película actualizada correctamente.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar película: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM film WHERE film_id = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
            System.out.println("Película eliminada correctamente.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar película: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Film get(int id) {
        String sql = "SELECT film_id, title, description, release_year, rental_rate FROM film WHERE film_id = ?";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Film(
                        rs.getInt("film_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("release_year"),
                        rs.getDouble("rental_rate")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar película: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Film> getAll() {
        List<Film> films = new ArrayList<>();

        String sql = "SELECT film_id, title, description, release_year, rental_rate FROM film LIMIT 20";

        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                films.add(new Film(
                        rs.getInt("film_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("release_year"),
                        rs.getDouble("rental_rate")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar películas: " + e.getMessage());
        }

        return films;
    }
}