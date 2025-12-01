package br.edu.ifpr.model;

public class Filme {
    private int id;
    private String titulo;
    private String genero;
    private int ano;
    private String diretor;
    private int duracao;
    private String classificacao;


    public Filme() {
    }


    public Filme(String titulo, String genero, int ano, String diretor, int duracao, String classificacao) {
        this.titulo = titulo;
        this.genero = genero;
        this.ano = ano;
        this.diretor = diretor;
        this.duracao = duracao;
        this.classificacao = classificacao;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getTitulo() {
        return titulo;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getGenero() {
        return genero;
    }


    public void setGenero(String genero) {
        this.genero = genero;
    }


    public int getAno() {
        return ano;
    }


    public void setAno(int ano) {
        this.ano = ano;
    }


    public String getDiretor() {
        return diretor;
    }


    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }


    public int getDuracao() {
        return duracao;
    }


    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }


    public String getClassificacao() {
        return classificacao;
    }


    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }


    @Override
    public String toString() {
        return id + " - " + titulo + " (" + ano + ") - " + genero + " - " + diretor;
    }

}
