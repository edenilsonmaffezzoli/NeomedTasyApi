package com.NeomedTasyApi.repository;

import com.NeomedTasyApi.dto.ExamesRequestDTO;
import com.NeomedTasyApi.dto.LaudoPacienteDTO;
import com.NeomedTasyApi.dto.PrescricaoDTO;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.sql.Date;
import java.sql.Timestamp;



@Repository
public class ExamsRepository {

    private final JdbcTemplate jdbcTemplate;

    public ExamsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public PrescricaoDTO obterDadosPrescricao(ExamesRequestDTO requestDTO) {
        PrescricaoDTO prescricaoDTO = new PrescricaoDTO();

        String sql = """
                    SELECT b.CD_MEDICO as CD_MEDICO,
                           b.NR_PRESCRICAO as NR_PRESCRICAO,
                           a.NR_SEQUENCIA as NR_SEQUENCIA,
                           b.CD_SETOR_ATENDIMENTO as CD_SETOR_ATENDIMENTO,
                           b.CD_PESSOA_FISICA as CD_PESSOA_FISICA,
                           b.NR_ATENDIMENTO as NR_ATENDIMENTO
                    FROM tasy.prescr_procedimento a,
                         tasy.prescr_medica b
                    WHERE a.nr_prescricao = b.nr_prescricao
                    AND a.nr_seq_interno = 20360320
                """;


        prescricaoDTO = jdbcTemplate.queryForObject(sql, /*new Object[]{requestDTO.getPatient().getIntegration_key()},*/ (rs, rowNum) -> new PrescricaoDTO(
                rs.getString("CD_MEDICO"),
                rs.getLong("NR_PRESCRICAO"),
                rs.getLong("NR_SEQUENCIA"),
                rs.getInt("CD_SETOR_ATENDIMENTO"),
                rs.getString("CD_PESSOA_FISICA"),
                rs.getLong("NR_ATENDIMENTO")
        ));

        return prescricaoDTO;

    }

    public LaudoPacienteDTO obterDadosLaudo(ExamesRequestDTO requestDTO) throws UnsupportedEncodingException {
        LaudoPacienteDTO laudoPacienteDTO = new LaudoPacienteDTO();
        final Date dataAtual = new Date(System.currentTimeMillis());
        final String nmUsuarioPadrao = "TasyApiNeomed";
        final PrescricaoDTO prescricaoDTO = this.obterDadosPrescricao(requestDTO);

        long nrSequenciaLaudoPaciente = jdbcTemplate.queryForObject(
                "select tasy.laudo_paciente_seq.nextval from dual", Integer.class);

        laudoPacienteDTO.setNrSequencia(nrSequenciaLaudoPaciente);
        laudoPacienteDTO.setDsLaudo(this.converterLaudoBase64toText(requestDTO));
        laudoPacienteDTO.setNrAtendimento(null);
        laudoPacienteDTO.setDtEntradaUnidade(dataAtual);
        laudoPacienteDTO.setNrLaudo((long) 1);
        laudoPacienteDTO.setNmUsuario(nmUsuarioPadrao);
        laudoPacienteDTO.setDtAtualizacao(dataAtual);
        laudoPacienteDTO.setCdMedicoResp(prescricaoDTO.getCdMedico());
        laudoPacienteDTO.setDsTituloLaudo(requestDTO.getTemplate().getTitle());
        laudoPacienteDTO.setDtLaudo(dataAtual);
        laudoPacienteDTO.setIeNormal("N");
        laudoPacienteDTO.setDtExame(dataAtual);
        laudoPacienteDTO.setNrPrescricao(prescricaoDTO.getNrPrescricao());
        laudoPacienteDTO.setDtAprovacao(dataAtual);
        laudoPacienteDTO.setNmUsuarioAprovacao(nmUsuarioPadrao);
        laudoPacienteDTO.setNrSeqProc(null);
        laudoPacienteDTO.setNrSeqPrescricao(laudoPacienteDTO.getNrSeqPrescricao());
        laudoPacienteDTO.setDtLiberacao(dataAtual);
        laudoPacienteDTO.setDtPrevEntrega(dataAtual);
        laudoPacienteDTO.setQtImagem(1);
        laudoPacienteDTO.setDtFimDigitacao(dataAtual);
        laudoPacienteDTO.setNmUsuarioDigitacao(nmUsuarioPadrao);
        laudoPacienteDTO.setDtInicioDigitacao(dataAtual);
        laudoPacienteDTO.setCdSetorUsuario(prescricaoDTO.getCdSetorAtendimento());
        laudoPacienteDTO.setNmUsuarioLiberacao(nmUsuarioPadrao);
        laudoPacienteDTO.setQtCaracteres((long) laudoPacienteDTO.getDsLaudo().length());
        laudoPacienteDTO.setCdPessoaFisica(prescricaoDTO.getCdPessoaFisica());
        laudoPacienteDTO.setIeStatusLaudo("LL");
        laudoPacienteDTO.setIeCdLaudo("N");
        laudoPacienteDTO.setIeTumor("N");
        laudoPacienteDTO.setIeFormatoTexto(1);
        laudoPacienteDTO.setIeGerarComunic("N");
        laudoPacienteDTO.setIeExigeSegAprov("N");
        laudoPacienteDTO.setIeUrgente("N");
        laudoPacienteDTO.setDsUtc(dataAtual.toString());
        laudoPacienteDTO.setDsUtcAtualizacao(dataAtual.toString());


        return laudoPacienteDTO;
    }

