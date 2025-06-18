package com.NeomedTasyApi.dto;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class TemplateDTO {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}