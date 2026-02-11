package com.projeto.renasce.dto.response;

public record AtletaResponse(
    Long id,
    String nome,
    String posicao,
    Integer numeroCamisa
) {}