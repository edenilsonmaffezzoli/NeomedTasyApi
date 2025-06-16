package com.NeomedTasyApi.dto;

import java.util.List;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
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

    public TemplateDTO getTemplate() {
        return template;
    }

    public SubTemplateDTO getSubtemplate() {
        return subtemplate;
    }

    public PhysicianDTO getPhysician() {
        return physician;
    }

    public OperatorDTO getOperator() {
        return operator;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public ExamDTO getExam() {
        return exam;
    }

    public MedicalReportDTO getMedical_report() {
        return medical_report;
    }

    public List<Object> getMedical_reports_history() {
        return medical_reports_history;
    }

    public List<StateableDTO> getStateable() {
        return stateable;
    }
}



