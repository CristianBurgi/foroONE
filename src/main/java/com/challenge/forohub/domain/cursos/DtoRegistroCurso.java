package com.challenge.forohub.domain.cursos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record DtoRegistroCurso(

        @NotBlank
        String nombre,
        @Nullable
        String categoria
) {
}
