package com.challenge.forohub.domain.cursos;

public record DtoListadoCurso(
        Long id,
        String nombre,
        String categoria
) {
    public DtoListadoCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
