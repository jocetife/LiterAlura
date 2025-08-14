package com.alura.literalura.model;

import java.util.List;

import jakarta.persistence.CascadeType;
//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    private String id;
    private String titulo;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "persona_id")
    private Persona autor;
    private Integer numeroDeDescargas;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> lenguajes;
    @Column(columnDefinition = "TEXT")
    private String sinopsis;

    public Libro() {}
    public Libro(DatosLibro datos) {
        this.id = datos.id();
        this.titulo = datos.titulo();
        this.autor = new Persona(datos.autores().get(0));
        this.numeroDeDescargas = datos.numeroDeDescargas();
        this.lenguajes = datos.lenguajes();
        this.sinopsis = datos.sinopsis().get(0);
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
    public Persona getAutor() {
        return autor;
    }
    public void setAutor(Persona autor) {
        this.autor = autor;
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
    public String getSinopsis() {
        return sinopsis;
    }
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    @Override
    public String toString() {
        return "-------------LIBRO-------------\n" +
                "Id=        '" + id + '\'' + "\n" +
                "Titulo=    '" + titulo + '\'' + "\n" +
                "Autor=     " + autor.getNombre() + "\n" +
                "Descargas= " + numeroDeDescargas + "\n" +
                "Lenguajes= " + lenguajes + "\n" +
                "-------------------------------\n";
    }
}
