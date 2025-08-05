package com.NeomedTasyApi.service;

import com.NeomedTasyApi.dto.ExamesRequestDTO;
import com.NeomedTasyApi.repository.ExamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamesService {
    @Autowired
    private ExamsRepository examsRepository;

    public void processExamRequest(ExamesRequestDTO requestDTO) {
        examsRepository.processExamRequest(requestDTO);
    }
}
