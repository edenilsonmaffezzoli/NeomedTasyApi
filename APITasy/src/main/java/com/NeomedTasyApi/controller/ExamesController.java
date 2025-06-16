package com.NeomedTasyApi.controller;

import com.NeomedTasyApi.dto.ExamesRequestDTO;
import com.NeomedTasyApi.service.ExamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/exames")
public class ExamesController {

    @Autowired
    private ExamesService examesService;

    @PostMapping
    public void receiveExamRequest(@RequestBody ExamesRequestDTO requestDTO) throws UnsupportedEncodingException {
        //throw new Exception("qualquer string si dentro");
        examesService.processExamRequest(requestDTO);

    }
}
