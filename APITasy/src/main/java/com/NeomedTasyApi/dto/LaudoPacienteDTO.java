package com.NeomedTasyApi.dto;

import java.util.Date;
import lombok.Data;

@Data
public class LaudoPacienteDTO {
    private Long nrSequencia;
    private Long nrAtendimento;
    private long nrControle;
    private Date dtEntradaUnidade;
    private Long nrLaudo;
    private String nmUsuario;
    private Date dtAtualizacao;
    private String cdMedicoResp;
    private String dsTituloLaudo;
    private Date dtLaudo;
    private String ieNormal;
    private Date dtExame;
    private Long nrPrescricao;
    private String dsLaudo;
    private Date dtAprovacao;
    private String nmUsuarioAprovacao;
    private Long nrSeqProc;
    private Long nrSeqPrescricao;
    private Date dtLiberacao;
    private Date dtPrevEntrega;
    private Integer qtImagem;
    private Date dtFimDigitacao;
    private String nmUsuarioDigitacao;
    private Date dtInicioDigitacao;
    private Integer cdSetorUsuario;
    private String nmUsuarioLiberacao;
    private Long qtCaracteres;
    private String cdPessoaFisica;
    private String ieStatusLaudo;
    private String ieCdLaudo;
    private String ieTumor;
    private Integer ieFormatoTexto;
    private String ieGerarComunic;
    private String ieExigeSegAprov;
    private String ieUrgente;
    private String dsUtc;
    private String dsUtcAtualizacao;
}
