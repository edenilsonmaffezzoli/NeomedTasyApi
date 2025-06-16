package com.NeomedTasyApi.repository;

import com.NeomedTasyApi.dto.ExamesRequestDTO;
import com.NeomedTasyApi.repository.ExamsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import java.util.Random;

@Repository
public class ExamsRepository {

    private final JdbcTemplate jdbcTemplate;

    public ExamsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void processExamRequest(ExamesRequestDTO requestDTO) throws UnsupportedEncodingException {
        Random random = new Random();
        String laudo = requestDTO.getMedical_report().getContent();


        byte[] decodedBytes = Base64.getDecoder().decode(laudo);
        String textoDecodificado = new String(decodedBytes, "UTF-8");
        int numero;
        // Implementação do método para processar a solicitação de exame
        // Aqui você pode adicionar a lógica para persistir os dados ou processar a solicitação
        // Por exemplo, integração com banco de dados ou outros serviços
        String pessoa  = jdbcTemplate.queryForObject(
                "select nm_pessoa_fisica from tasy.pessoa_fisica where cd_pessoa_fisica = 908885", String.class);
      System.out.println("Nome do pac: "+ textoDecodificado);

      String sql = "insert into tasy.laudo_paciente (NR_SEQUENCIA,DT_ENTRADA_UNIDADE,QT_IMAGEM,NM_USUARIO,DT_ATUALIZACAO,NR_LAUDO,ds_laudo)\n" +
              "values (6099341,sysdate,1,'TASY',sysdate,1,?)";
      jdbcTemplate.update(sql,textoDecodificado);
        /*pessoa  = jdbcTemplate.queryForObject(
                "select nm_pessoa_fisica from tasy.pessoa_fisica where cd_pessoa_fisica = 908885", String.class);
        System.out.println("Nome do pac: "+ pessoa);

        sql = "insert into edenilson (ds) values (?) ";
        for (int i = 1; i <= 20; i++) {
            numero = random.nextInt(100);
            jdbcTemplate.update(sql,"MARIA DA GRACA VALENTE NOVA " + String.valueOf(numero));
        }
*/

    }
}