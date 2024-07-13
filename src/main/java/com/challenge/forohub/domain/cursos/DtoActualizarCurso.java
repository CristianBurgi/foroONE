package com.challenge.forohub.domain.cursos;

import jakarta.validation.constraints.NotNull;

public record DtoActualizarCurso(
        @NotNull
        Long id,
        String nombre,
        String categoria
) {
}
