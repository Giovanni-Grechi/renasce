package com.projeto.renasce.service;

import com.projeto.renasce.dto.request.AtletaRequest;
import com.projeto.renasce.dto.response.AtletaResponse;
import com.projeto.renasce.model.Atleta;
import com.projeto.renasce.repository.AtletaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AtletaService {
    private final AtletaRepository repository;

    public AtletaService(AtletaRepository repository) {
        this.repository = repository;
    }

    public AtletaResponse criar(AtletaRequest request) {
        // Mapeamento: Request -> Entity
        Atleta atleta = new Atleta();
        atleta.setNome(request.nome());
        atleta.setPosicao(request.posicao());
        atleta.setNumeroCamisa(request.numeroCamisa());
        atleta.setAltura(request.altura());
        atleta.setFotoUrl(request.fotoUrl());

        Atleta salvo = repository.save(atleta);

        // Mapeamento: Entity -> Response
        return new AtletaResponse(
            salvo.getId(), 
            salvo.getNome(), 
            salvo.getPosicao(), 
            salvo.getNumeroCamisa()
        );
    }
    
    public List<AtletaResponse> listarTodos() {
        return repository.findAll().stream()
            .map(atleta -> new AtletaResponse(
                atleta.getId(), 
                atleta.getNome(), 
                atleta.getPosicao(), 
                atleta.getNumeroCamisa()))
            .toList();
    }
}