package com.NeomedTasyApi.dto;

import java.util.List;
import lombok.Data;

@Data
public class ExamesRequestDTO {
    private List<StateableDTO> stateable;
    private TemplateDTO template;
    private SubTemplateDTO subtemplate;
    private PhysicianDTO physician;
    private OperatorDTO operator;
    private PatientDTO patient;
    private ExamDTO exam;
    private MedicalReportDTO medical_report;
    private List<Object> medical_reports_history; // Assuming it's empty or unspecified

}



