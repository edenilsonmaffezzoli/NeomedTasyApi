package com.NeomedTasyApi.dto;

import lombok.Data;
import java.util.List;

@Data
public class PatientDTO {
    private String id;
    private String name;
    private String birth_dt;
    private String gender;
    private String integration_key;
    private boolean pacemaker;
    private String cardiologist_follow_up;
    private String height;
    private String weight;
    private List<Object> patient_historicals;
}