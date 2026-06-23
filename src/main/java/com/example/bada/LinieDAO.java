package com.example.bada;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LinieDAO {
    private final JdbcTemplate jdbcTemplate;

    public LinieDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Linie> list() {
        String sql = "SELECT * FROM linie";
        List<Linie> listLinie = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Linie.class));
        return listLinie;
    }

    public void save(Linie linie) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("linie").usingColumns("nr_linii", "rodzaj", "kierunek", "nr_bazy");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(linie);
        insertActor.execute(param);
    }

    public Linie get(int id) {
        return null;
    }

    public void update(Linie linie) {
        String sql = "UPDATE linie SET rodzaj = :rodzaj, " +
                "kierunek = :kierunek, nr_bazy = :nr_bazy " +
                "WHERE nr_linii = :nr_linii";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(linie);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(String nr_linii) {
        String sql = "DELETE FROM linie WHERE nr_linii = ?";
        jdbcTemplate.update(sql, nr_linii);
    }

    public List<Linie> findByNr(String nr) {
        String sql = "SELECT * FROM linie WHERE nr_linii = ?";
        List<Linie> lista = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Linie.class),
                nr
        );
        if (lista.isEmpty()) {
            return null; // lub rzucić UsernameNotFoundException w CustomUserDetailsService
        }
        return lista; // tylko pierwszy wynik
    }

}
