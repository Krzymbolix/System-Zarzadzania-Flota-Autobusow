package com.example.bada;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KierowcyDAO {
    private final JdbcTemplate jdbcTemplate;

    public KierowcyDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Kierowcy> list() {
        String sql = "SELECT * FROM kierowcy ORDER BY nr_pracownika";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Kierowcy.class));
    }

    public void save(Kierowcy kierowca) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("kierowcy").usingColumns(
                "nr_pracownika", "kat_prawa_jazdy", "data_badan"
        );
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(kierowca);
        insertActor.execute(param);
    }

    public void update(Kierowcy kierowca) {
        String sql = "UPDATE kierowcy SET kat_prawa_jazdy = :kat_prawa_jazdy, " +
                "data_badan = :data_badan " +
                "WHERE nr_pracownika = :nr_pracownika";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(kierowca);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int nr_pracownika) {
        String sql = "DELETE FROM kierowcy WHERE nr_pracownika = ?";
        jdbcTemplate.update(sql, nr_pracownika);
    }

    public Kierowcy get(int nr_pracownika) {
        String sql = "SELECT * FROM kierowcy WHERE nr_pracownika = ?";
        List<Kierowcy> lista = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Kierowcy.class),
                nr_pracownika
        );
        return lista.isEmpty() ? null : lista.get(0);
    }

    // Sprawdź czy pracownik jest kierowcą
    public boolean isKierowca(int nr_pracownika) {
        String sql = "SELECT COUNT(*) FROM kierowcy WHERE nr_pracownika = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nr_pracownika);
        return count != null && count > 0;
    }
}
