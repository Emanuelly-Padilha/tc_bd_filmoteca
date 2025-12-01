package br.edu.ifpr;

import java.util.Scanner;

import br.edu.ifpr.model.Usuario;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LoginView login = new LoginView();
        Usuario logado = login.autenticar();

        if (logado == null)
            return;

        FilmeView filmeView = new FilmeView();
        LoginView usuarioView = new LoginView();
        WatchlistView watchlistView = new WatchlistView();
        AvaliacaoView avaliacaoView = new AvaliacaoView();
        FilmeAssistidoView assistidoView = new FilmeAssistidoView();

        while (true) {
            System.out.println(
                    "░▒▓████████▓▒░▒▓█▓▒░▒▓█▓▒░      ░▒▓██████████████▓▒░ ░▒▓██████▓▒░▒▓████████▓▒░▒▓████████▓▒░▒▓██████▓▒░ ░▒▓██████▓▒░  \n"
                            + //
                            "░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ \n"
                            + //
                            "░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░     ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ \n"
                            + //
                            "░▒▓██████▓▒░ ░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓██████▓▒░░▒▓█▓▒░      ░▒▓████████▓▒░ \n"
                            + //
                            "░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░     ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ \n"
                            + //
                            "░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ \n"
                            + //
                            "░▒▓█▓▒░      ░▒▓█▓▒░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░  ░▒▓█▓▒░   ░▒▓████████▓▒░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░");
            System.out.println();
            System.out.println();
            System.out.println("Usuário: " + logado.getNome());
            System.out.println("1 - Usuários");
            System.out.println("2 - Filmes");
            System.out.println("3 - Watchlist");
            System.out.println("4 - Avaliações");
            System.out.println("5 - Filmes Assistidos");
            System.out.println("0 - Sair");

            int op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 : usuarioView.menu();
                case 2 : filmeView.menu();
                case 3 : watchlistView.menu(logado);
                case 4 : avaliacaoView.menu(logado);
                case 5 : assistidoView.menu(logado);
                case 0 : {
                    System.out.println("Saindo...");
                    return;
                }
                default  : System.out.println("Opção inválida!");
            }
        }
    }

}
