package com.NeomedTasyApi.dto;

import lombok.Data;

@Data
public class ExamDTO {
    private String code;
    private String created_at;
    private String breathlessness_description;
    private int breathlessness_intensity;
    private String chest_pain_description;
    private int chest_pain_intensity;
    private String request_reason;
    private String other_informations_or_results;
    private SourceFileDTO source_file;
    private boolean critical_exam;
    private String reason_for_pendency;
    private RedoTemplateDTO redo_template;
    private ExamTypeDTO exam_type;
    private UnitDTO unit;



}