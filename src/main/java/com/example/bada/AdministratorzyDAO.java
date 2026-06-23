package com.example.bada;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdministratorzyDAO {
    private final JdbcTemplate jdbcTemplate;

    public AdministratorzyDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Administratorzy> list() {
        String sql = "SELECT * FROM administratorzy";
        List<Administratorzy> listAdministratorzy = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Administratorzy.class));
        return listAdministratorzy;
    }

    public void save(Administratorzy administratorzy) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("administratorzy").usingColumns("nr_pracownika");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(administratorzy);
        insertActor.execute(param);
    }

    public Administratorzy get(int id) {
        return null;
    }

    public void update(Administratorzy administratorzy) {
    }

    public void delete(int id) {
        String sql = "DELETE FROM administratorzy WHERE nr_pracownika = ?";
        jdbcTemplate.update(sql, id);
    }

    public Administratorzy findByNr(int nr) {
        String sql = "SELECT * FROM administratorzy WHERE nr_pracownika = ?";
        List<Administratorzy> lista = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Administratorzy.class),
                nr
        );
        if (lista.isEmpty()) {
            return null; // lub rzucić UsernameNotFoundException w CustomUserDetailsService
        }
        return lista.get(0); // tylko pierwszy wynik
    }

}
