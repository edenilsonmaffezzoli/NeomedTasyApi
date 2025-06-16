package com.NeomedTasyApi.dto;

import lombok.Data;

@Data
public class PhysicianDTO {
    private String name;
    private CRMDTO crm;
    private String email;
    private String phone_number;
    private String rqe;
    private String cpf;
    private String rg;
}