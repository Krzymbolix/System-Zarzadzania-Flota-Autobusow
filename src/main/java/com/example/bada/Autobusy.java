package com.example.bada;

public class Autobusy {
    private int nr_taborowy;
    private String nr_rejestracyjny;
    private int nr_bazy;
    private Integer pojemnosc;  // Integer zamiast int, bo może być NULL
    private char czy_przegubowy;  // 'T' lub 'N'

    // Konstruktory
    public Autobusy() {
        super();
    }

    public Autobusy(int nr_taborowy, String nr_rejestracyjny, int nr_bazy,
                    Integer pojemnosc, char czy_przegubowy) {
        this.nr_taborowy = nr_taborowy;
        this.nr_rejestracyjny = nr_rejestracyjny;
        this.nr_bazy = nr_bazy;
        this.pojemnosc = pojemnosc;
        this.czy_przegubowy = czy_przegubowy;
    }

    // Gettery i Settery
    public int getNr_taborowy() {
        return nr_taborowy;
    }

    public void setNr_taborowy(int nr_taborowy) {
        this.nr_taborowy = nr_taborowy;
    }

    public String getNr_rejestracyjny() {
        return nr_rejestracyjny;
    }

    public void setNr_rejestracyjny(String nr_rejestracyjny) {
        this.nr_rejestracyjny = nr_rejestracyjny;
    }

    public int getNr_bazy() {
        return nr_bazy;
    }

    public void setNr_bazy(int nr_bazy) {
        this.nr_bazy = nr_bazy;
    }

    public Integer getPojemnosc() {
        return pojemnosc;
    }

    public void setPojemnosc(Integer pojemnosc) {
        this.pojemnosc = pojemnosc;
    }

    public char getCzy_przegubowy() {
        return czy_przegubowy;
    }

    public void setCzy_przegubowy(char czy_przegubowy) {
        this.czy_przegubowy = czy_przegubowy;
    }

    @Override
    public String toString() {
        return "Autobusy{" +
                "nr_taborowy=" + nr_taborowy +
                ", nr_rejestracyjny='" + nr_rejestracyjny + '\'' +
                ", nr_bazy=" + nr_bazy +
                ", pojemnosc=" + pojemnosc +
                ", czy_przegubowy=" + czy_przegubowy +
                '}';
    }
}
