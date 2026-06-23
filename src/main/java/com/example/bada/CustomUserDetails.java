package com.example.bada;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
    private final int nr_pracownika;
    private final String imie;
    private final String nazwisko;
    private final String pesel;
    private final String telefon;
    private final String email;
    private final String haslo;
    private final int nr_bazy;


    public CustomUserDetails(Pracownicy p, String role) {
        super(p.getEmail(), p.getHaslo(), true, true, true, true,
                List.of(new SimpleGrantedAuthority("ROLE_" + role)));
        this.nr_pracownika = p.getNr_pracownika();
        this.imie = p.getImie();
        this.nazwisko = p.getNazwisko();
        this.pesel = p.getPesel();
        this.telefon = p.getTelefon();
        this.email = p.getEmail();
        this.haslo = p.getHaslo();
        this.nr_bazy = p.getNr_bazy();
    }

    public String getImie() {
        return imie;
    }
}
