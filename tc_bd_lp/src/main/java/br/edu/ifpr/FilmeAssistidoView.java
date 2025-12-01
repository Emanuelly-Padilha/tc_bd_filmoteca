package br.edu.ifpr;

import java.util.Scanner;

import br.edu.ifpr.controller.FilmeAssistidoController;
import br.edu.ifpr.model.FilmeAssistido;
import br.edu.ifpr.model.Usuario;

public class FilmeAssistidoView {
      private Scanner sc = new Scanner(System.in);
    private FilmeAssistidoController controller = new FilmeAssistidoController();

   public void menu(Usuario logado) {
    System.out.println("\n=== FILMES ASSISTIDOS ===");
    System.out.println("1 - Registrar filme assistido");
    System.out.println("2 - Listar meus filmes assistidos");
    System.out.println("0 - Voltar");

    int op = Integer.parseInt(sc.nextLine());

    switch (op) {
        case 1 :registrar(logado);
        case 2 : listar(logado);
    }
}

private void registrar(Usuario logado) {

    System.out.print("Nome do filme: ");
    String nomeFilme = sc.nextLine();

    System.out.print("Data assistido: ");
    String data = sc.nextLine();

    FilmeAssistido f = new FilmeAssistido(logado.getNome(), nomeFilme, data);
    controller.registrar(f);

    System.out.println("Registrado!");
}

private void listar(Usuario logado) {
    controller.listar(logado.getNome()).forEach(f -> {
        System.out.println("- " + f.getNomeFilme() + " | " + f.getDataAssistido());
    });
}
}
