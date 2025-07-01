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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getBreathlessness_description() {
        return breathlessness_description;
    }

    public void setBreathlessness_description(String breathlessness_description) {
        this.breathlessness_description = breathlessness_description;
    }

    public int getBreathlessness_intensity() {
        return breathlessness_intensity;
    }

    public void setBreathlessness_intensity(int breathlessness_intensity) {
        this.breathlessness_intensity = breathlessness_intensity;
    }

    public String getChest_pain_description() {
        return chest_pain_description;
    }

    public void setChest_pain_description(String chest_pain_description) {
        this.chest_pain_description = chest_pain_description;
    }

    public int getChest_pain_intensity() {
        return chest_pain_intensity;
    }

    public void setChest_pain_intensity(int chest_pain_intensity) {
        this.chest_pain_intensity = chest_pain_intensity;
    }

    public String getRequest_reason() {
        return request_reason;
    }

    public void setRequest_reason(String request_reason) {
        this.request_reason = request_reason;
    }

    public String getOther_informations_or_results() {
        return other_informations_or_results;
    }

    public void setOther_informations_or_results(String other_informations_or_results) {
        this.other_informations_or_results = other_informations_or_results;
    }

    public SourceFileDTO getSource_file() {
        return source_file;
    }

    public void setSource_file(SourceFileDTO source_file) {
        this.source_file = source_file;
    }

    public boolean isCritical_exam() {
        return critical_exam;
    }

    public void setCritical_exam(boolean critical_exam) {
        this.critical_exam = critical_exam;
    }

    public String getReason_for_pendency() {
        return reason_for_pendency;
    }

    public void setReason_for_pendency(String reason_for_pendency) {
        this.reason_for_pendency = reason_for_pendency;
    }

    public RedoTemplateDTO getRedo_template() {
        return redo_template;
    }

    public void setRedo_template(RedoTemplateDTO redo_template) {
        this.redo_template = redo_template;
    }

    public ExamTypeDTO getExam_type() {
        return exam_type;
    }

    public void setExam_type(ExamTypeDTO exam_type) {
        this.exam_type = exam_type;
    }

    public UnitDTO getUnit() {
        return unit;
    }

    public void setUnit(UnitDTO unit) {
        this.unit = unit;
    }

    private ExamTypeDTO exam_type;
    private UnitDTO unit;
}