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
        PrescricaoDTO prescricaoDTO;

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
                    AND a.nr_seq_interno = ?
                """;


        prescricaoDTO = jdbcTemplate.queryForObject(sql, new Object[]{requestDTO.getExam().getCode()}, (rs, rowNum) -> new PrescricaoDTO(
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
        final long nrSeqLaudo = this.obterSeExisteLaudoAnterior(requestDTO);

        long nrSequenciaLaudoPaciente = jdbcTemplate.queryForObject(
                "select tasy.laudo_paciente_seq.nextval from dual", Integer.class);

        laudoPacienteDTO.setNrSequencia(nrSequenciaLaudoPaciente);
        laudoPacienteDTO.setDsLaudo(this.converterLaudoBase64toText(requestDTO));
        laudoPacienteDTO.setNrAtendimento(prescricaoDTO.getNrAtendimento());
        laudoPacienteDTO.setNrControle(Long.parseLong(requestDTO.getExam().getCode()));
        laudoPacienteDTO.setDtEntradaUnidade(dataAtual);
        laudoPacienteDTO.setNrLaudo(nrSeqLaudo);
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

    private long obterSeExisteLaudoAnterior(ExamesRequestDTO requestDTO) {

        long nrLaudo = jdbcTemplate.queryForObject(
                "select nvl(max(nr_laudo),0) " +
                        "from tasy.laudo_paciente " +
                        "where nr_controle = ? " +
                        "and dt_cancelamento is null ", new Object[]{requestDTO.getExam().getCode()}, Integer.class);

        if (nrLaudo > 0) {
            this.inativarLaudoAtual(requestDTO.getExam().getCode());
        }
        /*Sempre enviará o laudo atual + 1, caso não exista, a variável nrLaudo será 0, então acresce 1, para que o primeiro laudo seja o 1*/
        return nrLaudo +1;

    }

    private void inativarLaudoAtual(String code) {
        String sql = """
                UPDATE  TASY.LAUDO_PACIENTE 
                SET     DT_CANCELAMENTO = SYSDATE,
                        NM_USUARIO_CANCEL = 'TasyApiNeomed',
                        DT_ATUALIZACAO = SYSDATE,
                        NM_USUARIO = 'TasyApiNeomed'
                WHERE   NR_CONTROLE = ?
                AND     DT_CANCELAMENTO IS NULL
                """;
        jdbcTemplate.update(sql,Long.parseLong(code));
    }

    public void processExamRequest(ExamesRequestDTO requestDTO) throws UnsupportedEncodingException {
        LaudoPacienteDTO laudoPacienteDTO = this.obterDadosLaudo(requestDTO);

        String sql = """
            INSERT INTO TASY.LAUDO_PACIENTE (
                NR_SEQUENCIA, NR_CONTROLE,NR_ATENDIMENTO, DT_ENTRADA_UNIDADE, NR_LAUDO, NM_USUARIO,
                DT_ATUALIZACAO, CD_MEDICO_RESP, DS_TITULO_LAUDO, DT_LAUDO, IE_NORMAL,
                DT_EXAME, NR_PRESCRICAO, DS_LAUDO, DT_APROVACAO, NM_USUARIO_APROVACAO,
                NR_SEQ_PROC, NR_SEQ_PRESCRICAO, DT_LIBERACAO, DT_PREV_ENTREGA, QT_IMAGEM,
                DT_FIM_DIGITACAO, NM_USUARIO_DIGITACAO, DT_INICIO_DIGITACAO, CD_SETOR_USUARIO,
                NM_USUARIO_LIBERACAO, QT_CARACTERES, CD_PESSOA_FISICA, IE_STATUS_LAUDO,
                IE_CD_LAUDO, IE_TUMOR, IE_FORMATO_TEXTO, IE_GERAR_COMUNIC, IE_EXIGE_SEG_APROV,
                IE_URGENTE, DS_UTC, DS_UTC_ATUALIZACAO
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        jdbcTemplate.update(sql,
                laudoPacienteDTO.getNrSequencia(),
                laudoPacienteDTO.getNrControle(),
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