package com.NeomedTasyApi.service;

import com.NeomedTasyApi.repository.ExamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.NeomedTasyApi.dto.ExamesRequestDTO;
import com.NeomedTasyApi.service.ExamesService;

import java.io.UnsupportedEncodingException;

@Service
public class ExamesService {
    @Autowired
    private ExamsRepository examsRepository;
    
    public void processExamRequest(ExamesRequestDTO requestDTO)  throws UnsupportedEncodingException {
        // Processar a requisição aqui (persistir, validar, etc)
        //System.out.println("Processing request for patient: " + requestDTO.getPatient().getName());
        examsRepository.processExamRequest(requestDTO);
    }
}
