package com.example.bada;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BazyDAO {
    private final JdbcTemplate jdbcTemplate;

    public BazyDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Bazy> list() {
        String sql = "SELECT * FROM bazy ORDER BY nr_bazy";
        List<Bazy> listBazy = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Bazy.class));
        return listBazy;
    }

    public void save(Bazy bazy) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("bazy").usingColumns("nr_bazy","nazwa_ulicy","nr_porzadkowy_ulicy","kod_pocztowy");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(bazy);
        insertActor.execute(param);
    }

    public Bazy get(int id) {
        String sql = "SELECT * FROM bazy WHERE nr_bazy = ?";
        List<Bazy> result = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Bazy.class), id);
        return result.isEmpty() ? null : result.get(0);
    }

    public void update(Bazy bazy) {
        String sql = "UPDATE bazy SET nazwa_ulicy = :nazwa_ulicy, " +
                "nr_porzadkowy_ulicy = :nr_porzadkowy_ulicy, " +
                "kod_pocztowy = :kod_pocztowy " +
                "WHERE nr_bazy = :nr_bazy";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(bazy);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int id) {
        String sql = "DELETE FROM bazy WHERE nr_bazy = ?";
        jdbcTemplate.update(sql, id);
    }

    // Sprawdź czy numer bazy już istnieje
    public boolean exists(int nr_bazy) {
        String sql = "SELECT COUNT(*) FROM bazy WHERE nr_bazy = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nr_bazy);
        return count != null && count > 0;
    }

    // Sprawdź czy baza jest używana w innych tabelach
    public boolean isUsed(int nr_bazy) {
        // Sprawdź czy są przypisane pracownicy
        String sqlPracownicy = "SELECT COUNT(*) FROM pracownicy WHERE nr_bazy = ?";
        Integer countPracownicy = jdbcTemplate.queryForObject(sqlPracownicy, Integer.class, nr_bazy);

        // Sprawdź czy są przypisane autobusy
        String sqlAutobusy = "SELECT COUNT(*) FROM autobusy WHERE nr_bazy = ?";
        Integer countAutobusy = jdbcTemplate.queryForObject(sqlAutobusy, Integer.class, nr_bazy);

        // Sprawdź czy są przypisane linie
        String sqlLinie = "SELECT COUNT(*) FROM linie WHERE nr_bazy = ?";
        Integer countLinie = jdbcTemplate.queryForObject(sqlLinie, Integer.class, nr_bazy);

        return (countPracownicy != null && countPracownicy > 0) ||
                (countAutobusy != null && countAutobusy > 0) ||
                (countLinie != null && countLinie > 0);
    }
}