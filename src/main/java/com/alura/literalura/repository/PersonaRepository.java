package com.alura.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alura.literalura.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findByNombreAndNacimientoAndFallecimiento(String nombre, Integer nacimiento, Integer fallecimiento);

    // Listar todos los autores registrados ordenados por nombre
    List<Persona> findAllByOrderByNombreAsc();

    // Listar autores vivos en un año específico
    @Query("SELECT p FROM Persona p WHERE p.nacimiento <= :anio AND p.fallecimiento >= :anio")
    List<Persona> findAutoresVivosEn(@Param("anio") Integer anio);
}
