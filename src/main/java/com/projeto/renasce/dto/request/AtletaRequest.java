package com.projeto.renasce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record AtletaRequest(
    @NotBlank String nome,
    String posicao,
    @Positive Integer numeroCamisa,
    Double altura,
    String fotoUrl
) {}