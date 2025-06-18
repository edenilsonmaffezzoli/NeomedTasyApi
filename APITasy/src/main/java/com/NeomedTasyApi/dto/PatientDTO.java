package com.NeomedTasyApi.dto;

import lombok.Data;

import java.util.List;

@Data
public class PatientDTO {
    private String id;
    private String name;
    private String birth_dt;
    private String gender;
    private int integration_key;
    private boolean pacemaker;
    private String cardiologist_follow_up;
    private String height;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth_dt() {
        return birth_dt;
    }

    public void setBirth_dt(String birth_dt) {
        this.birth_dt = birth_dt;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getIntegration_key() {
        return integration_key;
    }

    public void setIntegration_key(int integration_key) {
        this.integration_key = integration_key;
    }

    public boolean isPacemaker() {
        return pacemaker;
    }

    public void setPacemaker(boolean pacemaker) {
        this.pacemaker = pacemaker;
    }

    public String getCardiologist_follow_up() {
        return cardiologist_follow_up;
    }

    public void setCardiologist_follow_up(String cardiologist_follow_up) {
        this.cardiologist_follow_up = cardiologist_follow_up;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public List<Object> getPatient_historicals() {
        return patient_historicals;
    }

    public void setPatient_historicals(List<Object> patient_historicals) {
        this.patient_historicals = patient_historicals;
    }

    private String weight;
    private List<Object> patient_historicals;
}