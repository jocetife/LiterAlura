package com.alura.literalura.principal;

import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;
import com.alura.literalura.model.DatosLibros;
import com.alura.literalura.model.Libro;
import com.alura.literalura.model.Persona;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.model.DatosLibro;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE_BUSQUEDA = "https://gutendex.com/books/?search=";
    //private final String URL_BASE = "https://gutendex.com/books/";
    private LibroRepository repository;

    public Principal() {}
    @Autowired
    public Principal(LibroRepository repository) {
        this.repository = repository;
    }

    public void muestraelMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Registrar libros por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados                                  
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    RegistrarLibros();
                    break;
                case 2:
                    ListarLibrosRegistrados();
                    break;
                case 3:
                    ListarAutoresRegistrados();
                    break;
                case 4:
                    ListarAutoresVivosEnAnio();
                    break;
                case 5:
                    ListarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }
    
    private void RegistrarLibros() {
        System.out.print("Por favor escribe el nombre del libro que deseas buscar: ");
        var nombreLibro = scanner.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE_BUSQUEDA + nombreLibro.replace(" ", "%20"));
        
        DatosLibros datos = conversor.obtenerDatos(json, DatosLibros.class);
        
        if (datos.libros().isEmpty()) {
            System.out.println("No se encontraron libros con el nombre: " + nombreLibro);
        } else {
            DatosLibro libro = datos.libros().get(0);
            String nombresAutores = libro.autores().stream().map(Persona::getNombre).collect(Collectors.joining(", "));
            System.out.println("--> Titulo: " + libro.titulo()
                    + ", ID: " + libro.id()
                    + ", Numero de descargas: " + libro.numeroDeDescargas()
                    + "\n--> Autor: " + nombresAutores
                    + ", Lenguajes: " + libro.lenguajes()
                    + "\n--> Sinopsis: " + libro.sinopsis() + "\n-------------------");
            // Guardar el libro en la base de datos
            Libro libroEntity = new Libro(libro);
            repository.save(libroEntity);
        }

    }

    private void ListarLibrosRegistrados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ListarLibrosRegistrados'");
    }

    private void ListarAutoresRegistrados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ListarAutoresRegistrados'");
    }

    private void ListarAutoresVivosEnAnio() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ListarAutoresVivosEnAnio'");
    }

    private void ListarLibrosPorIdioma() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ListarLibrosPorIdioma'");
    }
}
