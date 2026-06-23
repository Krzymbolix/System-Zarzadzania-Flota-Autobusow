package com.example.bada;

public class Linia_Przystanek {
    private int nr_polaczenia;
    private String nr_linii;
    private int nr_przystanku;
    private int nr_w_kolejnosci;
    private int minuty_od_startu;

    public int getNr_polaczenia() {
        return nr_polaczenia;
    }

    public void setNr_polaczenia(int nr_polaczenia) {
        this.nr_polaczenia = nr_polaczenia;
    }

    public String getNr_linii() {
        return nr_linii;
    }

    public void setNr_linii(String nr_linii) {
        this.nr_linii = nr_linii;
    }

    public int getNr_przystanku() {
        return nr_przystanku;
    }

    public void setNr_przystanku(int nr_przystanku) {
        this.nr_przystanku = nr_przystanku;
    }

    public int getNr_w_kolejnosci() {
        return nr_w_kolejnosci;
    }

    public void setNr_w_kolejnosci(int nr_w_kolejnosci) {
        this.nr_w_kolejnosci = nr_w_kolejnosci;
    }

    public int getMinuty_od_startu() {
        return minuty_od_startu;
    }

    public void setMinuty_od_startu(int minuty_od_startu) {
        this.minuty_od_startu = minuty_od_startu;
    }

    @Override
    public String toString() {
        return "Linia_Przystanek{" +
                "nr_polaczenia=" + nr_polaczenia +
                ", nr_linii='" + nr_linii + '\'' +
                ", nr_przystanku=" + nr_przystanku +
                ", nr_w_kolejnosci=" + nr_w_kolejnosci +
                ", minuty_od_startu=" + minuty_od_startu +
                '}';
    }
}