    public void processExamRequest(ExamesRequestDTO requestDTO, LaudoPacienteDTO laudoPacienteDTO) throws UnsupportedEncodingException {


        String sql = """
            INSERT INTO TASY.LAUDO_PACIENTE (
                NR_SEQUENCIA, NR_ATENDIMENTO, DT_ENTRADA_UNIDADE, NR_LAUDO, NM_USUARIO,
                DT_ATUALIZACAO, CD_MEDICO_RESP, DS_TITULO_LAUDO, DT_LAUDO, IE_NORMAL,
                DT_EXAME, NR_PRESCRICAO, DS_LAUDO, DT_APROVACAO, NM_USUARIO_APROVACAO,
                NR_SEQ_PROC, NR_SEQ_PRESCRICAO, DT_LIBERACAO, DT_PREV_ENTREGA, QT_IMAGEM,
                DT_FIM_DIGITACAO, NM_USUARIO_DIGITACAO, DT_INICIO_DIGITACAO, CD_SETOR_USUARIO,
                NM_USUARIO_LIBERACAO, QT_CARACTERES, CD_PESSOA_FISICA, IE_STATUS_LAUDO,
                IE_CD_LAUDO, IE_TUMOR, IE_FORMATO_TEXTO, IE_GERAR_COMUNIC, IE_EXIGE_SEG_APROV,
                IE_URGENTE, DS_UTC, DS_UTC_ATUALIZACAO
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        jdbcTemplate.update(sql,
                laudoPacienteDTO.getNrSequencia(),
                laudoPacienteDTO.getNrAtendimento(),
                laudoPacienteDTO.getDtEntradaUnidade() != null ? new Timestamp(laudoPacienteDTO.getDtEntradaUnidade().getTime()) : null,
                laudoPacienteDTO.getNrLaudo(),
                laudoPacienteDTO.getNmUsuario(),
                laudoPacienteDTO.getDtAtualizacao() != null ? new Timestamp(laudoPacienteDTO.getDtAtualizacao().getTime()) : null,
                laudoPacienteDTO.getCdMedicoResp(),
                laudoPacienteDTO.getDsTituloLaudo(),
                laudoPacienteDTO.getDtLaudo() != null ? new Timestamp(laudoPacienteDTO.getDtLaudo().getTime()) : null,
                laudoPacienteDTO.getIeNormal(),
                laudoPacienteDTO.getDtExame() != null ? new Timestamp(laudoPacienteDTO.getDtExame().getTime()) : null,
                laudoPacienteDTO.getNrPrescricao(),
                laudoPacienteDTO.getDsLaudo(),
                laudoPacienteDTO.getDtAprovacao() != null ? new Timestamp(laudoPacienteDTO.getDtAprovacao().getTime()) : null,
                laudoPacienteDTO.getNmUsuarioAprovacao(),
                laudoPacienteDTO.getNrSeqProc(),
                laudoPacienteDTO.getNrSeqPrescricao(),
                laudoPacienteDTO.getDtLiberacao() != null ? new Timestamp(laudoPacienteDTO.getDtLiberacao().getTime()) : null,
                laudoPacienteDTO.getDtPrevEntrega() != null ? new Timestamp(laudoPacienteDTO.getDtPrevEntrega().getTime()) : null,
                laudoPacienteDTO.getQtImagem(),
                laudoPacienteDTO.getDtFimDigitacao() != null ? new Timestamp(laudoPacienteDTO.getDtFimDigitacao().getTime()) : null,
                laudoPacienteDTO.getNmUsuarioDigitacao(),
                laudoPacienteDTO.getDtInicioDigitacao() != null ? new Timestamp(laudoPacienteDTO.getDtInicioDigitacao().getTime()) : null,
                laudoPacienteDTO.getCdSetorUsuario(),
                laudoPacienteDTO.getNmUsuarioLiberacao(),
                laudoPacienteDTO.getQtCaracteres(),
                laudoPacienteDTO.getCdPessoaFisica(),
                laudoPacienteDTO.getIeStatusLaudo(),
                laudoPacienteDTO.getIeCdLaudo(),
                laudoPacienteDTO.getIeTumor(),
                laudoPacienteDTO.getIeFormatoTexto(),
                laudoPacienteDTO.getIeGerarComunic(),
                laudoPacienteDTO.getIeExigeSegAprov(),
                laudoPacienteDTO.getIeUrgente(),
                laudoPacienteDTO.getDsUtc(),
                laudoPacienteDTO.getDsUtcAtualizacao()
        );
    }

    private String converterLaudoBase64toText(ExamesRequestDTO requestDTO) throws UnsupportedEncodingException {
        String dslaudoBase64 = requestDTO.getMedical_report().getContent();
        byte[] decodedBytes = Base64.getDecoder().decode(dslaudoBase64);
        return new String(decodedBytes, "UTF-8");
    }
}