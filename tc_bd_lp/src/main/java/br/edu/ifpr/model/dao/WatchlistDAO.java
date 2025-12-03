package br.edu.ifpr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.model.Usuario;
import br.edu.ifpr.model.Watchlist;

public class WatchlistDAO {

    UsuarioDAO usuarioDAO = new UsuarioDAO();
    FilmeDAO filmeDAO = new FilmeDAO();

    public void inserir(Watchlist w) {

        Usuario usuario = usuarioDAO.buscarPorNome(w.getNomeUsuario());

        if (usuario == null) {
            System.out.println("Erro: Usuário não encontrado: " + w.getNomeUsuario());
            return;
        }

        int usuarioId = usuario.getId();
        int filmeId = filmeDAO.buscarIdPorTitulo(w.getNomeFilme());

        String sql = "INSERT INTO watchlist (id_usuario, id_filme) VALUES (?, ?)";

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, usuarioId);
            ps.setInt(2, filmeId);

            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar à watchlist: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Watchlist> listarPorUsuario(String nomeUsuario) {

        Usuario usuario = usuarioDAO.buscarPorNome(nomeUsuario);

        if (usuario == null) {
            System.out.println("Erro: Usuário não encontrado: " + nomeUsuario);
            return new ArrayList<>();
        }

        int usuarioId = usuario.getId();

        List<Watchlist> lista = new ArrayList<Watchlist>();

        String sql = "SELECT f.titulo AS filme " +
                "FROM watchlist w " +
                "JOIN filmes f ON f.id = w.id_filme " +
                "WHERE w.id_usuario = ?";

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Watchlist item = new Watchlist(nomeUsuario, rs.getString("filme"));

                lista.add(item);
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro ao listar watchlist: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    public void remover(Watchlist w) {

        Usuario usuario = usuarioDAO.buscarPorNome(w.getNomeUsuario());

        if (usuario == null) {
            System.out.println("Erro: Usuário não encontrado: " + w.getNomeUsuario());
            return;
        }

        int usuarioId = usuario.getId();
        int filmeId = filmeDAO.buscarIdPorTitulo(w.getNomeFilme());

        if (filmeId == -1) {
            System.out.println("Erro: Filme não encontrado: " + w.getNomeFilme());
            return;
        }

        String sql = "DELETE FROM watchlist WHERE id_usuario = ? AND id_filme = ?";

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, usuarioId);
            ps.setInt(2, filmeId);

            int linhasRemovidas = ps.executeUpdate();

            if (linhasRemovidas > 0) {
                System.out.println("Filme removido da watchlist: " + w.getNomeFilme());
            } else {
                System.out.println("Filme não encontrado na sua watchlist: " + w.getNomeFilme());
            }

            ps.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro ao remover da watchlist: " + e.getMessage());
            e.printStackTrace();
        }
    }
}