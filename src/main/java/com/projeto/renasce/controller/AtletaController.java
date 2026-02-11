package com.projeto.renasce.controller;

import com.projeto.renasce.dto.request.AtletaRequest; // Ajuste se for AtletaRequestDTO
import com.projeto.renasce.dto.response.AtletaResponse; // Ajuste se for AtletaResponseDTO
import com.projeto.renasce.service.AtletaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atletas")
public class AtletaController {

    private final AtletaService service;

    public AtletaController(AtletaService service) {
        this.service = service;
    }

    // ADICIONE ESTE MÉTODO PARA RESOLVER O ERRO 405
    @GetMapping
    public ResponseEntity<List<AtletaResponse>> listar() {
        List<AtletaResponse> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<AtletaResponse> salvar(@Valid @RequestBody AtletaRequest request) {
        AtletaResponse response = service.criar(request);
        return ResponseEntity.ok(response);
    }
}