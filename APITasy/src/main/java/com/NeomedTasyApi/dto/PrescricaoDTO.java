package com.NeomedTasyApi.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrescricaoDTO {
    private String cdMedico;
    private Long nrPrescricao;
    private Long nrSequencia;
    private int cdSetorAtendimento;
    private String cdPessoaFisica;
    private Long nrAtendimento;
}