package com.sakila.controllers;

import com.sakila.data.DataContext;
import com.sakila.models.Actor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador Actor
 * Autor: Alexander Rickards
 */

public class ActorController extends DataContext<Actor> {

    /**
     * INSERTAR
     */
    @Override
    public boolean post(Actor actor) {

        String sql = "INSERT INTO actor(first_name, last_name) VALUES (?, ?)";

        try {

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, actor.getFirstName());
            statement.setString(2, actor.getLastName());

            statement.executeUpdate();

            System.out.println("Actor insertado");

            return true;

        } catch (SQLException e) {

            System.out.println("Error insertando actor");
            e.printStackTrace();

            return false;
        }
    }

    /**
     * ACTUALIZAR
     */
    @Override
    public boolean put(Actor actor) {

        String sql =
                "UPDATE actor SET first_name=?, last_name=? WHERE actor_id=?";

        try {

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, actor.getFirstName());
            statement.setString(2, actor.getLastName());
            statement.setInt(3, actor.getActorId());

            statement.executeUpdate();

            System.out.println("Actor actualizado");

            return true;

        } catch (SQLException e) {

            System.out.println("Error actualizando actor");
            e.printStackTrace();

            return false;
        }
    }

    /**
     * ELIMINAR
     */
    @Override
    public boolean delete(int id) {

        String sql = "DELETE FROM actor WHERE actor_id=?";

        try {

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, id);

            statement.executeUpdate();

            System.out.println("Actor eliminado");

            return true;

        } catch (SQLException e) {

            System.out.println("Error eliminando actor");
            e.printStackTrace();

            return false;
        }
    }

    /**
     * BUSCAR POR ID
     */
    @Override
    public Actor get(int id) {

        String sql =
                "SELECT * FROM actor WHERE actor_id=?";

        try {

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet result =
                    statement.executeQuery();

            if (result.next()) {

                return new Actor(
                        result.getInt("actor_id"),
                        result.getString("first_name"),
                        result.getString("last_name")
                );
            }

        } catch (SQLException e) {

            System.out.println("Error buscando actor");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * LISTAR TODOS
     */
    @Override
    public List<Actor> getAll() {

        List<Actor> actors =
                new ArrayList<>();

        String sql = "SELECT * FROM actor";

        try {

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet result =
                    statement.executeQuery();

            while (result.next()) {

                Actor actor = new Actor(

                        result.getInt("actor_id"),
                        result.getString("first_name"),
                        result.getString("last_name")
                );

                actors.add(actor);
            }

        } catch (SQLException e) {

            System.out.println("Error listando actores");
            e.printStackTrace();
        }

        return actors;
    }
}