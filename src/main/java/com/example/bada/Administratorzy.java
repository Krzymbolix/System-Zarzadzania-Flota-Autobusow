package com.example.bada;

public class Administratorzy {
    private int nr_pracownika;

    public Administratorzy() {
        super();
    }

    public Administratorzy(int nr_pracownika) {
        this.nr_pracownika = nr_pracownika;
    }

    public int getNr_pracownika() {
        return nr_pracownika;
    }

    public void setNr_pracownika(int nr_pracownika) {
        this.nr_pracownika = nr_pracownika;
    }

    @Override
    public String toString() {
        return "Administratorzy{" +
                "nr_pracownika=" + nr_pracownika +
                '}';
    }
}
