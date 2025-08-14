package com.alura.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.literalura.model.Libro;
import com.alura.literalura.model.Persona;

public interface LibroRepository extends JpaRepository<Libro, String> {

    Optional<Libro> findById(String id);

    // Buscar libros por título (contenga texto, ignorando mayúsculas/minúsculas)
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    // Buscar libros por idioma
    List<Libro> findByLenguajesContainingIgnoreCase(String idioma);

    // Buscar libros de un autor específico
    List<Libro> findByAutor(Persona autor);
}
