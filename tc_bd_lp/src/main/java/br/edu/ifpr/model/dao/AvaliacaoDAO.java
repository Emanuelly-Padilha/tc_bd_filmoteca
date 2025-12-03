package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.model.Avaliacao;
import br.edu.ifpr.model.Usuario;

public class AvaliacaoDAO {
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    FilmeDAO filmeDAO = new FilmeDAO();

    public void inserir(Avaliacao a) {

        Usuario usuario = usuarioDAO.buscarPorNome(a.getNomeUsuario());

        if (usuario == null) {
            System.out.println("Erro: Usuário não encontrado: " + a.getNomeUsuario());
            return;
        }

        int usuarioId = usuario.getId();
        int filmeId = filmeDAO.buscarIdPorTitulo(a.getNomeFilme());

        String sql = "INSERT INTO avaliacoes (id_usuario, id_filme, nota, review) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            ps.setInt(2, filmeId);
            ps.setInt(3, a.getNota());
            ps.setString(4, a.getReview());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Avaliacao> listarPorUsuario(String nomeUsuario) {
        List<Avaliacao> lista = new ArrayList<>();

        String sql = "SELECT u.nome as nome_usuario, f.titulo as nome_filme, a.nota, a.review " +
                "FROM avaliacoes a " +
                "JOIN usuarios u ON a.id_usuario = u.id " +
                "JOIN filmes f ON a.id_filme = f.id " +
                "WHERE u.nome = ? " +
                "ORDER BY f.titulo";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nomeUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String usuario = rs.getString("nome_usuario");
                String nomeFilme = rs.getString("nome_filme");
                int nota = rs.getInt("nota");
                String review = rs.getString("review");

                Avaliacao avaliacao = new Avaliacao(usuario, nomeFilme, nota, review);
                lista.add(avaliacao);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar avaliações por usuário: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;

    }
}
