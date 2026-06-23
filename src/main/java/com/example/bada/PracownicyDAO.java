package com.example.bada;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PracownicyDAO {
    private final JdbcTemplate jdbcTemplate;

    public PracownicyDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Pracownicy> list() {
        String sql = "SELECT * FROM pracownicy";
        List<Pracownicy> listPracownicy = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Pracownicy.class));
        return listPracownicy;
    }

    public void save(Pracownicy pracownicy) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("pracownicy").usingColumns("nr_pracownika","imie","nazwisko","pesel","telefon","email","haslo","aktywny","nr_bazy");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(pracownicy);
        insertActor.execute(param);
    }

    public Pracownicy get(int id) {
        return null;
    }

    public void update(Pracownicy pracownicy) {
        String sql = "UPDATE pracownicy SET imie = '" + pracownicy.getImie() + "', nazwisko = " + pracownicy.getNazwisko()
                + ", pesel = " + pracownicy.getPesel() + ", telefon = " + pracownicy.getTelefon() + ", email = " + pracownicy.getEmail() + ", haslo = " + pracownicy.getHaslo() + ", aktywny = " + pracownicy.getAktywny() + ", nr_bazy = " + pracownicy.getNr_bazy() + " where nr_pracownika = " + pracownicy.getNr_pracownika();
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(pracownicy);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int id) {
        String sql = "DELETE FROM pracownicy WHERE nr_pracownika = ?";
        jdbcTemplate.update(sql, id);
    }

    public Pracownicy findByEmail(String email) {
        String sql = "SELECT * FROM pracownicy WHERE email = ?";
        List<Pracownicy> lista = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(Pracownicy.class),
                email
        );
        if (lista.isEmpty()) {
            return null; // lub rzucić UsernameNotFoundException w CustomUserDetailsService
        }
        return lista.get(0); // tylko pierwszy wynik
    }

}
