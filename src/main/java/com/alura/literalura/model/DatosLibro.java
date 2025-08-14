package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import java.util.Optional;
import java.util.List;

@JsonIgnoreProperties (ignoreUnknown = true)
public record DatosLibro(
    @JsonAlias("title") String titulo,
    @JsonAlias("authors") List<DatosPersona> autores,
    @JsonAlias("languages") List<String> lenguajes,
    @JsonAlias("download_count") Integer numeroDeDescargas,
    @JsonAlias("id") String id,
    @JsonAlias("summaries") List<String> sinopsis
) {}
