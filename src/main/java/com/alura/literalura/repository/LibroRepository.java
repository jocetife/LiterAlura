package com.alura.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.literalura.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, String> {
    // Aquí puedes definir métodos específicos para manejar Libro si es necesario
    // Por ejemplo, buscar por título, autor, etc.

}
