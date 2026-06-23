package com.example.bada;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Linia_PrzystanekDAO {
    private final JdbcTemplate jdbcTemplate;

    public Linia_PrzystanekDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Linia_Przystanek> list() {
        String sql = "SELECT * FROM linia_przystanek";
        List<Linia_Przystanek> listLinia_Przystanek = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Linia_Przystanek.class));
        return listLinia_Przystanek;
    }

    public void save(Linia_Przystanek linia_Przystanek) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("linia_przystanek").usingColumns("nr_polaczenia", "nr_linii", "nr_przystanku", "nr_w_kolejnosci", "minuty_od_startu");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(linia_Przystanek);
        insertActor.execute(param);
    }

    public Linia_Przystanek get(int id) {
        return null;
    }

    public void update(Linia_Przystanek linia_Przystanek) {
        String sql = "UPDATE linia_przystanek SET nr_linii = :nr_linii, " +
                "nr_przystanku = :nr_przystanku, nr_w_kolejnosci = :nr_w_kolejnosci, " +
                "minuty_od_startu = :minuty_od_startu " +
                "WHERE nr_polaczenia = :nr_polaczenia";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(linia_Przystanek);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int id) {
        String sql = "DELETE FROM linia_przystanek WHERE nr_polaczenia = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Linia_Przystanek> findByStop(int stop) {
        String sql = "SELECT * FROM linia_przystanek WHERE nr_przystanku = ?";
        List<Linia_Przystanek> lista = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Linia_Przystanek.class),
                stop
        );
        if (lista.isEmpty()) {
            return null; // lub rzucić UsernameNotFoundException w CustomUserDetailsService
        }
        return lista; // tylko pierwszy wynik
    }

    public List<Linia_Przystanek> findByLine(String line) {
        String sql = "SELECT * FROM linia_przystanek WHERE nr_linii = ?";
        List<Linia_Przystanek> lista = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Linia_Przystanek.class),
                line
        );
        if (lista.isEmpty()) {
            return null; // lub rzucić UsernameNotFoundException w CustomUserDetailsService
        }
        return lista; // tylko pierwszy wynik
    }

}
