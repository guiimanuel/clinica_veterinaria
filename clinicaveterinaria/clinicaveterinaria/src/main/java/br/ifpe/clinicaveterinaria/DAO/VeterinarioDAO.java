    package br.ifpe.clinicaveterinaria.DAO;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.Optional;

    import org.springframework.jdbc.core.simple.JdbcClient;
    import org.springframework.stereotype.Repository;

    import br.ifpe.clinicaveterinaria.model.Veterinario;


    @Repository
    public class VeterinarioDAO {
    
        private final JdbcClient jdbcClient;
    
        public VeterinarioDAO(JdbcClient jdbcClient) {
            this.jdbcClient = jdbcClient;
        }
    
        public Veterinario create(Veterinario veterinario) {
    
            String sql = """
                INSERT INTO veterinario (idPessoa, nome, telefone, especialidade) 
                VALUES (:idPessoa, :nome, :telefone, :especialidade)
            """;
    
            jdbcClient.sql(sql)
                    .param("idPessoa", veterinario.getIdPessoa())
                    .param("nome", veterinario.getNome())
                    .param("telefone", veterinario.getTelefone())
                    .param("especialidade", veterinario.getEspecialidade()).update();
            Long id = jdbcClient.sql("SELECT LAST_INSERT_ID()").query(Long.class).single();
    
            veterinario.setIdPessoa(id.intValue());
            return veterinario;
        }
    
        public Optional<Veterinario> findById(Long id) {
            return jdbcClient.sql("SELECT * FROM veterinario WHERE idPessoa = :idPessoa").param("idPessoa", id).query(Veterinario.class).optional();
        }
    
        public List<Veterinario> findAll() {
            return jdbcClient.sql("SELECT * FROM veterinario").query(Veterinario.class).list();
        }
    
        public List<Veterinario> findByFilters(
                String especialidade
        ) {
            StringBuilder sql = new StringBuilder("SELECT * FROM veterinario WHERE 1=1 ");
            Map<String, Object> params = new HashMap<>();
    
            if (especialidade != null && !especialidade.isBlank()) {
                sql.append(" AND especialidade LIKE :especialidade ");
                params.put("especialidade", "%" + especialidade + "%");
            }
    
            return jdbcClient.sql(sql.toString()).params(params).query(Veterinario.class).list();
        }
    
        public int update(Long id, Veterinario veterinario) {
            String sql = """
                UPDATE veterinario
                SET nome = :nome, telefone = :telefone, especialidade = :especialidade
                WHERE idPessoa = :idPessoa
            """;
    
            return jdbcClient.sql(sql)
                    .param("nome", veterinario.getNome())
                    .param("telefone", veterinario.getTelefone())
                    .param("especialidade", veterinario.getEspecialidade())
                    .param("idPessoa", id)
                    .update();
        }
    
        public int delete(Long id) {
            return jdbcClient.sql("DELETE FROM veterinario WHERE idPessoa = :idPessoa").param("idPessoa", id).update();
        }
    
    }