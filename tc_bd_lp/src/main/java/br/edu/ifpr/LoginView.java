package br.edu.ifpr;

import java.util.Scanner;

import br.edu.ifpr.controller.UsuarioController;
import br.edu.ifpr.model.Usuario;

public class LoginView {
    
    private Scanner sc = new Scanner(System.in);
    private UsuarioController controller = new UsuarioController();

    public Usuario autenticar() {

        while (true) {
            System.out.println("\n=== LOGIN ===");
            System.out.println("1 - Fazer login");
            System.out.println("2 - Criar conta");
            System.out.println("0 - Sair");

            int op = Integer.parseInt(sc.nextLine());

            switch (op) {

                case 1 : {
                    return login();
                }
                case 2 : {
                    cadastrar();
                    break; 
                }
                case 0 : {
                    System.out.println("Encerrando...");
                    return null;
                }
                default : System.out.println("Opção inválida!");
            }
        }
    }

    private Usuario login() {
        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Usuario u = UsuarioController.login(email, senha);

        if (u == null) {
            System.out.println("Email ou senha incorretos!");
            return null;
        }

        System.out.println("Bem-vindo(a), " + u.getNome() + "!");
        return u;
    }

    private void cadastrar() {
        System.out.println("\n=== NOVO USUÁRIO ===");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Usuario u = new Usuario(nome, email, senha);
        controller.cadastrar(u);

        System.out.println("Cadastro realizado! Agora faça login.");
    }
}
