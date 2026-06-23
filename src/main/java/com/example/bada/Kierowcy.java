package com.example.bada;

import java.sql.Date;

public class Kierowcy {
    private int nr_pracownika;
    private String kat_prawa_jazdy;
    private Date data_badan;

    // Konstruktory
    public Kierowcy() {
        super();
    }

    public Kierowcy(int nr_pracownika, String kat_prawa_jazdy, Date data_badan) {
        this.nr_pracownika = nr_pracownika;
        this.kat_prawa_jazdy = kat_prawa_jazdy;
        this.data_badan = data_badan;
    }

    // Gettery i Settery
    public int getNr_pracownika() {
        return nr_pracownika;
    }

    public void setNr_pracownika(int nr_pracownika) {
        this.nr_pracownika = nr_pracownika;
    }

    public String getKat_prawa_jazdy() {
        return kat_prawa_jazdy;
    }

    public void setKat_prawa_jazdy(String kat_prawa_jazdy) {
        this.kat_prawa_jazdy = kat_prawa_jazdy;
    }

    public Date getData_badan() {
        return data_badan;
    }

    public void setData_badan(Date data_badan) {
        this.data_badan = data_badan;
    }

    @Override
    public String toString() {
        return "Kierowcy{" +
                "nr_pracownika=" + nr_pracownika +
                ", kat_prawa_jazdy='" + kat_prawa_jazdy + '\'' +
                ", data_badan=" + data_badan +
                '}';
    }
}
