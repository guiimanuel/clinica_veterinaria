package br.ifpe.clinicaveterinaria.DAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import br.ifpe.clinicaveterinaria.model.Cliente;


@Repository
public class ClienteDAO {
    private final JdbcClient jdbcClient;

    public ClienteDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

public Cliente create(Cliente cliente) {
    String sql = """
        INSERT INTO cliente (idPessoa, nome, telefone, animais)
        VALUES (:idPessoa, :nome, :telefone, :animais)
    """;

    jdbcClient.sql(sql)
            .param("idPessoa", cliente.getIdPessoa()).param("nome", cliente.getNome()).param("telefone", cliente.getTelefone()).param("animais", cliente.getAnimais()).update();
    Long idPessoa = jdbcClient.sql("SELECT LAST_INSERT_ID()").query(Long.class).single();

    cliente.setIdPessoa(idPessoa.intValue());
    return cliente;
}
    public Optional<Cliente> findById(Long idPessoa) {
        return jdbcClient.sql("SELECT * FROM cliente WHERE idPessoa = :idPessoa").param("idPessoa", idPessoa).query(Cliente.class).optional();
    }

public List<Cliente> findAll() {
        return jdbcClient.sql("SELECT * FROM cliente").query(Cliente.class).list();
    }

    public List<Cliente> findByFilters(
            String nome
    ) {
        StringBuilder sql = new StringBuilder("SELECT * FROM Cliente WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();

        if (nome != null && !nome.isBlank()) {
            sql.append(" AND nome LIKE :nome ");
            params.put("nome", "%" + nome + "%");
        }

        return jdbcClient.sql(sql.toString()).params(params).query(Cliente.class).list();
    }

    public int update(Long idPessoa, Cliente cliente) {
        String sql = """
            UPDATE cliente
            SET nome = :nome, telefone = :telefone, animais = :animais
            WHERE idPessoa = :idPessoa
        """;

        return jdbcClient.sql(sql).param("nome", cliente.getNome()).param("telefone", cliente.getTelefone()).param("animais", cliente.getAnimais()).param("idPessoa", idPessoa).update();
    }

    public int delete(Long idPessoa) {
        return jdbcClient.sql("DELETE FROM cliente WHERE idPessoa = :idPessoa").param("idPessoa", idPessoa).update();
    }

}