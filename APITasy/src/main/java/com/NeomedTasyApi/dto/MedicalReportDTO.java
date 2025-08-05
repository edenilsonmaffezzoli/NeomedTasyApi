package com.NeomedTasyApi.dto;

import lombok.Data;

@Data
public class MedicalReportDTO {

    private String id;
    private String file_base64;
    private String content;
    private boolean replace_result;

}