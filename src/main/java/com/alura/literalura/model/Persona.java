package com.alura.literalura.model;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "personas")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer nacimiento;
    private Integer fallecimiento;
    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Persona() {}
    public Persona(DatosPersona datos) {
        this.nombre = datos.nombre();
        this.nacimiento = datos.nacimiento();
        this.fallecimiento = datos.fallecimiento();
    }

    public List<Libro> getLibros() {
        return libros;
    }
    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getNacimiento() {
        return nacimiento;
    }
    public void setNacimiento(Integer nacimiento) {
        this.nacimiento = nacimiento;
    }
    public Integer getFallecimiento() {
        return fallecimiento;
    }
    public void setFallecimiento(Integer fallecimiento) {
        this.fallecimiento = fallecimiento;
    }

    @Override
    public String toString() {
        return "-------------AUTOR-------------\n" +
                "Id=        '" + id + '\'' + "\n" +
                "Nombre=    '" + nombre + '\'' + "\n" +
                "Nacimiento= " + nacimiento + "\n" +
                "Fallecimiento= " + fallecimiento + "\n" +
                "Libros=    " + (libros.stream().map(Libro::getTitulo).collect(Collectors.joining(", "))) + "\n" +
                "-------------------------------\n";
    }
}
