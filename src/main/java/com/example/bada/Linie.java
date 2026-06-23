package com.example.bada;

public class Linie {
    private String nr_linii;
    private String rodzaj;
    private String kierunek;
    private int nr_bazy;

    public String getNr_linii() {
        return nr_linii;
    }

    public void setNr_linii(String nr_linii) {
        this.nr_linii = nr_linii;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public String getKierunek() {
        return kierunek;
    }

    public void setKierunek(String kierunek) {
        this.kierunek = kierunek;
    }

    public int getNr_bazy() {
        return nr_bazy;
    }

    public void setNr_bazy(int nr_bazy) {
        this.nr_bazy = nr_bazy;
    }

    @Override
    public String toString() {
        return "Linie{" +
                "nr_linii='" + nr_linii + '\'' +
                ", rodzaj='" + rodzaj + '\'' +
                ", kierunek='" + kierunek + '\'' +
                ", nr_bazy=" + nr_bazy +
                '}';
    }
}
