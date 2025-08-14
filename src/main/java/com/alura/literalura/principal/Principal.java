package com.alura.literalura.principal;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;

import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;
import com.alura.literalura.model.DatosLibros;
import com.alura.literalura.model.DatosPersona;
import com.alura.literalura.model.Libro;
import com.alura.literalura.model.Persona;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.repository.PersonaRepository;
import com.alura.literalura.model.DatosLibro;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE_BUSQUEDA = "https://gutendex.com/books/?search=";
    //private final String URL_BASE = "https://gutendex.com/books/";
    private LibroRepository repository;
    private PersonaRepository personaRepository;

    public Principal() {}
    @Autowired
    public Principal(LibroRepository repository, PersonaRepository personaRepository) {
        this.repository = repository;
        this.personaRepository = personaRepository;
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
        System.out.print("Por favor escribe el nombre del libro que deseas buscar y registrar: ");
        var nombreLibro = scanner.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE_BUSQUEDA + nombreLibro.replace(" ", "%20"));
        
        DatosLibros datos = conversor.obtenerDatos(json, DatosLibros.class);
        
        if (datos.libros().isEmpty()) {
            System.out.println("No se encontraron libros con el nombre: " + nombreLibro + "\n");
        } else {
            DatosLibro libro = datos.libros().get(0);
            DatosPersona datosAutor = libro.autores().get(0);

            Persona personaEntity = personaRepository.findByNombreAndNacimientoAndFallecimiento(
                datosAutor.nombre(), datosAutor.nacimiento(), datosAutor.fallecimiento()
            ).orElseGet(() -> {
                // Si no existe, crear y guardar
                Persona nueva = new Persona(datosAutor);
                return personaRepository.save(nueva);
            });
            System.out.println(libro);

            Libro libroExistente = repository.findById(libro.id()).orElse(null);
            if (libroExistente != null) {
                System.out.println("El libro ya está registrado: \n" + libroExistente);
                return;
            }

            // Guardar el libro en la base de datos
            Libro libroEntity = new Libro(libro);
            libroEntity.setAutor(personaEntity); // asigna el autor
            repository.save(libroEntity);
        }
    }

    private void ListarLibrosRegistrados() {
        var libros = repository.findAll();
        libros.forEach(System.out::println);
    }

    private void ListarAutoresRegistrados() {
        var autores = personaRepository.findAllByOrderByNombreAsc();
        autores.forEach(System.out::println);
    }

    private void ListarAutoresVivosEnAnio() {
        System.out.print("Ingresa el año: ");
        int anio = scanner.nextInt();
        scanner.nextLine();

        var autores = personaRepository.findAutoresVivosEn(anio);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + anio);
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void ListarLibrosPorIdioma() {
        
        var libros = repository.findAll();
        System.out.println("Idiomas encontrados: ");

        Set<String> idiomas = new TreeSet<>(); //para que no se repitan
        libros.forEach(l -> idiomas.addAll(l.getLenguajes()));

        idiomas.forEach(System.out::println);

        System.out.print("Ingresa el idioma: ");
        String idiomaSeleccionado = scanner.nextLine();

        if (idiomas.contains(idiomaSeleccionado)) {
            var librosFiltrados = libros.stream().filter(l -> l.getLenguajes().stream().anyMatch(lang -> lang.equalsIgnoreCase(idiomaSeleccionado))).toList();
            // Mostrar libros filtrados
            System.out.println("Libros en '" + idiomaSeleccionado + "': ");
            librosFiltrados.forEach(System.out::println);
        } else {
            System.out.println("No se encontraron libros en el idioma: " + idiomaSeleccionado);
        }
    }
}
