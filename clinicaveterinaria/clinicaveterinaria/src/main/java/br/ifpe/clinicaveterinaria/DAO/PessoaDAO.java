package br.ifpe.clinicaveterinaria.DAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import br.ifpe.clinicaveterinaria.model.Pessoa;


@Repository
public class PessoaDAO {

    private final JdbcClient jdbcClient;

public PessoaDAO(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
}

public Pessoa create(Pessoa pessoa) {

    String sql = """
        INSERT INTO pessoa (idPessoa, nome, telefone)
        VALUES (:idPessoa, :nome, :telefone)
    """;

    jdbcClient.sql(sql)
            .param("idPessoa", pessoa.getIdPessoa()).param("nome", pessoa.getNome()).param("telefone", pessoa.getTelefone()).update();
    Long id = jdbcClient.sql("SELECT LAST_INSERT_ID()").query(Long.class).single();

    pessoa.setIdPessoa(id.intValue());
    return pessoa;
}
    public Optional<Pessoa> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM pessoa WHERE idPessoa = :idPessoa").param("idPessoa", id).query(Pessoa.class).optional();
    }

public List<Pessoa> findAll() {
        return jdbcClient.sql("SELECT * FROM pessoa").query(Pessoa.class).list();
    }

    public List<Pessoa> findByFilters(
            String nome
    ) {
        StringBuilder sql = new StringBuilder("SELECT * FROM veterinario WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();

        if (nome != null && !nome.isBlank()) {
            sql.append(" AND nome LIKE :nome ");
            params.put("nome", "%" + nome + "%");
        }

        return jdbcClient.sql(sql.toString()).params(params).query(Pessoa.class).list();
    }

    public int update(Long id, Pessoa pessoa) {
        String sql = """
            UPDATE pessoa SET nome = :nome, telefone = :telefone
            WHERE idPessoa = :idPessoa
        """;

        return jdbcClient.sql(sql).param("nome", pessoa.getNome()).param("telefone", pessoa.getTelefone()).param("idPessoa", id).update();
    }

    public int delete(Long id) {
        return jdbcClient.sql("DELETE FROM pessoa WHERE idPessoa = :idPessoa").param("idPessoa", id).update();
    }

}