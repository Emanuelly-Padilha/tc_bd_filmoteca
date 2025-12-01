package br.edu.ifpr.view;

import java.util.Scanner;
import java.util.List;

import br.edu.ifpr.controller.AvaliacaoController;
import br.edu.ifpr.controller.FilmeAssistidoController;
import br.edu.ifpr.controller.FilmeController;
import br.edu.ifpr.controller.UsuarioController;
import br.edu.ifpr.controller.WatchlistController;
import br.edu.ifpr.model.Usuario;
import br.edu.ifpr.model.Filme;
import br.edu.ifpr.model.Watchlist;
import br.edu.ifpr.model.Avaliacao;
import br.edu.ifpr.model.FilmeAssistido;

public class Main {
    
    private static Scanner sc = new Scanner(System.in);
    
    // Criar os controladores (objetos que controlam as operações)
    private static UsuarioController usuarioController = new UsuarioController();
    private static FilmeController filmeController = new FilmeController();
    private static WatchlistController watchlistController = new WatchlistController();
    private static AvaliacaoController avaliacaoController = new AvaliacaoController();
    private static FilmeAssistidoController assistidoController = new FilmeAssistidoController();
    
    // Usuário que está logado no sistema
    private static Usuario usuarioLogado = null;
    
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("     SISTEMA FILMOTECA");
        System.out.println("=================================");
        
        // Primeiro: mostrar tela de login/cadastro
        telaLogin();
        
        // Se o usuário fez login com sucesso, mostrar menu principal
        if (usuarioLogado != null) {
            menuPrincipal();
        }
        
