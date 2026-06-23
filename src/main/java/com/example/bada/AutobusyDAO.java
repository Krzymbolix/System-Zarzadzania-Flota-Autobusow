package com.example.bada;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AutobusyDAO {
    private final JdbcTemplate jdbcTemplate;

    public AutobusyDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Autobusy> list() {
        String sql = "SELECT * FROM autobusy ORDER BY nr_taborowy";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Autobusy.class));
    }

    public void save(Autobusy autobus) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("autobusy").usingColumns(
                "nr_taborowy", "nr_rejestracyjny", "nr_bazy", "pojemnosc", "czy_przegubowy"
        );
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(autobus);
        insertActor.execute(param);
    }

    public void update(Autobusy autobus) {
        String sql = "UPDATE autobusy SET nr_rejestracyjny = :nr_rejestracyjny, " +
                "nr_bazy = :nr_bazy, pojemnosc = :pojemnosc, " +
                "czy_przegubowy = :czy_przegubowy " +
                "WHERE nr_taborowy = :nr_taborowy";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(autobus);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int nr_taborowy) {
        String sql = "DELETE FROM autobusy WHERE nr_taborowy = ?";
        jdbcTemplate.update(sql, nr_taborowy);
    }

    public Autobusy get(int nr_taborowy) {
        String sql = "SELECT * FROM autobusy WHERE nr_taborowy = ?";
        List<Autobusy> lista = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Autobusy.class),
                nr_taborowy
        );
        return lista.isEmpty() ? null : lista.get(0);
    }

    // Dodatkowa metoda dla dropdown w formularzach
    public List<Autobusy> findByBaza(int nr_bazy) {
        String sql = "SELECT * FROM autobusy WHERE nr_bazy = ? ORDER BY nr_taborowy";
        return jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Autobusy.class),
                nr_bazy
        );
    }
}
