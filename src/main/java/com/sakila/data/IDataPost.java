package com.sakila.data;

import java.util.List;

/**
 * Interface CRUD general
 * Autor: Alexander Rickards
 */

public interface IDataPost<T> {

    // INSERTAR
    boolean post(T entity);

    // ACTUALIZAR
    boolean put(T entity);

    // ELIMINAR
    boolean delete(int id);

    // BUSCAR POR ID
    T get(int id);

    // LISTAR TODOS
    List<T> getAll();
}