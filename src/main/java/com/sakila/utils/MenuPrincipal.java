package com.sakila.utils;
import com.sakila.utils.GenericPrinter;
import com.sakila.controllers.ActorController;
import com.sakila.controllers.CustomerController;
import com.sakila.controllers.FilmController;

import com.sakila.models.Actor;
import com.sakila.models.Customer;
import com.sakila.models.Film;

import com.sakila.reports.ActorFilmReport;
import com.sakila.reports.ActorMovieCountReport;
import com.sakila.reports.InventoryReport;
import com.sakila.reports.PaymentReport;
import com.sakila.reports.RentalReport;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {

    private final Scanner scanner = new Scanner(System.in);

    public void iniciar() {

        int opcion = -1;

        do {

            System.out.println("\n==============================");
            System.out.println("      SISTEMA SAKILA ORM");
            System.out.println("==============================");
            System.out.println("1. Listar Actores");
            System.out.println("2. Listar Peliculas");
            System.out.println("3. Listar Clientes");
            System.out.println("4. Reporte Inventario de Peliculas");
            System.out.println("5. Reporte Actores y Peliculas");
            System.out.println("6. Reporte Rentas por Tienda/Ciudad/Pais/Cliente");
            System.out.println("7. Reporte Pagos por Tienda/Ciudad/Pais/Cliente");
            System.out.println("8. Exportar Peliculas JSON");
            System.out.println("9. Exportar Peliculas CSV");
            System.out.println("10. Validar Email");
            System.out.println("11. Reporte HashMap Peliculas por Actor");
            System.out.println("12. Insertar Pelicula");
            System.out.println("13. Buscar Pelicula por ID");
            System.out.println("14. Actualizar Pelicula");
            System.out.println("15. Eliminar Pelicula");
            System.out.println("16. Imprimir Peliculas con Wildcard");
            System.out.println("0. Salir");
            System.out.println("==============================");
            System.out.print("Seleccione una opcion: ");

            try {

                opcion = Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                opcion = -1;
            }

            switch (opcion) {

                case 1:
                    listarActores();
                    break;

                case 2:
                    listarPeliculas();
                    break;

                case 3:
                    listarClientes();
                    break;

                case 4:
                    reporteInventario();
                    break;

                case 5:
                    reporteActoresPeliculas();
                    break;

                case 6:
                    reporteRentas();
                    break;

                case 7:
                    reportePagos();
                    break;

                case 8:
                    exportarPeliculasJSON();
                    break;

                case 9:
                    exportarPeliculasCSV();
                    break;

                case 10:
                    validarEmail();
                    break;

                case 11:
                    reporteHashMapPeliculasActor();
                    break;

                case 12:
                    insertarPelicula();
                    break;

                case 13:
                    buscarPeliculaPorId();
                    break;

                case 14:
                    actualizarPelicula();
                    break;

                case 15:
                    eliminarPelicula();
                    break;

                case 16:
                    imprimirPeliculasWildcard();
                    break;

                case 0:
                    System.out.println("\nSaliendo del sistema...");
                    break;

                default:
                    System.out.println("\nOpcion invalida. Intente nuevamente.");
            }

        } while (opcion != 0);
    }

    private void listarActores() {

        ActorController actorController = new ActorController();

        System.out.println("\n===== LISTADO DE ACTORES =====");

        for (Actor actor : actorController.getAll()) {

            System.out.println(actor);
        }
    }

    private void listarPeliculas() {

        FilmController filmController = new FilmController();

        System.out.println("\n===== LISTADO DE PELICULAS =====");

        for (Film film : filmController.getAll()) {

            System.out.println(film);
        }
    }

    private void listarClientes() {

        CustomerController customerController = new CustomerController();

        System.out.println("\n===== LISTADO DE CLIENTES =====");

        for (Customer customer : customerController.getAll()) {

            System.out.println(customer);
        }
    }

    private void reporteInventario() {

        InventoryReport report = new InventoryReport();

        report.mostrarInventarioPeliculas();
    }

    private void reporteActoresPeliculas() {

        ActorFilmReport report = new ActorFilmReport();

        report.mostrarActoresYPeliculas();
    }

    private void reporteRentas() {

        RentalReport report = new RentalReport();

        report.mostrarRentasPorTiendaCiudadPaisCliente();
    }

    private void reportePagos() {

        PaymentReport report = new PaymentReport();

        report.mostrarPagosPorTiendaCiudadPaisCliente();
    }

    private void exportarPeliculasJSON() {

        FilmController filmController = new FilmController();

        List<Film> peliculas = filmController.getAll();

        JSONExporter exporter = new JSONExporter();

        exporter.exportar("peliculas.json", peliculas);
    }

    private void exportarPeliculasCSV() {

        FilmController filmController = new FilmController();

        List<Film> peliculas = filmController.getAll();

        CSVExporter exporter = new CSVExporter();

        exporter.exportarPeliculasCSV("peliculas.csv", peliculas);
    }

    private void validarEmail() {

        RegexValidator validator = new RegexValidator();

        System.out.print("\nIngrese un email: ");

        String email = scanner.nextLine();

        boolean valido = validator.validarEmail(email);

        if (valido) {

            System.out.println("Email valido.");

        } else {

            System.out.println("Email invalido.");
        }
    }

    private void insertarPelicula() {

        FilmController filmController = new FilmController();

        System.out.println("\n===== INSERTAR PELICULA =====");

        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();

        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();

        System.out.print("Ano de lanzamiento: ");
        int releaseYear = Integer.parseInt(scanner.nextLine());

        System.out.print("Precio de renta: ");
        double rentalRate = Double.parseDouble(scanner.nextLine());

        Film film = new Film();
        film.setTitle(titulo);
        film.setDescription(descripcion);
        film.setReleaseYear(releaseYear);
        film.setRentalRate(rentalRate);

        boolean resultado = filmController.post(film);

        if (resultado) {
            System.out.println("Pelicula agregada correctamente.");
        } else {
            System.out.println("No se pudo agregar la pelicula.");
        }
    }

    private void reporteHashMapPeliculasActor() {

        ActorMovieCountReport report = new ActorMovieCountReport();

        report.mostrarCantidadPeliculasPorActor();

    }
    private void buscarPeliculaPorId() {

        FilmController filmController = new FilmController();

        System.out.print("\nIngrese el ID de la pelicula: ");

        int id = Integer.parseInt(scanner.nextLine());

        Film film = filmController.get(id);

        if (film != null) {

            System.out.println("\n===== PELICULA ENCONTRADA =====");
            System.out.println(film);

        } else {

            System.out.println("No se encontro la pelicula.");
        }
    }
    private void actualizarPelicula() {

        FilmController filmController = new FilmController();

        System.out.print("\nIngrese el ID de la pelicula a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Film filmExistente = filmController.get(id);

        if (filmExistente == null) {
            System.out.println("No se encontro la pelicula.");
            return;
        }

        System.out.println("\nPelicula actual:");
        System.out.println(filmExistente);

        System.out.print("Nuevo titulo: ");
        String titulo = scanner.nextLine();

        System.out.print("Nueva descripcion: ");
        String descripcion = scanner.nextLine();

        System.out.print("Nuevo ano de lanzamiento: ");
        int releaseYear = Integer.parseInt(scanner.nextLine());

        System.out.print("Nuevo precio de renta: ");
        double rentalRate = Double.parseDouble(scanner.nextLine());

        Film film = new Film();
        film.setFilmId(id);
        film.setTitle(titulo);
        film.setDescription(descripcion);
        film.setReleaseYear(releaseYear);
        film.setRentalRate(rentalRate);

        boolean resultado = filmController.put(film);

        if (resultado) {
            System.out.println("Pelicula actualizada correctamente.");
        } else {
            System.out.println("No se pudo actualizar la pelicula.");
        }
    }
    private void imprimirPeliculasWildcard() {

        FilmController filmController = new FilmController();

        GenericPrinter printer = new GenericPrinter();

        printer.imprimirLista(filmController.getAll());
    }

    private void eliminarPelicula() {

        FilmController filmController = new FilmController();

        System.out.print("\nIngrese el ID de la pelicula a eliminar: ");

        int id = Integer.parseInt(scanner.nextLine());

        Film film = filmController.get(id);

        if (film == null) {

            System.out.println("No se encontro la pelicula.");
            return;
        }

        System.out.println("\nPelicula encontrada:");
        System.out.println(film);

        System.out.print("\nConfirma eliminar? (s/n): ");

        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("s")) {

            boolean resultado = filmController.delete(id);

            if (resultado) {

                System.out.println("Pelicula eliminada correctamente.");

            } else {

                System.out.println("No se pudo eliminar la pelicula.");
            }

        } else {

            System.out.println("Operacion cancelada.");
        }
    }
}



