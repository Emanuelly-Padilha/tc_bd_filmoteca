package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.model.Filme;

public class FilmeDAO {
    public void inserir(Filme f) {
        String sql = "INSERT INTO filmes (titulo, genero, ano, diretor, duracao, classificacao) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, f.getTitulo());
            ps.setString(2, f.getGenero());
            ps.setInt(3, f.getAno());
            ps.setString(4, f.getDiretor());
            ps.setInt(5, f.getDuracao());
            ps.setString(6, f.getClassificacao());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int buscarIdPorTitulo(String titulo) {
        String sql = "SELECT id FROM filmes WHERE titulo = ?";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, titulo);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return rs.getInt("id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Filme> listar() {
        List<Filme> lista = new ArrayList<>();

        String sql = "SELECT titulo, ano, genero, diretor, duracao, classificacao FROM filmes";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                int ano = rs.getInt("ano");
                String genero = rs.getString("genero");
                String diretor = rs.getString("diretor");
                int duracao = rs.getInt("duracao");
                String classificacao = rs.getString("classificacao");

                Filme filme = new Filme(titulo, ano, genero, diretor, duracao, classificacao);
                lista.add(filme);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void remover(String tituloFilme) {
        int filmeId = buscarIdPorTitulo(tituloFilme);

        if (filmeId == -1) {
            System.out.println("Erro: Filme não encontrado: " + tituloFilme);
            return;
        }

        Connection conn = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);

            String sqlAvaliacoes = "DELETE FROM avaliacoes WHERE id_filme = ?";
            PreparedStatement ps = conn.prepareStatement(sqlAvaliacoes);
            ps.setInt(1, filmeId);
            ps.executeUpdate();
            System.out.println("Avaliações do filme removidas.");

            String sqlWatchlist = "DELETE FROM watchlist WHERE id_filme = ?";
            ps = conn.prepareStatement(sqlWatchlist);
            ps.setInt(1, filmeId);
            ps.executeUpdate();
            System.out.println("Filme removido da watchlist de todos os usuários.");

            String sqlAssistidos = "DELETE FROM filmes_assistidos WHERE id_filme = ?";
            ps = conn.prepareStatement(sqlAssistidos);
            ps.setInt(1, filmeId);
            ps.executeUpdate();
            System.out.println("Histórico do filme removido.");

            String sqlFilme = "DELETE FROM filmes WHERE id = ?";
            ps = conn.prepareStatement(sqlFilme);
            ps.setInt(1, filmeId);
            int linhasRemovidas = ps.executeUpdate();

            conn.commit();

            if (linhasRemovidas > 0) {
                System.out.println("Filme '" + tituloFilme + "' removido com sucesso!");
                System.out.println("Todos os dados relacionados também foram removidos.");
            }

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
            System.out.println("Erro ao remover filme: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
