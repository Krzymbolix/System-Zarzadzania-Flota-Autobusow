package com.example.bada;

public class Pracownicy {
    private int nr_pracownika;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String telefon;
    private String email;
    private String haslo;
    private char aktywny;
    private int nr_bazy;

    // DODAJ DOMYŚLNY KONSTRUKTOR
    public Pracownicy() {
        super();
    }

    public Pracownicy(int nr_pracownika, String imie, String nazwisko, String pesel,
                      String telefon, String email, String haslo, char aktywny, int nr_bazy) {
        this.nr_pracownika = nr_pracownika;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.telefon = telefon;
        this.email = email;
        this.haslo = haslo;
        this.aktywny = aktywny;
        this.nr_bazy = nr_bazy;
    }

    public int getNr_pracownika() {
        return nr_pracownika;
    }

    public void setNr_pracownika(int nr_pracownika) {
        this.nr_pracownika = nr_pracownika;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public char getAktywny() {
        return aktywny;
    }

    public void setAktywny(char aktywny) {
        this.aktywny = aktywny;
    }

    public int getNr_bazy() {
        return nr_bazy;
    }

    public void setNr_bazy(int nr_bazy) {
        this.nr_bazy = nr_bazy;
    }

    @Override
    public String toString() {
        return "Pracownicy{" +
                "nr_pracownika=" + nr_pracownika +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pesel='" + pesel + '\'' +
                ", telefon='" + telefon + '\'' +
                ", email='" + email + '\'' +
                ", haslo='" + haslo + '\'' +
                ", aktywny=" + aktywny +
                ", nr_bazy=" + nr_bazy +
                '}';
    }
}
