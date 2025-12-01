package br.edu.ifpr;

import java.util.Scanner;

import br.edu.ifpr.controller.AvaliacaoController;
import br.edu.ifpr.model.Avaliacao;
import br.edu.ifpr.model.Usuario;

public class AvaliacaoView {
    
    private Scanner sc = new Scanner(System.in);
    private AvaliacaoController controller = new AvaliacaoController();

   public void menu(Usuario logado) {
    System.out.println("\n=== AVALIAÇÕES ===");
    System.out.println("1 - Avaliar filme");
    System.out.println("0 - Voltar");

    int op = Integer.parseInt(sc.nextLine());

    if (op == 1) cadastrar(logado);
}

private void cadastrar(Usuario logado) {

    System.out.print("Nome do filme: ");
    String nomeFilme = sc.nextLine();

    System.out.print("Nota: ");
    int nota = Integer.parseInt(sc.nextLine());

    System.out.print("Review: ");
    String review = sc.nextLine();

    Avaliacao a = new Avaliacao(logado.getNome(), nomeFilme, nota, review);
    controller.avaliar(a);

    System.out.println("Avaliação registrada!");
}
}