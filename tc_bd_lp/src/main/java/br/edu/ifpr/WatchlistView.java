package br.edu.ifpr;

import java.util.Scanner;

import br.edu.ifpr.controller.WatchlistController;
import br.edu.ifpr.model.Usuario;
import br.edu.ifpr.model.Watchlist;

public class WatchlistView {
    private Scanner sc = new Scanner(System.in);
    public void menu(Usuario logado) {
        System.out.println("\n=== WATCHLIST ===");
        System.out.println("1 - Adicionar filme à watchlist");
        System.out.println("2 - Ver minha watchlist");
        System.out.println("0 - Voltar");
    
        int op = Integer.parseInt(sc.nextLine());
    
        switch (op) {
            case 1 : adicionar(logado);
            case 2 : listar(logado);
        }
    }
    
    private void adicionar(Usuario logado) {
        System.out.print("Nome do filme: ");
        String nomeFilme = sc.nextLine();
    
        Watchlist w = new Watchlist(logado.getNome(), nomeFilme);
        controller.adicionar(w);
    
        System.out.println("Filme adicionado!");
    }
    
    private void listar(Usuario logado) {
        controller.listar(logado.getNome()).forEach(w -> {
            System.out.println("- " + w.getNomeFilme());
        });
}
}
