package com.NeomedTasyApi.controller;

import com.NeomedTasyApi.dto.ExamesRequestDTO;
import com.NeomedTasyApi.service.ExamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/exames")
public class ExamesController {

    @Autowired
    private ExamesService examesService;

    @PostMapping
    public ResponseEntity<String> receiveExamRequest(@RequestBody ExamesRequestDTO requestDTO) {
        try {
            examesService.processExamRequest(requestDTO);
            return ResponseEntity.ok("Laudo processado com sucesso"); // HTTP 200
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.ok("Erro ao processar laudo"); // Ainda retorna 200
        }
    }
}
