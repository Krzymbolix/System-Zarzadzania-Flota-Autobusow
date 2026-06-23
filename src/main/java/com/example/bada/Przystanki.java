package com.example.bada;

public class Przystanki {
    private int nr_przystanku;
    private String nazwa;
    private int nr_porzadkowy;
    private int czy_wiata;

    public int getNr_przystanku() {
        return nr_przystanku;
    }

    public void setNr_przystanku(int nr_przystanku) {
        this.nr_przystanku = nr_przystanku;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getNr_porzadkowy() {
        return nr_porzadkowy;
    }

    public void setNr_porzadkowy(int nr_porzadkowy) {
        this.nr_porzadkowy = nr_porzadkowy;
    }

    public int getCzy_wiata() {
        return czy_wiata;
    }

    public void setCzy_wiata(int czy_wiata) {
        this.czy_wiata = czy_wiata;
    }

    @Override
    public String toString() {
        return "Przystanki{" +
                "nr_przystanku=" + nr_przystanku +
                ", nazwa='" + nazwa + '\'' +
                ", nr_porzadkowy=" + nr_porzadkowy +
                ", czy_wiata=" + czy_wiata +
                '}';
    }
}
