package br.ifpe.clinicaveterinaria.DAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import br.ifpe.clinicaveterinaria.model.Consulta;


@Repository
public class ConsultaDAO {
    private final JdbcClient jdbcClient;

    public ConsultaDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

public Consulta create(Consulta consulta) {
    String sql = """
        INSERT INTO consulta (idConsulta, data, diagnostico, tratamento, animal, veterinario)
        VALUES (:idConsulta, :data, :diagnostico, :tratamento, :animal, :veterinario)
    """;

    jdbcClient.sql(sql)
            .param("idConsulta", consulta.getIdConsulta()).param("data", consulta.getData()).param("diagnostico", consulta.getDiagnostico()).param("tratamento", consulta.getTratamento()).param("animal", consulta.getAnimal()).param("veterinario", consulta.getVeterinario()).update();
    Long idConsulta = jdbcClient.sql("SELECT LAST_INSERT_ID()").query(Long.class).single();

    consulta.setIdConsulta(idConsulta.intValue());
    return consulta;
}
    public Optional<Consulta> findById(Long idConsulta) {
        return jdbcClient.sql("SELECT * FROM consulta WHERE idConsulta = :idConsulta").param("idConsulta", idConsulta).query(Consulta.class).optional();
    }

public List<Consulta> findAll() {
        return jdbcClient.sql("SELECT * FROM consulta").query(Consulta.class).list();
    }

    public List<Consulta> findByFilters(
            String nome
    ) {
        StringBuilder sql = new StringBuilder("SELECT * FROM Cliente WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();

        if (nome != null && !nome.isBlank()) {
            sql.append(" AND nome LIKE :nome ");
            params.put("nome", "%" + nome + "%");
        }

        return jdbcClient.sql(sql.toString()).params(params).query(Consulta.class).list();
    }

    public int update(Long idConsulta, Consulta consulta) {
        String sql = """
            UPDATE consulta
            SET data = :data, diagnostico = :diagnostico, tratamento = :tratamento, animal = :animal, veterinario = :veterinario
            WHERE idConsulta = :idConsulta
        """;

        return jdbcClient.sql(sql).param("data", consulta.getData()).param("diagnostico", consulta.getDiagnostico()).param("tratamento", consulta.getTratamento()).param("animal", consulta.getAnimal()).param("veterinario", consulta.getVeterinario()).param("idConsulta", idConsulta).update();
    }

    public int delete(Long idConsulta) {
        return jdbcClient.sql("DELETE FROM consulta WHERE idConsulta = :idConsulta").param("idConsulta", idConsulta).update();
    }

}