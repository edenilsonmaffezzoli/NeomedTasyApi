package com.NeomedTasyApi.dto;

public class PrescricaoDTO {
    private String cdMedico;
    private Long nrPrescricao;
    private Long nrSequencia;
    private int cdSetorAtendimento;
    private String cdPessoaFisica;
    private Long nrAtendimento;

    // Construtores
    public PrescricaoDTO() {
    }

    public PrescricaoDTO(String cdMedico, Long nrPrescricao, Long nrSequencia,
                         int cdSetorAtendimento, String cdPessoaFisica, Long nrAtendimento) {
        this.cdMedico = cdMedico;
        this.nrPrescricao = nrPrescricao;
        this.nrSequencia = nrSequencia;
        this.cdSetorAtendimento = cdSetorAtendimento;
        this.cdPessoaFisica = cdPessoaFisica;
        this.nrAtendimento = nrAtendimento;
    }

    // Getters e Setters
    public String getCdMedico() {
        return cdMedico;
    }

    public void setCdMedico(String cdMedico) {
        this.cdMedico = cdMedico;
    }

    public Long getNrPrescricao() {
        return nrPrescricao;
    }

    public void setNrPrescricao(Long nrPrescricao) {
        this.nrPrescricao = nrPrescricao;
    }

    public Long getNrSequencia() {
        return nrSequencia;
    }

    public void setNrSequencia(Long nrSequencia) {
        this.nrSequencia = nrSequencia;
    }

    public int getCdSetorAtendimento() {
        return cdSetorAtendimento;
    }

    public void setCdSetorAtendimento(int cdSetorAtendimento) {
        this.cdSetorAtendimento = cdSetorAtendimento;
    }

    public String getCdPessoaFisica() {
        return cdPessoaFisica;
    }

    public void setCdPessoaFisica(String cdPessoaFisica) {
        this.cdPessoaFisica = cdPessoaFisica;
    }

    public Long getNrAtendimento() {
        return nrAtendimento;
    }

    public void setNrAtendimento(Long nrAtendimento) {
        this.nrAtendimento = nrAtendimento;
    }
}