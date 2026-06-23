package com.example.bada;

import com.example.bada.Pracownicy;
import com.example.bada.PracownicyDAO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PracownicyDAO pracownicyDAO;
    private final AdministratorzyDAO administratorzyDAO;

    public CustomUserDetailsService(PracownicyDAO pracownicyDAO, AdministratorzyDAO administratorzyDAO) {
        this.pracownicyDAO = pracownicyDAO;
        this.administratorzyDAO = administratorzyDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Pracownicy p = pracownicyDAO.findByEmail(username);

        if (p == null) {throw new UsernameNotFoundException("Nie znaleziono użytkownika: " + username);}
        boolean aktywny = p.getAktywny() != '0';
        String role = "USER";
        if (administratorzyDAO.findByNr(p.getNr_pracownika()) != null)  role = "ADMIN";

        return new CustomUserDetails(p, role);

    }
}
