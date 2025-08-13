package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosPersona(
    @JsonAlias("name") String nombre,
    @JsonAlias("birth_year") Integer nacimiento,
    @JsonAlias("death_year") Integer fallecimiento
) {}
