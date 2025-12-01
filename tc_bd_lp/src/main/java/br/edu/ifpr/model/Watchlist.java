package br.edu.ifpr.model;

public class Watchlist {
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


    public Watchlist(String nomeUsuario, String nomeFilme) {
        this.nomeUsuario = nomeUsuario;
        this.nomeFilme = nomeFilme;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


  



}
