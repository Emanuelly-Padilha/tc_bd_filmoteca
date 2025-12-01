package br.edu.ifpr.model;

public class Avaliacao {
    private int id;
    private String nomeUsuario;
    private String nomeFilme;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    private int nota;
    private String review;

    public Avaliacao(String nomeUsuario, String nomeFilme, int nota, String review) {
        this.nomeUsuario = nomeUsuario;
        this.nomeFilme = nomeFilme;
        this.nota = nota;
        this.review = review;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

}
