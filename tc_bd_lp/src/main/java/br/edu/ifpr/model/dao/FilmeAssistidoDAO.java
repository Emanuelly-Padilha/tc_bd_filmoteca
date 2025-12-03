package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.model.FilmeAssistido;
import br.edu.ifpr.model.Usuario;

public class FilmeAssistidoDAO {

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    FilmeDAO filmeDAO = new FilmeDAO();

    public void inserir(FilmeAssistido fa) {

        Usuario usuario = usuarioDAO.buscarPorNome(fa.getNomeUsuario());

        if (usuario == null) {
            System.out.println("Erro: Usuário não encontrado: " + fa.getNomeUsuario());
            return;
        }

        int usuarioId = usuario.getId();
        int filmeId = filmeDAO.buscarIdPorTitulo(fa.getNomeFilme());

        String sql = "INSERT INTO filmes_assistidos (id_usuario, id_filme, data_assistido) VALUES (?, ?, ?)";

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, usuarioId);
            ps.setInt(2, filmeId);
            ps.setString(3, fa.getDataAssistido());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Filme registrado como assistido: " + fa.getNomeFilme());
                removerDaWatchlist(usuarioId, filmeId);
            }

            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro ao registrar filme assistido: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<FilmeAssistido> listar(String nomeUsuario) {

        Usuario usuario = usuarioDAO.buscarPorNome(nomeUsuario);

        if (usuario == null) {
            System.out.println("Erro: Usuário não encontrado: " + nomeUsuario);
            return new ArrayList<>();
        }

        int usuarioId = usuario.getId();

        List<FilmeAssistido> lista = new ArrayList<FilmeAssistido>();

        String sql = "SELECT f.titulo AS filme, fa.data_assistido " +
                "FROM filmes_assistidos fa " +
                "JOIN filmes f ON f.id = fa.id_filme " +
                "WHERE fa.id_usuario = ? " +
                "ORDER BY fa.data_assistido DESC";

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, usuarioId);

            ResultSet rs = ps.executeQuery();

            int contador = 0;
            while (rs.next()) {
                String nomeFilme = rs.getString("filme");
                String dataAssistido = rs.getString("data_assistido");

                FilmeAssistido filme = new FilmeAssistido(nomeUsuario, nomeFilme, dataAssistido);

                lista.add(filme);
                contador++;
            }

            System.out.println("Encontrados " + contador + " filmes assistidos para " + nomeUsuario);

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro ao listar filmes assistidos: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    private void removerDaWatchlist(int usuarioId, int filmeId) {
        String sql = "DELETE FROM watchlist WHERE id_usuario = ? AND id_filme = ?";

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, usuarioId);
            ps.setInt(2, filmeId);

            int linhasRemovidas = ps.executeUpdate();

            if (linhasRemovidas > 0) {
                System.out.println("Filme removido automaticamente da sua watchlist!");
            }

            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro ao remover da watchlist: " + e.getMessage());
        }
    }

}