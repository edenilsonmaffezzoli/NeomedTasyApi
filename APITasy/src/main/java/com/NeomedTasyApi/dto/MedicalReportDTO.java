package com.NeomedTasyApi.dto;

import lombok.Data;
import lombok.Getter;


@Data
@Getter
public class MedicalReportDTO {

    private String id;
    private String file_base64;
    private String content;
    private boolean replace_result;

    public String getId() {
        return id;
    }

    public String getFile_base64() {
        return file_base64;
    }

    public String getContent() {
        return content;
    }

    public boolean isReplace_result() {
        return replace_result;
    }


}