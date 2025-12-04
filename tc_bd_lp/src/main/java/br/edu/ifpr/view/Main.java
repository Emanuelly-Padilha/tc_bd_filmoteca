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

    // objetos que controlam as operações
    private static UsuarioController usuarioController = new UsuarioController();
    private static FilmeController filmeController = new FilmeController();
    private static WatchlistController watchlistController = new WatchlistController();
    private static AvaliacaoController avaliacaoController = new AvaliacaoController();
    private static FilmeAssistidoController assistidoController = new FilmeAssistidoController();

    private static Usuario usuarioLogado = null;

    public static void main(String[] args) {
        System.out.println();
        System.out.println(
                "░▒▓████████▓▒░▒▓█▓▒░▒▓█▓▒░      ░▒▓██████████████▓▒░ ░▒▓██████▓▒░▒▓████████▓▒░▒▓████████▓▒░▒▓██████▓▒░ ░▒▓██████▓▒░  \n"
                        +
                        "░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ \n"
                        +
                        "░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░     ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ \n"
                        +
                        "░▒▓██████▓▒░ ░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓██████▓▒░░▒▓█▓▒░      ░▒▓████████▓▒░ \n"
                        +
                        "░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░     ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ \n"
                        +
                        "░▒▓█▓▒░      ░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ \n"
                        +
                        "░▒▓█▓▒░      ░▒▓█▓▒░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░  ░▒▓█▓▒░   ░▒▓████████▓▒░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░");
        System.out.println();
        telaLogin();

        if (usuarioLogado != null) {
            menuPrincipal();
        }

        System.out.println("\nPrograma encerrado.");

    }

    private static void telaLogin() {
        int opcao;

        do {
            System.out.println("\n=== TELA DE LOGIN ===");
            System.out.println("1 - Fazer login");
            System.out.println("2 - Criar nova conta");
            System.out.println("0 - Sair do sistema");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
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

        } while (opcao != 0 && usuarioLogado == null);
    }

    private static void fazerLogin() {
        System.out.println("\n=== FAZER LOGIN ===");
        System.out.print("Digite seu email: ");
        String email = sc.nextLine();

        System.out.print("Digite sua senha: ");
        String senha = sc.nextLine();

        usuarioLogado = usuarioController.login(email, senha);

        if (usuarioLogado != null) {
            System.out.println("Login realizado com sucesso!");
            System.out.println("Bem-vindo, " + usuarioLogado.getNome() + "!");
        } else {
            System.out.println("Email ou senha incorretos. Tente novamente.");
        }
    }

    private static void criarConta() {
        System.out.println("\n=== CRIAR NOVA CONTA ===");
        System.out.print("Digite seu nome: ");
        String nome = sc.nextLine();

        System.out.print("Digite seu email: ");
        String email = sc.nextLine();

        System.out.print("Digite sua senha: ");
        String senha = sc.nextLine();

        Usuario novoUsuario = new Usuario(nome, email, senha);
        usuarioController.cadastrar(novoUsuario);

        System.out.println("Conta criada com sucesso! Agora faça login.");
    }

    private static void menuPrincipal() {
        int opcao;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("Usuário: " + usuarioLogado.getNome());
            System.out.println("------------------------");
            System.out.println("1 - Minha Conta");
            System.out.println("2 - Meus Filmes");
            System.out.println("3 - Minha Watchlist");
            System.out.println("4 - Minhas Avaliações");
            System.out.println("5 - Filmes Assistidos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
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

        } while (opcao != 0);
    }

    private static void menuUsuarios() {
        int opcao;

        do {
            System.out.println("\n=== MENU USUÁRIOS ===");
            System.out.println("1 - Listar todos os usuários");
            System.out.println("2 - Remover usuário do sistema");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) {
                listarUsuarios();
            } else if (opcao == 2) {
                removerUsuario();
            } else if (opcao != 0) {
                System.out.println("Opção inválida! Digite 0, 1 ou 2.");
            }

        } while (opcao != 0);
    }

    private static void listarUsuarios() {
        System.out.println("\n=== LISTA DE USUÁRIOS ===");
        List<Usuario> listaUsuarios = usuarioController.listar();

        if (listaUsuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            System.out.println("Usuários cadastrados no sistema:");
            for (Usuario usuario : listaUsuarios) {
                System.out.println("Nome: " + usuario.getNome() + " | Email: " + usuario.getEmail());
            }
            System.out.println("Total: " + listaUsuarios.size() + " usuário(s)");
        }
    }

    private static void removerUsuario() {
        System.out.println("\n=== REMOVER USUÁRIO DO SISTEMA ===");

        List<Usuario> usuariosCadastrados = usuarioController.listar();

        if (usuariosCadastrados.isEmpty()) {
            System.out.println("Não há usuários cadastrados no sistema.");
            return;
        }

        System.out.println("Usuários cadastrados no sistema:");

        int contador = 0;
        for (int i = 0; i < usuariosCadastrados.size(); i++) {
            Usuario usuario = usuariosCadastrados.get(i);

            if (usuarioLogado != null && usuario.getId() == usuarioLogado.getId()) {
                continue;
            }

            System.out.println((contador + 1) + " - " + usuario.getNome() +
                    " | Email: " + usuario.getEmail());
            contador++;
        }

        if (contador == 0) {
            System.out.println("Só existe você cadastrado no sistema.");
            return;
        }

        System.out.print("\nDigite o número do usuário que deseja remover: ");

        try {
            String entrada = sc.nextLine();
            int opcao = Integer.parseInt(entrada);

            if (opcao < 1 || opcao > contador) {
                System.out.println("Opção inválida! Escolha um número da lista.");
                return;
            }

            int indexReal = -1;
            int contadorLista = 0;
            for (int i = 0; i < usuariosCadastrados.size(); i++) {
                Usuario usuario = usuariosCadastrados.get(i);

                if (usuarioLogado != null && usuario.getId() == usuarioLogado.getId()) {
                    continue;
                }

                contadorLista++;
                if (contadorLista == opcao) {
                    indexReal = i;
                    break;
                }
            }

            if (indexReal == -1) {
                System.out.println("Erro ao encontrar usuário.");
                return;
            }

            String nomeUsuario = usuariosCadastrados.get(indexReal).getNome();
            String emailUsuario = usuariosCadastrados.get(indexReal).getEmail();

            System.out.println("\n=== ATENÇÃO ===");
            System.out.println("Você está prestes a remover o usuário:");
            System.out.println("Nome: " + nomeUsuario);
            System.out.println("Email: " + emailUsuario);
            System.out.println("\nIsso irá remover TODOS os dados relacionados:");
            System.out.println("- Todas as avaliações feitas por este usuário");
            System.out.println("- Watchlist do usuário");
            System.out.println("- Histórico de filmes assistidos");
            System.out.println("- Conta do usuário");
            System.out.println("\nEsta operação NÃO pode ser desfeita!");

            System.out.print("\nDigite 'CONFIRMAR' para prosseguir ou qualquer coisa para cancelar: ");
            String confirmacao = sc.nextLine();

            if (confirmacao.equalsIgnoreCase("CONFIRMAR")) {
                usuarioController.remover(nomeUsuario);
                System.out.println("Operação concluída.");
            } else {
                System.out.println("Operação cancelada.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Erro! Digite apenas números.");
        }
    }

    private static void menuFilmes() {
        int opcao;

        do {
            System.out.println("\n=== MENU FILMES ===");
            System.out.println("1 - Cadastrar novo filme");
            System.out.println("2 - Listar todos os filmes");
            System.out.println("3 - Remover filme do sistema");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");

            String entrada = sc.nextLine();

            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
                opcao = -1;
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarFilme();
                    break;
                case 2:
                    listarFilmes();
                    break;
                case 3:
                    removerFilme();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida! Digite 0, 1, 2 ou 3.");
            }

        } while (opcao != 0);
    }

    private static void cadastrarFilme() {
        System.out.println("\n=== CADASTRAR FILME ===");

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

        Filme novoFilme = new Filme(titulo, ano, genero, diretor, duracao, classificacao);
        filmeController.cadastrar(novoFilme);

        System.out.println("Filme cadastrado com sucesso!");
    }

    private static void listarFilmes() {
        System.out.println("\n--- LISTA DE FILMES ---");
        List<Filme> listaFilmes = filmeController.listar();

        if (listaFilmes.isEmpty()) {
            System.out.println("Nenhum filme cadastrado.");
        } else {
            System.out.println("Filmes cadastrados no sistema:");
            for (Filme filme : listaFilmes) {
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

    private static void removerFilme() {
        System.out.println("\n=== REMOVER FILME DO SISTEMA ===");

        List<Filme> filmesCadastrados = filmeController.listar();

        if (filmesCadastrados.isEmpty()) {
            System.out.println("Não há filmes cadastrados no sistema.");
            return;
        }

        System.out.println("Filmes cadastrados no sistema:");
        for (int i = 0; i < filmesCadastrados.size(); i++) {
            Filme filme = filmesCadastrados.get(i);
            System.out.println((i + 1) + " - " + filme.getTitulo() +
                    " (" + filme.getAno() + ")");
        }

        System.out.print("\nDigite o número do filme que deseja remover: ");

        try {

            String entrada = sc.nextLine();
            int opcao = Integer.parseInt(entrada);

            if (opcao < 1 || opcao > filmesCadastrados.size()) {
                System.out.println("Opção inválida! Escolha um número da lista.");
                return;
            }

            String tituloFilme = filmesCadastrados.get(opcao - 1).getTitulo();

            System.out.println("\nATENÇÃO: Esta operação não pode ser desfeita!");
            System.out.print("Tem certeza que deseja remover \"" + tituloFilme + "\"? (S/N): ");
            String confirmacao = sc.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                filmeController.remover(tituloFilme);
            } else {
                System.out.println("Operação cancelada.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Erro! Digite apenas números.");
        }
    }

    private static void menuWatchlist() {
        int opcao;

        do {
            System.out.println("\n=== MINHA WATCHLIST ===");
            System.out.println("1 - Adicionar filme à watchlist");
            System.out.println("2 - Ver minha watchlist");
            System.out.println("3 - Remover filme da watchlist");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    adicionarWatchlist();
                    break;
                case 2:
                    verWatchlist();
                    break;
                case 3:
                    removerDaWatchlist();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida! Digite 0, 1, 2 ou 3.");
            }

        } while (opcao != 0);
    }

    private static void adicionarWatchlist() {
        System.out.println("\n--- ADICIONAR À WATCHLIST ---");

        System.out.print("Nome do filme que deseja assistir: ");
        String nomeFilme = sc.nextLine();

        Watchlist item = new Watchlist(usuarioLogado.getNome(), nomeFilme);
        watchlistController.adicionar(item);

        System.out.println("Filme adicionado à sua watchlist!");
    }

    private static void verWatchlist() {
        System.out.println("\n=== MINHA WATCHLIST ===");
        List<Watchlist> minhaWatchlist = watchlistController.listar(usuarioLogado.getNome());

        if (minhaWatchlist.isEmpty()) {
            System.out.println("Sua watchlist está vazia.");
            System.out.println("Adicione filmes que você quer assistir!");
        } else {
            System.out.println("Filmes na sua watchlist:");
            for (Watchlist item : minhaWatchlist) {
                System.out.println("- " + item.getNomeFilme());
            }
            System.out.println("Total: " + minhaWatchlist.size() + " filme(s) na lista");
        }
    }

    private static void removerDaWatchlist() {
        System.out.println("\n=== REMOVER DA WATCHLIST ===");

        List<Watchlist> minhaWatchlist = watchlistController.listar(usuarioLogado.getNome());

        if (minhaWatchlist.isEmpty()) {
            System.out.println("Sua watchlist está vazia.");
            return;
        }

        System.out.println("Sua watchlist atual:");
        for (int i = 0; i < minhaWatchlist.size(); i++) {
            Watchlist item = minhaWatchlist.get(i);
            System.out.println((i + 1) + " - " + item.getNomeFilme());
        }

        System.out.print("\nDigite o número do filme que deseja remover: ");

        try {
            int opcao = sc.nextInt();
            sc.nextLine();

            if (opcao < 1 || opcao > minhaWatchlist.size()) {
                System.out.println("Opção inválida! Escolha um número da lista.");
                return;
            }

            String nomeFilme = minhaWatchlist.get(opcao - 1).getNomeFilme();

            System.out.print("Tem certeza que deseja remover \"" + nomeFilme + "\"? (S/N): ");
            String confirmacao = sc.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                Watchlist item = new Watchlist(usuarioLogado.getNome(), nomeFilme);
                watchlistController.remover(item);

            } else {
                System.out.println("Operação cancelada.");
            }

        } catch (Exception e) {
            System.out.println("Erro! Digite apenas números.");
            sc.nextLine();
        }
    }

    private static void menuAvaliacoes() {
        int opcao;

        do {
            System.out.println("\n=== MINHAS AVALIAÇÕES ===");
            System.out.println("1 - Avaliar um filme");
            System.out.println("2 - Listar avaliações");
            System.out.println("3 - Remover avaliações");
            System.out.println("4 - Editar  avaliações");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) {
                avaliarFilme();
            } else if (opcao == 2) {
                listarMinhasAvaliacoes();
            } else if (opcao == 3) {
                removerAvaliacao();
            } else if (opcao == 4) {
                editarAvaliacao();
            } else if (opcao != 0) {
                System.out.println("Opção inválida! Digite 0, 1, 2, 3 ou 4.");
            }

        } while (opcao != 0);
    }

    private static void avaliarFilme() {
        System.out.println("\n=== AVALIAR FILME ===");

        System.out.print("Nome do filme que você quer avaliar: ");
        String nomeFilme = sc.nextLine();

        List<FilmeAssistido> filmesAssistidos = assistidoController.listar(usuarioLogado.getNome());
        boolean filmeAssistido = false;

        for (FilmeAssistido fa : filmesAssistidos) {
            if (fa.getNomeFilme().equalsIgnoreCase(nomeFilme)) {
                filmeAssistido = true;
                break;
            }
        }

        if (!filmeAssistido) {
            System.out.println("Você não pode avaliar este filme!");
            System.out.println("Primeiro assista ao filme e registre no menu 'Filmes Assistidos'.");
            return;
        }

        System.out.print("Nota (de 1 a 10, onde 10 é excelente): ");
        int nota = sc.nextInt();
        sc.nextLine();

        if (nota < 1 || nota > 10) {
            System.out.println("Nota inválida! Deve ser entre 1 e 10.");
            return;
        }

        System.out.print("Sua opinião sobre o filme: ");
        String review = sc.nextLine();

        Avaliacao avaliacao = new Avaliacao(usuarioLogado.getNome(), nomeFilme, nota, review);
        avaliacaoController.avaliar(avaliacao);

        System.out.println("Avaliação registrada com sucesso!");
        System.out.println("Obrigado por compartilhar sua opinião!");
    }

    private static void listarMinhasAvaliacoes() {
        System.out.println("\n=== MINHAS AVALIAÇÕES ===");
        List<Avaliacao> minhasAvaliacoes = avaliacaoController.listarPorUsuario(usuarioLogado.getNome());

        if (minhasAvaliacoes.isEmpty()) {
            System.out.println("Você ainda não fez nenhuma avaliação.");
            System.out.println("Avalie um filme no menu 'Minhas Avaliações'!");
        } else {
            System.out.println("Suas avaliações:");
            System.out.println("========================================");

            for (Avaliacao av : minhasAvaliacoes) {
                System.out.println("Filme: " + av.getNomeFilme());
                System.out.println("Nota: " + av.getNota() + "/10");
                System.out.println("Opinião: " + av.getReview());
                System.out.println("========================================");
            }

            System.out.println("Total: " + minhasAvaliacoes.size() + " avaliação(ões)");
        }
    }

    private static void removerAvaliacao() {
        System.out.println("\n===  REMOVER AVALIAÇÃO === ");

        List<Avaliacao> minhasAvaliacoes = avaliacaoController.listarPorUsuario(usuarioLogado.getNome());

        if (minhasAvaliacoes.isEmpty()) {
            System.out.println("Você não tem nenhuma avaliação para remover.");
            return;
        }

        System.out.println("Suas avaliações:");
        for (int i = 0; i < minhasAvaliacoes.size(); i++) {
            Avaliacao av = minhasAvaliacoes.get(i);
            System.out.println((i + 1) + " - Filme: " + av.getNomeFilme());
            System.out.println("    Nota: " + av.getNota() + "/10");
            System.out.println("    Opinião: " + av.getReview());
            System.out.println("    ---");
        }

        System.out.print("\nDigite o número da avaliação que deseja remover: ");

        try {
            String entrada = sc.nextLine();
            int opcao = Integer.parseInt(entrada);

            if (opcao < 1 || opcao > minhasAvaliacoes.size()) {
                System.out.println("Opção inválida! Escolha um número da lista.");
                return;
            }

            Avaliacao avaliacaoSelecionada = minhasAvaliacoes.get(opcao - 1);
            String nomeFilme = avaliacaoSelecionada.getNomeFilme();

            System.out.println("\nVocê está prestes a remover sua avaliação do filme:");
            System.out.println("Filme: " + nomeFilme);
            System.out.println("Nota: " + avaliacaoSelecionada.getNota() + "/10");
            System.out.println("Opinião: " + avaliacaoSelecionada.getReview());

            System.out.print("\nTem certeza que deseja remover esta avaliação? (S/N): ");
            String confirmacao = sc.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                avaliacaoController.remover(usuarioLogado.getNome(), nomeFilme);
            } else {
                System.out.println("Operação cancelada.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Erro! Digite apenas números.");
        }
    }

    private static void editarAvaliacao() {
        System.out.println("\n===  EDITAR AVALIAÇÃO === ");

        List<Avaliacao> minhasAvaliacoes = avaliacaoController.listarPorUsuario(usuarioLogado.getNome());

        if (minhasAvaliacoes.isEmpty()) {
            System.out.println("Você não tem nenhuma avaliação para editar.");
            return;
        }

        System.out.println("Suas avaliações:");
        for (int i = 0; i < minhasAvaliacoes.size(); i++) {
            Avaliacao av = minhasAvaliacoes.get(i);
            System.out.println((i + 1) + " - Filme: " + av.getNomeFilme());
            System.out.println("    Nota atual: " + av.getNota() + "/10");
            System.out.println("    Opinião atual: " + av.getReview());
            System.out.println("    ---");
        }

        System.out.print("\nDigite o número da avaliação que deseja editar: ");

        try {
            String entrada = sc.nextLine();
            int opcao = Integer.parseInt(entrada);

            if (opcao < 1 || opcao > minhasAvaliacoes.size()) {
                System.out.println("Opção inválida! Escolha um número da lista.");
                return;
            }

            Avaliacao avaliacaoAntiga = minhasAvaliacoes.get(opcao - 1);
            String nomeFilme = avaliacaoAntiga.getNomeFilme();

            System.out.println("\nEditando avaliação do filme: " + nomeFilme);
            System.out.println("Nota atual: " + avaliacaoAntiga.getNota() + "/10");
            System.out.println("Opinião atual: " + avaliacaoAntiga.getReview());
            System.out.println("----------------------------------------");

            int novaNota;
            while (true) {
                System.out.print("Nova nota (1 a 10, ou 0 para manter a atual): ");
                String notaStr = sc.nextLine();

                if (notaStr.trim().isEmpty() || notaStr.equals("0")) {
                    novaNota = avaliacaoAntiga.getNota();
                    System.out.println("Manter nota atual: " + novaNota);
                    break;
                }

                try {
                    novaNota = Integer.parseInt(notaStr);
                    if (novaNota >= 1 && novaNota <= 10) {
                        break;
                    } else {
                        System.out.println("Nota inválida! Digite um número entre 1 e 10.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Digite um número.");
                }
            }

            System.out.println("\nOpinião atual: " + avaliacaoAntiga.getReview());
            System.out.print("Nova opinião (pressione ENTER para manter a atual): ");
            String novaReview = sc.nextLine();

            if (novaReview.trim().isEmpty()) {
                novaReview = avaliacaoAntiga.getReview();
                System.out.println("Manter opinião atual.");
            }

            System.out.println("\n===  RESUMO DAS ALTERAÇÕES === ");
            System.out.println("Filme: " + nomeFilme);
            System.out.println("Nota: " + avaliacaoAntiga.getNota() + " → " + novaNota);
            System.out.println("Opinião: " + avaliacaoAntiga.getReview());
            System.out.println("         ↓");
            System.out.println("         " + novaReview);

            System.out.print("\nConfirmar alterações? (S/N): ");
            String confirmacao = sc.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {

                Avaliacao avaliacaoAtualizada = new Avaliacao(
                        usuarioLogado.getNome(),
                        nomeFilme,
                        novaNota,
                        novaReview);

                avaliacaoController.editar(avaliacaoAtualizada);

            } else {
                System.out.println("Edição cancelada.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Erro! Digite apenas números.");
        }
    }

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

            switch (opcao) {
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

        } while (opcao != 0);
    }

    private static void registrarFilmeAssistido() {
        System.out.println("\n=== REGISTRAR FILME ASSISTIDO ===");

        System.out.print("Nome do filme que você assistiu: ");
        String nomeFilme = sc.nextLine();

        System.out.print("Data que assistiu (formato: ano/mês/dia, ex: 2009/07/10): ");
        String data = sc.nextLine();

        FilmeAssistido filme = new FilmeAssistido(usuarioLogado.getNome(), nomeFilme, data);

        assistidoController.registrar(filme);

    }

    private static void verFilmesAssistidos() {
        System.out.println("\n=== MEUS FILMES ASSISTIDOS ===");
        List<FilmeAssistido> meusFilmes = assistidoController.listar(usuarioLogado.getNome());

        if (meusFilmes.isEmpty()) {
            System.out.println("Você ainda não registrou nenhum filme assistido.");
            System.out.println("Registre os filmes que você já assistiu!");
        } else {
            System.out.println("Filmes que você já assistiu:");
            for (FilmeAssistido filme : meusFilmes) {
                System.out.println("- " + filme.getNomeFilme() + " (Assistido em: " + filme.getDataAssistido() + ")");
            }
            System.out.println("Total: " + meusFilmes.size() + " filme(s) assistido(s)");
        }
    }
}