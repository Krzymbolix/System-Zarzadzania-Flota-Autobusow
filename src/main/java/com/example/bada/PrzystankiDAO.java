package com.example.bada;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class PrzystankiDAO {
    private final JdbcTemplate jdbcTemplate;

    public PrzystankiDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Przystanki> list() {
        String sql = "SELECT * FROM przystanki";
        List<Przystanki> listPrzystanki = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Przystanki.class));
        return listPrzystanki;
    }

    public void save(Przystanki przystanki) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("przystanki").usingColumns("nr_przystanku", "nazwa", "nr_porzadkowy", "czy_wiata");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(przystanki);
        insertActor.execute(param);
    }

    public Przystanki get(int id) {
        return null;
    }

    public void update(Przystanki przystanki) {
        String sql = "UPDATE przystanki SET nazwa = :nazwa, " +
                "nr_porzadkowy = :nr_porzadkowy, czy_wiata = :czy_wiata " +
                "WHERE nr_przystanku = :nr_przystanku";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(przystanki);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int id) {
        String sql = "DELETE FROM przystanki WHERE nr_przystanku = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Przystanki> findByName(String name) {
        String sql = "SELECT * FROM przystanki WHERE Lower(nazwa) LIKE LOWER(?) ORDER BY nazwa";
        List<Przystanki> lista = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Przystanki.class),
                name + "%"
        );
        if (lista.isEmpty()) {
            return Collections.emptyList();
        }
        return lista;
    }

    public Przystanki findByID(int id) {
        String sql = "SELECT * FROM przystanki WHERE nr_przystanku = ?";
        List<Przystanki> lista = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Przystanki.class),
                id
        );
        Przystanki przystanek = lista.get(0);
        return przystanek;
    }

}
