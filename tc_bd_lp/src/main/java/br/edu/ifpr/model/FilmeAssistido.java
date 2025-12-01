package br.edu.ifpr.model;

public class FilmeAssistido {
    private int id;
    private String nomeUsuario;
    private String nomeFilme;
    private String dataAssistido;

    public FilmeAssistido() {
    }

    public FilmeAssistido(String nomeUsuario, String nomeFilme, String dataAssistido) {
        this.nomeUsuario = nomeUsuario;
        this.nomeFilme = nomeFilme;
        this.dataAssistido = dataAssistido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getDataAssistido() {
        return dataAssistido;
    }

    public void setDataAssistido(String dataAssistido) {
        this.dataAssistido = dataAssistido;
    }

}
