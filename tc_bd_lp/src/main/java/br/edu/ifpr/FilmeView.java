package br.edu.ifpr;

import java.util.Scanner;

import br.edu.ifpr.controller.FilmeController;
import br.edu.ifpr.model.Filme;

public class FilmeView {
    private Scanner sc = new Scanner(System.in);
    private FilmeController controller = new FilmeController();

    public void menu() {
        System.out.println("\n=== FILMES ===");
        System.out.println("1 - Cadastrar filme");
        System.out.println("2 - Listar filmes");
        System.out.println("0 - Voltar");

        int op = Integer.parseInt(sc.nextLine());

        switch (op) {
            case 1 : cadastrar();
            case 2 : listar();
        }
    }

    private void cadastrar() {
        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Gênero: ");
        String genero = sc.nextLine();

        System.out.print("Ano: ");
        int ano = Integer.parseInt(sc.nextLine());

        System.out.print("Diretor: ");
        String diretor = sc.nextLine();

        System.out.print("Duração: ");
        int duracao = Integer.parseInt(sc.nextLine());

        System.out.print("Classificação: ");
        String classif = sc.nextLine();

        Filme f = new Filme(titulo, genero, ano, diretor, duracao, classif);
        controller.cadastrar(f);

        System.out.println("Filme cadastrado!");
    }

    private void listar() {
        controller.listar().forEach(f -> {
            System.out.println("- " + f.getTitulo() + " (" + f.getAno() + ")");
        });
    }
}
