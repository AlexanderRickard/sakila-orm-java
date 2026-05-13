package com.sakila.utils;

import java.util.List;

/**
 * Clase utilitaria para imprimir listas usando wildcard <?>.
 * @author Alexander
 */
public class GenericPrinter {

    public void imprimirLista(List<?> lista) {

        System.out.println("\n===== IMPRESION GENERICA CON WILDCARD <?> =====");

        for (Object item : lista) {
            System.out.println(item);
        }
    }
}