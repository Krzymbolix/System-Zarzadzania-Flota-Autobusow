package com.example.bada;

import java.sql.Date;

public class Kursy {
    private int nr_kursu;
    private int nr_pracownika;
    private String nr_linii;
    private int nr_taborowy;
    private Date godzina_odjazdu;

    public int getNr_kursu() {
        return nr_kursu;
    }

    public void setNr_kursu(int nr_kursu) {
        this.nr_kursu = nr_kursu;
    }

    public int getNr_pracownika() {
        return nr_pracownika;
    }

    public void setNr_pracownika(int nr_pracownika) {
        this.nr_pracownika = nr_pracownika;
    }

    public String getNr_linii() {
        return nr_linii;
    }

    public void setNr_linii(String nr_linii) {
        this.nr_linii = nr_linii;
    }

    public int getNr_taborowy() {
        return nr_taborowy;
    }

    public void setNr_taborowy(int nr_taborowy) {
        this.nr_taborowy = nr_taborowy;
    }

    public Date getGodzina_odjazdu() {
        return godzina_odjazdu;
    }

    public void setGodzina_odjazdu(Date godzina_odjazdu) {
        this.godzina_odjazdu = godzina_odjazdu;
    }

    @Override
    public String toString() {
        return "Kursy{" +
                "nr_kursu=" + nr_kursu +
                ", nr_pracownika=" + nr_pracownika +
                ", nr_linii='" + nr_linii + '\'' +
                ", nr_taborowy=" + nr_taborowy +
                ", godzina_odjazdu=" + godzina_odjazdu +
                '}';
    }
}
