package com.example.bada;

public class Bazy {
    private int nr_bazy;
    private String nazwa_ulicy;
    private int nr_porzadkowy_ulicy;
    private String kod_pocztowy;

    public Bazy() {
        super();
    }

    public Bazy(int nr_bazy, String nazwa_ulicy, int nr_porzadkowy_ulicy, String kod_pocztowy) {
        this.nr_bazy = nr_bazy;
        this.nazwa_ulicy = nazwa_ulicy;
        this.nr_porzadkowy_ulicy = nr_porzadkowy_ulicy;
        this.kod_pocztowy = kod_pocztowy;
    }

    public int getNr_bazy() {
        return nr_bazy;
    }

    public void setNr_bazy(int nr_bazy) {
        this.nr_bazy = nr_bazy;
    }

    public String getNazwa_ulicy() {
        return nazwa_ulicy;
    }

    public void setNazwa_ulicy(String nazwa_ulicy) {
        this.nazwa_ulicy = nazwa_ulicy;
    }

    public int getNr_porzadkowy_ulicy() {
        return nr_porzadkowy_ulicy;
    }

    public void setNr_porzadkowy_ulicy(int nr_porzadkowy_ulicy) {
        this.nr_porzadkowy_ulicy = nr_porzadkowy_ulicy;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "nr_bazy=" + nr_bazy +
                ", nazwa_ulicy='" + nazwa_ulicy + '\'' +
                ", nr_porzadkowy_ulicy=" + nr_porzadkowy_ulicy +
                ", kod_pocztowy='" + kod_pocztowy + '\'' +
                '}';
    }
}
