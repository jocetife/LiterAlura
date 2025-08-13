package com.alura.literalura.model;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    private String id;
    private String titulo;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
    name = "libro_persona",
    joinColumns = @JoinColumn(name = "libro_id"),
    inverseJoinColumns = @JoinColumn(name = "persona_id"))
    private List<Persona> autores;
    private Integer numeroDeDescargas;
    private List<String> lenguajes;
    @Column(columnDefinition = "TEXT")
    private List<String> sinopsis;

    public Libro() {}
    public Libro(DatosLibro datos) {
        this.id = datos.id();
        this.titulo = datos.titulo();
        this.autores = datos.autores();
        this.numeroDeDescargas = datos.numeroDeDescargas();
        this.lenguajes = datos.lenguajes();
        this.sinopsis = datos.sinopsis();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public List<Persona> getAutores() {
        return autores;
    }
    public void setAutores(List<Persona> autores) {
        this.autores = autores;
    }
    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }
    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }
    public List<String> getLenguajes() {
        return lenguajes;
    }
    public void setLenguajes(List<String> lenguajes) {
        this.lenguajes = lenguajes;
    }
    public List<String> getSinopsis() {
        return sinopsis;
    }
    public void setSinopsis(List<String> sinopsis) {
        this.sinopsis = sinopsis;
    }

}
