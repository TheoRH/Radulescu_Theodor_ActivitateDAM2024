package com.example.seminar4;

public class Termos {
    private String nume; //rez
    private int numar;//rez
    private String detalii;//rez
    private boolean curat;//rez
    private float grade;//rez

    public Termos(String nume, int numar, String detalii, boolean curat, float grade) {
        this.nume = nume;
        this.numar = numar;
        this.detalii = detalii;
        this.curat = curat;
        this.grade = grade;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    public String getDetalii() {
        return detalii;
    }

    public void setDetalii(String detalii) {
        this.detalii = detalii;
    }

    public boolean isCurat() {
        return curat;
    }

    public void setCurat(boolean curat) {
        this.curat = curat;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Termos{");
        sb.append("nume='").append(nume).append('\'');
        sb.append(", numar=").append(numar);
        sb.append(", detalii='").append(detalii).append('\'');
        sb.append(", curat=").append(curat);
        sb.append(", grade=").append(grade);
        sb.append('}');
        return sb.toString();
    }
}
