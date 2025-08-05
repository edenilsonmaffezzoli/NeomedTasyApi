package com.NeomedTasyApi.controller;

import com.NeomedTasyApi.dto.ExamesRequestDTO;
import com.NeomedTasyApi.service.ExamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exames")
public class ExamesController {

    @Autowired
    private ExamesService examesService;

    @PostMapping
    public ResponseEntity<String> receiveExamRequest(@RequestBody ExamesRequestDTO requestDTO) {
        try {
            // Validate input
            if (requestDTO == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Erro: Requisição inválida ou vazia");
            }

            examesService.processExamRequest(requestDTO);
            return ResponseEntity.ok("Laudo processado com sucesso"); // HTTP 200

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Dados inválidos: " + e.getMessage()); // HTTP 400
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno do servidor: " + e.getMessage()); // HTTP 500
        }
    }
}