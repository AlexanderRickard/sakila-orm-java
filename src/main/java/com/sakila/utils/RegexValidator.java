package com.sakila.utils;

import java.util.regex.Pattern;

/**
 * Clase para validaciones usando expresiones regulares.
 * @author Alexander
 */
public class RegexValidator {

    /**
     * Valida formato de email.
     */
    public boolean validarEmail(String email) {

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        return Pattern.matches(regex, email);
    }

    /**
     * Valida telefono de 10 digitos.
     */
    public boolean validarTelefono(String telefono) {

        String regex = "^\\d{10}$";

        return Pattern.matches(regex, telefono);
    }
}