package com.example.bada;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KursyDAO {
    private final JdbcTemplate jdbcTemplate;

    public KursyDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Kursy> list() {
        String sql = "SELECT * FROM kursy";
        List<Kursy> listKursy = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Kursy.class));
        return listKursy;
    }

    public void save(Kursy kursy) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("kursy").usingColumns("nr_kursu", "nr_pracownika", "nr_linii", "nr_taborowy", "godzina_odjazdu");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(kursy);
        insertActor.execute(param);
    }

    public Kursy get(int id) {
        return null;
    }

    public void update(Kursy kursy) {
        String sql = "UPDATE kursy SET nr_pracownika = :nr_pracownika, " +
                "nr_linii = :nr_linii, nr_taborowy = :nr_taborowy, " +
                "godzina_odjazdu = :godzina_odjazdu " +
                "WHERE nr_kursu = :nr_kursu";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(kursy);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int id) {
        String sql = "DELETE FROM kursy WHERE nr_kursu= ?";
        jdbcTemplate.update(sql, id);
    }

}
