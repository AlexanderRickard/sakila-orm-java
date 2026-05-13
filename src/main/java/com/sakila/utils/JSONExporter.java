package com.sakila.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase para exportar objetos a formato JSON.
 * @author Alexander
 */
public class JSONExporter {

    public void exportar(String nombreArchivo, Object data) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(nombreArchivo)) {

            gson.toJson(data, writer);

            System.out.println("Archivo JSON exportado correctamente: " + nombreArchivo);

        } catch (IOException e) {

            System.out.println("Error exportando JSON: " + e.getMessage());
        }
    }
}