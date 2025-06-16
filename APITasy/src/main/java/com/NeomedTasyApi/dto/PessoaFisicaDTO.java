package com.NeomedTasyApi.dto;

public class PessoaFisicaDTO {
    private String cdPessoaFisica;
    private String nmPessoaFisica;

    public PessoaFisicaDTO() {}

    public PessoaFisicaDTO(String cdPessoaFisica, String nmPessoaFisica) {
        this.cdPessoaFisica = cdPessoaFisica;
        this.nmPessoaFisica = nmPessoaFisica;
    }

    // Getters e Setters
    public String getCdPessoaFisica() {
        return cdPessoaFisica;
    }

    public void setCdPessoaFisica(String cdPessoaFisica) {
        this.cdPessoaFisica = cdPessoaFisica;
    }

    public String getNmPessoaFisica() {
        return nmPessoaFisica;
    }

    public void setNmPessoaFisica(String nmPessoaFisica) {
        this.nmPessoaFisica = nmPessoaFisica;
    }
}