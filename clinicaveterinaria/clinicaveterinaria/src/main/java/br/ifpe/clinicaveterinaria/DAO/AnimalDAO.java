package br.ifpe.clinicaveterinaria.DAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import br.ifpe.clinicaveterinaria.model.Animal;


@Repository
public class AnimalDAO {

    private final JdbcClient jdbcClient;

    public AnimalDAO(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

public Animal create(Animal animal) {
    String sql = """
        INSERT INTO animal (idAnimal, nome, especie, idade, dono)
        VALUES (:idAnimal, :nome, :especie, :idade, :dono)
    """;

    jdbcClient.sql(sql)
            .param("idAnimal", animal.getIdAnimal()).param("nome", animal.getNome()).param("especie", animal.getEspecie()).param("idade", animal.getIdade()).param("dono", animal.getDono()).update();

    Long idAnimal = jdbcClient.sql("SELECT LAST_INSERT_ID()").query(Long.class).single();

    animal.setIdAnimal(idAnimal.intValue());
    return animal;
}
    public Optional<Animal> findById(Long idAnimal) {
        return jdbcClient.sql("SELECT * FROM animal WHERE idAnimal = :idAnimal").param("idAnimal", idAnimal).query(Animal.class).optional();
    }

public List<Animal> findAll() {
        return jdbcClient.sql("SELECT * FROM animal").query(Animal.class).list();
    }

    public List<Animal> findByFilters(
            String especie
    ) {
        StringBuilder sql = new StringBuilder("SELECT * FROM animal WHERE 1=1 ");
        Map<String, Object> params = new HashMap<>();

        if (especie != null && !especie.isBlank()) {
            sql.append(" AND especie LIKE :especie ");
            params.put("especie", "%" + especie + "%");
        }

        return jdbcClient.sql(sql.toString()).params(params).query(Animal.class).list();
    }

    public int update(Long idAnimal, Animal animal) {
        String sql = """
            UPDATE animal
            SET nome = :nome, especie = :especie, idade = :idade, dono = :dono
            WHERE idAnimal = :idAnimal
        """;

        return jdbcClient.sql(sql).param("nome", animal.getNome()).param("especie", animal.getEspecie()).param("idade", animal.getIdade()).param("dono", animal.getDono()).param("idAnimal", idAnimal).update();
    }

    public int delete(Long idAnimal) {
        return jdbcClient.sql("DELETE FROM animal WHERE idAnimal = :idAnimal").param("idAnimal", idAnimal).update();
    }

}