        System.out.println("\nPrograma encerrado.");
        sc.close();
    }
    
    // ============================================
    // TELA DE LOGIN E CADASTRO
    // ============================================
    private static void telaLogin() {
        int opcao;
        
        do {
            System.out.println("\n=== TELA DE LOGIN ===");
            System.out.println("1 - Fazer login");
            System.out.println("2 - Criar nova conta");
            System.out.println("0 - Sair do sistema");
            System.out.print("Escolha uma opção: ");
            
            opcao = sc.nextInt();
            sc.nextLine(); // Limpar o buffer do teclado
            
            switch(opcao) {
                case 1:
                    fazerLogin();
                    break;
                case 2:
                    criarConta();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Digite 0, 1 ou 2.");
            }
            
        } while(opcao != 0 && usuarioLogado == null);
    }
    
    private static void fazerLogin() {
        System.out.println("\n--- FAZER LOGIN ---");
        System.out.print("Digite seu email: ");
        String email = sc.nextLine();
        
        System.out.print("Digite sua senha: ");
        String senha = sc.nextLine();
        
        // Usar o método login do controlador
        usuarioLogado = UsuarioController.login(email, senha);
        
        if(usuarioLogado != null) {
            System.out.println("Login realizado com sucesso!");
            System.out.println("Bem-vindo, " + usuarioLogado.getNome() + "!");
        } else {
            System.out.println("Email ou senha incorretos. Tente novamente.");
        }
    }
    
    private static void criarConta() {
        System.out.println("\n--- CRIAR NOVA CONTA ---");
        System.out.print("Digite seu nome: ");
        String nome = sc.nextLine();
        
        System.out.print("Digite seu email: ");
        String email = sc.nextLine();
        
        System.out.print("Digite sua senha: ");
        String senha = sc.nextLine();
        
        // Criar novo usuário
        Usuario novoUsuario = new Usuario(nome, email, senha);
        
        // Cadastrar no banco de dados
        usuarioController.cadastrar(novoUsuario);
        
        System.out.println("Conta criada com sucesso! Agora faça login.");
    }
    
    // ============================================
    // MENU PRINCIPAL
    // ============================================
    private static void menuPrincipal() {
        int opcao;
        
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("Usuário: " + usuarioLogado.getNome());
            System.out.println("------------------------");
            System.out.println("1 - Gerenciar Usuários");
            System.out.println("2 - Gerenciar Filmes");
            System.out.println("3 - Minha Watchlist");
            System.out.println("4 - Minhas Avaliações");
            System.out.println("5 - Filmes Assistidos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = sc.nextInt();
            sc.nextLine(); // Limpar o buffer
            
            switch(opcao) {
                case 1:
                    menuUsuarios();
                    break;
                case 2:
                    menuFilmes();
                    break;
                case 3:
                    menuWatchlist();
                    break;
                case 4:
                    menuAvaliacoes();
                    break;
                case 5:
                    menuFilmesAssistidos();
                    break;
                case 0:
                    System.out.println("Saindo... Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Digite um número de 0 a 5.");
            }
            
        } while(opcao != 0);
    }
    
    // ============================================
    // MENU DE USUÁRIOS
    // ============================================
    private static void menuUsuarios() {
        int opcao;
        
        do {
            System.out.println("\n=== MENU USUÁRIOS ===");
            System.out.println("1 - Listar todos os usuários");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");
            
            opcao = sc.nextInt();
            sc.nextLine();
            
            if(opcao == 1) {
                listarUsuarios();
            } else if(opcao != 0) {
                System.out.println("Opção inválida! Digite 0 ou 1.");
            }
            
        } while(opcao != 0);
    }
    
    private static void listarUsuarios() {
        System.out.println("\n--- LISTA DE USUÁRIOS ---");
        List<Usuario> listaUsuarios = usuarioController.listar();
        
        if(listaUsuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            System.out.println("Usuários cadastrados no sistema:");
            for(Usuario usuario : listaUsuarios) {
                System.out.println("Nome: " + usuario.getNome() + " | Email: " + usuario.getEmail());
            }
            System.out.println("Total: " + listaUsuarios.size() + " usuário(s)");
        }
    }
    
    // ============================================
    // MENU DE FILMES
    // ============================================
    private static void menuFilmes() {
        int opcao;
        
        do {
            System.out.println("\n=== MENU FILMES ===");
            System.out.println("1 - Cadastrar novo filme");
            System.out.println("2 - Listar todos os filmes");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");
            
            opcao = sc.nextInt();
            sc.nextLine();
            
            switch(opcao) {
                case 1:
                    cadastrarFilme();
                    break;
                case 2:
                    listarFilmes();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida! Digite 0, 1 ou 2.");
            }
            
        } while(opcao != 0);
    }
    
    private static void cadastrarFilme() {
        System.out.println("\n--- CADASTRAR FILME ---");
        
        System.out.print("Título do filme: ");
        String titulo = sc.nextLine();
        
        System.out.print("Gênero (ex: Ação, Comédia, Drama): ");
        String genero = sc.nextLine();
        
        System.out.print("Ano de lançamento: ");
        int ano = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Diretor: ");
        String diretor = sc.nextLine();
        
        System.out.print("Duração (em minutos): ");
        int duracao = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Classificação etária (ex: Livre, 12 anos, 16 anos): ");
        String classificacao = sc.nextLine();
        
        // Criar objeto Filme
        Filme novoFilme = new Filme(titulo, genero, ano, diretor, duracao, classificacao);
        
        // Salvar no banco de dados
        filmeController.cadastrar(novoFilme);
        
        System.out.println("Filme cadastrado com sucesso!");
    }
    
    private static void listarFilmes() {
        System.out.println("\n--- LISTA DE FILMES ---");
        List<Filme> listaFilmes = filmeController.listar();
        
        if(listaFilmes.isEmpty()) {
            System.out.println("Nenhum filme cadastrado.");
        } else {
            System.out.println("Filmes cadastrados no sistema:");
            for(Filme filme : listaFilmes) {
                System.out.println("--------------------------------");
                System.out.println("Título: " + filme.getTitulo());
                System.out.println("Ano: " + filme.getAno());
                System.out.println("Gênero: " + filme.getGenero());
                System.out.println("Diretor: " + filme.getDiretor());
                System.out.println("Duração: " + filme.getDuracao() + " minutos");
                System.out.println("Classificação: " + filme.getClassificacao());
            }
            System.out.println("--------------------------------");
            System.out.println("Total: " + listaFilmes.size() + " filme(s)");
        }
    }
    
    // ============================================
    // MENU WATCHLIST (LISTA DE DESEJOS)
    // ============================================
    private static void menuWatchlist() {
        int opcao;
        
        do {
            System.out.println("\n=== MINHA WATCHLIST ===");
            System.out.println("1 - Adicionar filme à watchlist");
            System.out.println("2 - Ver minha watchlist");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");
            
            opcao = sc.nextInt();
            sc.nextLine();
            
            switch(opcao) {
                case 1:
                    adicionarWatchlist();
                    break;
                case 2:
                    verWatchlist();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida! Digite 0, 1 ou 2.");
            }
            
        } while(opcao != 0);
    }
    
    private static void adicionarWatchlist() {
        System.out.println("\n--- ADICIONAR À WATCHLIST ---");
        System.out.print("Nome do filme que deseja assistir: ");
        String nomeFilme = sc.nextLine();
        
        // Criar objeto Watchlist
        Watchlist item = new Watchlist(usuarioLogado.getNome(), nomeFilme);
        
        // Adicionar ao banco de dados
        watchlistController.adicionar(item);
        
        System.out.println("Filme adicionado à sua watchlist!");
    }
    
    private static void verWatchlist() {
        System.out.println("\n--- MINHA WATCHLIST ---");
        List<Watchlist> minhaWatchlist = watchlistController.listar(usuarioLogado.getNome());
        
        if(minhaWatchlist.isEmpty()) {
            System.out.println("Sua watchlist está vazia.");
            System.out.println("Adicione filmes que você quer assistir!");
        } else {
            System.out.println("Filmes na sua watchlist:");
            for(Watchlist item : minhaWatchlist) {
                System.out.println("- " + item.getNomeFilme());
            }
            System.out.println("Total: " + minhaWatchlist.size() + " filme(s) na lista");
        }
    }
    
    // ============================================
    // MENU AVALIAÇÕES
    // ============================================
    private static void menuAvaliacoes() {
        int opcao;
        
        do {
            System.out.println("\n=== MINHAS AVALIAÇÕES ===");
            System.out.println("1 - Avaliar um filme");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");
            
            opcao = sc.nextInt();
            sc.nextLine();
            
            if(opcao == 1) {
                avaliarFilme();
            } else if(opcao != 0) {
                System.out.println("Opção inválida! Digite 0 ou 1.");
            }
            
        } while(opcao != 0);
    }
    
    private static void avaliarFilme() {
        System.out.println("\n--- AVALIAR FILME ---");
        
        System.out.print("Nome do filme que você quer avaliar: ");
        String nomeFilme = sc.nextLine();
        
        System.out.print("Nota (de 1 a 10, onde 10 é excelente): ");
        int nota = sc.nextInt();
        sc.nextLine();
        
        // Verificar se a nota está no intervalo correto
        if(nota < 1 || nota > 10) {
            System.out.println("Nota inválida! Deve ser entre 1 e 10.");
            return;
        }
        
        System.out.print("Sua opinião sobre o filme: ");
        String review = sc.nextLine();
        
        // Criar objeto Avaliacao
        Avaliacao avaliacao = new Avaliacao(usuarioLogado.getNome(), nomeFilme, nota, review);
        
        // Salvar no banco de dados
        avaliacaoController.avaliar(avaliacao);
        
        System.out.println("Avaliação registrada com sucesso!");
        System.out.println("Obrigado por compartilhar sua opinião!");
    }
    
    // ============================================
    // MENU FILMES ASSISTIDOS
    // ============================================
    private static void menuFilmesAssistidos() {
        int opcao;
        
        do {
            System.out.println("\n=== FILMES ASSISTIDOS ===");
            System.out.println("1 - Registrar filme assistido");
            System.out.println("2 - Ver meus filmes assistidos");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");
            
            opcao = sc.nextInt();
            sc.nextLine();
            
            switch(opcao) {
                case 1:
                    registrarFilmeAssistido();
                    break;
                case 2:
                    verFilmesAssistidos();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida! Digite 0, 1 ou 2.");
            }
            
        } while(opcao != 0);
    }
    
    private static void registrarFilmeAssistido() {
        System.out.println("\n--- REGISTRAR FILME ASSISTIDO ---");
        
        System.out.print("Nome do filme que você assistiu: ");
        String nomeFilme = sc.nextLine();
        
        System.out.print("Data que assistiu (formato: dia/mês/ano, ex: 15/11/2024): ");
        String data = sc.nextLine();
        
        // Criar objeto FilmeAssistido
        FilmeAssistido filme = new FilmeAssistido(usuarioLogado.getNome(), nomeFilme, data);
        
        // Salvar no banco de dados
        assistidoController.registrar(filme);
        
        System.out.println("Filme registrado como assistido!");
    }
    
    private static void verFilmesAssistidos() {
        System.out.println("\n--- MEUS FILMES ASSISTIDOS ---");
        List<FilmeAssistido> meusFilmes = assistidoController.listar(usuarioLogado.getNome());
        
        if(meusFilmes.isEmpty()) {
            System.out.println("Você ainda não registrou nenhum filme assistido.");
            System.out.println("Registre os filmes que você já assistiu!");
        } else {
            System.out.println("Filmes que você já assistiu:");
            for(FilmeAssistido filme : meusFilmes) {
                System.out.println("- " + filme.getNomeFilme() + " (Assistido em: " + filme.getDataAssistido() + ")");
            }
            System.out.println("Total: " + meusFilmes.size() + " filme(s) assistido(s)");
        }
    }
}